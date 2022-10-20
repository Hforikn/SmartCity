package cn.itcast.smartcity2.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.bumptech.glide.Glide;

import java.util.List;

import cn.itcast.smartcity2.GetSet.BannerImg;
import cn.itcast.smartcity2.R;
import cn.itcast.smartcity2.Tools.JsonParse;
import cn.itcast.smartcity2.Tools.SendRequest;

public class WelComeActivity extends AppCompatActivity {

    private ImageView wel_imageView;
    private RelativeLayout wel_Rl;
    private TextView wel_time;
    private SharedPreferences.Editor editor;
    private boolean isFirstIn;
    private int time_num = 3000;
    private Time time;
    private Handler timeHandler = new Handler();
    private Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wel_come);


        wel_imageView = findViewById(R.id.wel_imageView);
        wel_Rl = findViewById(R.id.wel_rl);
        wel_time = findViewById(R.id.wel_time);

        SendRequest.get_request("/prod-api/api/rotation/list",0,mHandler);
    }

    public void isFirst(){
        timeHandler.removeCallbacks(runnable);
        SharedPreferences sp = getSharedPreferences("isFirst", Context.MODE_PRIVATE);
        editor = sp.edit();
        isFirstIn = sp.getBoolean("isFirst",true);
        if (isFirstIn){
            Intent intent = new Intent(WelComeActivity.this,GuideActivity.class);
            startActivity(intent);
            finish();
        }else {
            Intent intent = new Intent(WelComeActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

    }



    private final Handler mHandler = new Handler(Looper.myLooper()){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0){
                String data1 = (String) msg.obj;
                JSONObject jsonObject1 = JSONObject.parseObject(data1);
                String rows1 = jsonObject1.getString("rows");
                List<BannerImg> imgs = JsonParse.getBanner(rows1);
                String url = imgs.get(0).getAdvImg();
                Glide.with(WelComeActivity.this)
                        .load(url)
                        .into(wel_imageView);

                setTime();
                wel_Rl.setVisibility(View.VISIBLE);
                runnable = new Runnable() {
                    @Override
                    public void run() {
                        isFirst();
                    }
                };
                timeHandler.postDelayed(runnable,time_num);
            }
        }
    };


    public void setTime(){
        wel_time.setText("3s 跳过");
        time = new Time(time_num,1000);
        time.start();
        wel_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isFirst();
            }
        });
    }
    class Time extends CountDownTimer{

        public Time(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long l) {
            wel_time.setText(l / 1000 + "s 跳过");
        }

        @Override
        public void onFinish() {
            wel_time.setText("0s 跳过");
        }
    }
}