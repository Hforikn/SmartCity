package cn.itcast.smartcity2.Activity.GeRenZX;

import android.content.DialogInterface;
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
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

import cn.itcast.smartcity2.R;
import cn.itcast.smartcity2.Tools.SaveData;
import cn.itcast.smartcity2.Tools.SendRequest;

public class QianBaoActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView qianbao_return;
    private TextView qianbao_money;
    private TextView qianbao_mingxi;
    private Button qianbao_btn;
    private String et_money;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qian_bao);
        initView();
    }

    public void initView(){
        qianbao_return = findViewById(R.id.qianbao_return);
        qianbao_money = findViewById(R.id.qianbao_money);
        qianbao_btn = findViewById(R.id.qianbao_btn);
        qianbao_mingxi = findViewById(R.id.qianbao_mingxi);

        qianbao_return.setOnClickListener(this);
        qianbao_btn.setOnClickListener(this);
        qianbao_mingxi.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.qianbao_return:
                onBackPressed();
                break;
            case R.id.qianbao_mingxi:
                Intent intent = new Intent(QianBaoActivity.this, MingXiActivity.class);
                startActivity(intent);
                break;
            case R.id.qianbao_btn:
                final EditText et = new EditText(QianBaoActivity.this);
                AlertDialog dialog;
                AlertDialog.Builder builder = new AlertDialog.Builder(this)
                        .setTitle("充值")
                        .setIcon(R.mipmap.ic_launcher)
                        .setView(et)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                et_money = et.getText().toString();
                                if (TextUtils.isEmpty(et_money)){
                                    Toast.makeText(QianBaoActivity.this, "输入为空", Toast.LENGTH_SHORT).show();
                                }
                                try {
                                    if (Integer.valueOf(et_money) <= 0){
                                        Toast.makeText(QianBaoActivity.this, "输入非法", Toast.LENGTH_SHORT).show();
                                    }else {
                                        String token = SaveData.get_token(QianBaoActivity.this);
                                        Map<String, Object> map = new HashMap<>();
                                        SendRequest.post_request_token("/prod-api/api/common/balance/recharge?money=" + et_money,1,mHandler,map,token);
                                    }
                                }catch (Exception e){

                                }

                            }
                        })
                        .setNegativeButton("取消",null);
                dialog = builder.create();
                dialog.show();
                break;
        }
    }

    private final Handler mHandler = new Handler(Looper.myLooper()){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what ==0){
                String data = (String) msg.obj;
                JSONObject jsonObject = JSONObject.parseObject(data);
                String user = jsonObject.getString("user");
                JSONObject userData =JSONObject.parseObject(user);
                String code = jsonObject.getString("code");
                String msgs = jsonObject.getString("msg");
                if (code.equals("200")){
                    qianbao_money.setText("￥" + userData.getString("balance"));
                }else {
                    Toast.makeText(QianBaoActivity.this, msgs, Toast.LENGTH_SHORT).show();
                }
            }else if (msg.what == 1){
                String data = (String) msg.obj;
                JSONObject jsonObject = JSONObject.parseObject(data);
                String code = jsonObject.getString("code");
                String msgs = jsonObject.getString("msg");
                if (code.equals("200")){
                    onResume();
                    Toast.makeText(QianBaoActivity.this, "充值" + et_money + "元成功" , Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(QianBaoActivity.this, msgs, Toast.LENGTH_SHORT).show();
                }
            }
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        String token = SaveData.get_token(QianBaoActivity.this);
        SendRequest.get_request_token("/prod-api/api/common/user/getInfo",0,mHandler,token);
    }
}