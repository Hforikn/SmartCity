package cn.itcast.smartcity2.Activity.GeRenZX;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

import cn.itcast.smartcity2.R;
import cn.itcast.smartcity2.Tools.SaveData;
import cn.itcast.smartcity2.Tools.SendRequest;

public class YiJianActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView yijian_return;
    private EditText yijian_title;
    private EditText yijian_content;
    private Button yijian_btn;
    private String title;
    private String content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yi_jian);
        initView();
    }

    public void initView(){
        yijian_return = findViewById(R.id.yijian_return);
        yijian_title = findViewById(R.id.yijian_title);
        yijian_content = findViewById(R.id.yijian_content);
        yijian_btn = findViewById(R.id.yijian_btn);

        yijian_return.setOnClickListener(this);
        yijian_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.yijian_return:
                onBackPressed();
                break;
            case R.id.yijian_btn:
                title = yijian_title.getText().toString();
                content = yijian_content.getText().toString();
                if (TextUtils.isEmpty(title) || TextUtils.isEmpty(content)){
                    Toast.makeText(YiJianActivity.this, "请输入意见再提交", Toast.LENGTH_SHORT).show();
                }else {
                    String token = SaveData.get_token(YiJianActivity.this);
                    Map<String, Object> map = new HashMap<>();
                    map.put("title",title);
                    map.put("content",content);
                    SendRequest.post_request_token("/prod-api/api/common/feedback",0,mHandler,map,token);
                }
                break;
        }
    }

    private final Handler mHandler = new Handler(Looper.myLooper()){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0){
                String data = (String) msg.obj;
                JSONObject jsonObject = JSON.parseObject(data);
                String code = jsonObject.getString("code");
                String msgs = jsonObject.getString("msg");
                if (code.equals("200")){
                    onBackPressed();
                    yijian_title.setText("");
                    yijian_content.setText("");
                    Toast.makeText(YiJianActivity.this, "提交成功", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(YiJianActivity.this, msgs, Toast.LENGTH_SHORT).show();
                }
            }
        }
    };
}