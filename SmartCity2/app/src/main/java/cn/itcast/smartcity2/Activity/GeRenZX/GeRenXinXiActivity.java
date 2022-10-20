package cn.itcast.smartcity2.Activity.GeRenZX;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

import cn.itcast.smartcity2.R;
import cn.itcast.smartcity2.Tools.SaveData;
import cn.itcast.smartcity2.Tools.SendRequest;

public class GeRenXinXiActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView ge_ren_xin_xi_return;
    private TextView ge_ren_xin_xi_userCard;
    private EditText ge_ren_xin_xi_nicheng;
    private EditText ge_ren_xin_xi_sex;
    private EditText ge_ren_xin_xi_email;
    private EditText ge_ren_xin_xi_number;
    private EditText ge_ren_xin_xi_card;
    private Button ge_ren_xin_xi_btn;
    private String nicheng;
    private String sex;
    private String email;
    private String number;
    private String card;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ge_ren_xin_xi);
        initView();
    }

    public void initView(){
        ge_ren_xin_xi_return = findViewById(R.id.ge_ren_xin_xi_return);
        ge_ren_xin_xi_userCard = findViewById(R.id.ge_ren_xin_xi_userCard);
        ge_ren_xin_xi_nicheng = findViewById(R.id.ge_ren_xin_xi_nicheng);
        ge_ren_xin_xi_sex = findViewById(R.id.ge_ren_xin_xi_sex);
        ge_ren_xin_xi_email = findViewById(R.id.ge_ren_xin_xi_email);
        ge_ren_xin_xi_number = findViewById(R.id.ge_ren_xin_xi_number);
        ge_ren_xin_xi_card = findViewById(R.id.ge_ren_xin_xi_card);
        ge_ren_xin_xi_btn = findViewById(R.id.ge_ren_xin_xi_btn);

        ge_ren_xin_xi_return.setOnClickListener(this);
        ge_ren_xin_xi_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ge_ren_xin_xi_return:
                onBackPressed();
                break;
            case R.id.ge_ren_xin_xi_btn:
                nicheng = ge_ren_xin_xi_nicheng.getText().toString();
                sex = ge_ren_xin_xi_sex.getText().toString();
                email = ge_ren_xin_xi_email.getText().toString();
                number = ge_ren_xin_xi_number.getText().toString();
                card = ge_ren_xin_xi_card.getText().toString();
                if (ge_ren_xin_xi_sex.getText().toString().equals("男")){
                    sex = "0";
                }else if (ge_ren_xin_xi_sex.getText().toString().equals("女")){
                    sex = "1";
                }else if (!(number.length() == 11)){
                    Toast.makeText(GeRenXinXiActivity.this, "密码长度为11位", Toast.LENGTH_SHORT).show();
                }else if (!(card.length() == 18)){
                    Toast.makeText(GeRenXinXiActivity.this, "身份证号为18位", Toast.LENGTH_SHORT).show();
                }else {
                    Map<String, Object> map = new HashMap<>();
                    map.put("nickName",nicheng);
                    map.put("sex",sex);
                    map.put("email",email);
                    map.put("phonenumber",number);
                    map.put("idCard",card);
                    String token = SaveData.get_token(GeRenXinXiActivity.this);
                    SendRequest.put_request_token("/prod-api/api/common/user",0,mHandler,map,token);
                }

                break;
        }
    }
    private final Handler mHandler = new Handler(Looper.myLooper()){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    String data = (String) msg.obj;
                    JSONObject jsonObject = JSONObject.parseObject(data);
                    String code = jsonObject.getString("code");
                    String msgs = jsonObject.getString("msg");
                    if (code.equals("200")){
                        onBackPressed();
                    }else {
                        Toast.makeText(GeRenXinXiActivity.this, msgs, Toast.LENGTH_SHORT).show();
                    }
                    break;
                case 1:
                    String data1 = (String) msg.obj;
                    JSONObject jsonObject1 = JSONObject.parseObject(data1);
                    String user = jsonObject1.getString("user");
                    JSONObject userData =JSONObject.parseObject(user);
                    if (userData.getString("nickName") == null){
                        ge_ren_xin_xi_nicheng.setText("     未设置");
                    }else {
                        ge_ren_xin_xi_nicheng.setText("   " + userData.getString("nickName"));
                    }
                    if (userData.getString("sex").equals("0")){
                        ge_ren_xin_xi_sex.setText("   男");
                    }else if (userData.getString("sex").equals("1")){
                        ge_ren_xin_xi_sex.setText("   女");
                    }
                    if (userData.getString("email") == null){
                        ge_ren_xin_xi_email.setText("     未设置");
                    }else {
                        ge_ren_xin_xi_email.setText("   " + userData.getString("email"));
                    }
                    if (userData.getString("idCard") == null){
                        ge_ren_xin_xi_card.setText("     未设置");
                    }else {
                        ge_ren_xin_xi_card.setText("   " + userData.getString("idCard"));
                    }
                    ge_ren_xin_xi_number.setText("   " + userData.getString("phonenumber"));
                    ge_ren_xin_xi_userCard.setText(userData.getString("userName"));
                    break;
            }
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        String token = SaveData.get_token(GeRenXinXiActivity.this);
        SendRequest.get_request_token("/prod-api/api/common/user/getInfo",1,mHandler,token);
    }
}