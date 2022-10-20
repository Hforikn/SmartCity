package cn.itcast.smartcity2.Activity.GeRenZX;

import android.content.Intent;
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

import cn.itcast.smartcity2.Activity.MainActivity;
import cn.itcast.smartcity2.Fragment.PersonDetailsFragment;
import cn.itcast.smartcity2.R;
import cn.itcast.smartcity2.Tools.SaveData;
import cn.itcast.smartcity2.Tools.SendRequest;

public class XiuGaiMiMaActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView xiugaimima_return;
    private EditText xiugaimima_oldPasswd;
    private EditText xiugaimima_newPasswd1;
    private EditText xiugaimima_newPasswd2;
    private Button xiugaimima_btn;
    private String oldPasswd;
    private String newPasswd1;
    private String newPasswd2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiu_gai_mi_ma);
        initView();
    }

    public void initView(){
        xiugaimima_return = findViewById(R.id.xiugaimima_return);
        xiugaimima_oldPasswd = findViewById(R.id.xiugaimima_oldPasswd);
        xiugaimima_newPasswd1 = findViewById(R.id.xiugaimima_newPasswd1);
        xiugaimima_newPasswd2 = findViewById(R.id.xiugaimima_newPasswd2);
        xiugaimima_btn = findViewById(R.id.xiugaimima_btn);

        xiugaimima_return.setOnClickListener(this);
        xiugaimima_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.xiugaimima_return:
                onBackPressed();
                break;
            case R.id.xiugaimima_btn:
                oldPasswd = xiugaimima_oldPasswd.getText().toString();
                newPasswd1 = xiugaimima_newPasswd1.getText().toString();
                newPasswd2 = xiugaimima_newPasswd2.getText().toString();
                if (TextUtils.isEmpty(oldPasswd) || TextUtils.isEmpty(newPasswd1) || TextUtils.isEmpty(newPasswd2)){
                    Toast.makeText(XiuGaiMiMaActivity.this, "输入有空", Toast.LENGTH_SHORT).show();
                }else if (!((newPasswd1.length() >= 6) & (newPasswd2.length() >= 6))){
                    Toast.makeText(XiuGaiMiMaActivity.this, "密码长度应大于等于六位", Toast.LENGTH_SHORT).show();
                }else if (!(newPasswd1.equals(newPasswd2))){
                    Toast.makeText(XiuGaiMiMaActivity.this, "两次输入的密码不一致", Toast.LENGTH_SHORT).show();
                }else {
                    String token = SaveData.get_token(XiuGaiMiMaActivity.this);
                    Map<String, Object> map  = new HashMap<>();
                    map.put("oldPassword",oldPasswd);
                    map.put("newPassword",newPasswd1);
                    SendRequest.put_request_token("/prod-api/api/common/user/resetPwd",0,mHandler,map,token);
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
                    SaveData.save_login(XiuGaiMiMaActivity.this,false);
                }else {
                    Toast.makeText(XiuGaiMiMaActivity.this, msgs, Toast.LENGTH_SHORT).show();
                }
            }
        }
    };
}