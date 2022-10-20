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

import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

import cn.itcast.smartcity2.R;
import cn.itcast.smartcity2.Tools.SendRequest;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView register_return;
    private EditText register_userCard;
    private EditText register_passwd1;
    private EditText register_passwd2;
    private EditText register_number;
    private EditText register_sex;
    private Button register_btn;
    private String userCard;
    private String passwd1;
    private String passwd2;
    private String number;
    private String sex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
    }

    public void initView(){
        register_return = findViewById(R.id.register_return);
        register_userCard = findViewById(R.id.register_userCard);
        register_passwd1 = findViewById(R.id.register_passwd1);
        register_passwd2 = findViewById(R.id.register_passwd2);
        register_number = findViewById(R.id.register_number);
        register_sex = findViewById(R.id.register_sex);
        register_btn = findViewById(R.id.register_btn);

        register_return.setOnClickListener(this);
        register_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.register_return:
                onBackPressed();
                break;
            case R.id.register_btn:
                userCard = register_userCard.getText().toString();
                passwd1 = register_passwd1.getText().toString();
                passwd2 = register_passwd2.getText().toString();
                number = register_number.getText().toString();
                sex = register_sex.getText().toString();
                if (!((register_sex.getText().toString().equals("男")) || (register_sex.getText().toString().equals("女")))){
                    Toast.makeText(this, "性别输入有误", Toast.LENGTH_SHORT).show();
                }else if (register_sex.getText().toString().equals("男")){
                    sex = "0";
                }else if (register_sex.getText().toString().equals("女")){
                    sex = "1";
                }
                if (TextUtils.isEmpty(userCard) || TextUtils.isEmpty(passwd1) || TextUtils.isEmpty(passwd2) || TextUtils.isEmpty(number) || TextUtils.isEmpty(sex)){
                    Toast.makeText(RegisterActivity.this, "输入有空", Toast.LENGTH_SHORT).show();
                }else if (!((passwd1.length() >= 6) & (passwd2.length() >= 6))){
                    Toast.makeText(RegisterActivity.this, "密码长度需大于等于六位", Toast.LENGTH_SHORT).show();
                }else if (!(number.length() == 11)){
                    Toast.makeText(RegisterActivity.this, "手机号码长度应为11位", Toast.LENGTH_SHORT).show();
                } else if (!(passwd1.equals(passwd2))){
                    Toast.makeText(RegisterActivity.this, "两次输入的密码不一致", Toast.LENGTH_SHORT).show();
                }else {
                    Map<String, Object> map = new HashMap<>();
                    map.put("userName",userCard);
                    map.put("password",passwd1);
                    map.put("phonenumber",number);
                    map.put("sex",sex);
                    SendRequest.post_request("/prod-api/api/register",0,mHandler,map);
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
                JSONObject jsonObject = JSONObject.parseObject(data);
                String code = jsonObject.getString("code");
                String msgs = jsonObject.getString("msg");
                if (code.equals("200")){
                    onBackPressed();
                }
                Toast.makeText(RegisterActivity.this, msgs, Toast.LENGTH_SHORT).show();
            }
        }
    };
}