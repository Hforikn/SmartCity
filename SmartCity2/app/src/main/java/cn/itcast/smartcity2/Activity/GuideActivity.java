package cn.itcast.smartcity2.Activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.alibaba.fastjson.JSONObject;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import cn.itcast.smartcity2.Adapter.GuideAdapter;
import cn.itcast.smartcity2.GetSet.BannerImg;
import cn.itcast.smartcity2.R;
import cn.itcast.smartcity2.Tools.JsonParse;
import cn.itcast.smartcity2.Tools.SendRequest;

public class GuideActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener, View.OnClickListener {

    private RelativeLayout relativeLayout;
    private ViewPager viewPager;
    private Button start_btn;
    private Button start_btn2;
    private LinearLayout point_layout;
    private View point_view;
    private List<ImageView> imageViewList;
    private int pWidth;
    private LinearLayout start_btnll;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        initView();
    }
    public void initView(){
        relativeLayout = findViewById(R.id.relativeLayout);
        viewPager = findViewById(R.id.guide_viewpager);
        start_btn = findViewById(R.id.start_btn);
        start_btn2 = findViewById(R.id.start_btn2);
        point_layout = findViewById(R.id.guide_layout);
        point_view = findViewById(R.id.guide_view);
        start_btnll = findViewById(R.id.start_btnll);

        relativeLayout.setVisibility(View.VISIBLE);

        SendRequest.get_request("/prod-api/api/rotation/list",0,mHandler);
        start_btn.setOnClickListener(GuideActivity.this);
        start_btn2.setOnClickListener(GuideActivity.this);
    }

    private final Handler mHandler = new Handler(Looper.myLooper()){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    String data = (String) msg.obj;
                    JSONObject jsonObject = JSONObject.parseObject(data);
                    String rows = jsonObject.getString("rows");
                    List<BannerImg> imgList = JsonParse.getBanner(rows);

                    ImageView iv;
                    imageViewList = new ArrayList<>();

                    View view;
                    LayoutParams params;
                    for (int i = 0; i < imgList.size(); i++) {
                        iv = new ImageView(GuideActivity.this);
                        Glide.with(GuideActivity.this)
                                .load(imgList.get(i).getAdvImg())
                                .into(iv);
                        imageViewList.add(iv);

                        view = new View(GuideActivity.this);
                        view.setBackgroundResource(R.drawable.point_white);
                        params = new LayoutParams(20,20);
                        if (i != 0){
                            params.leftMargin = 10;
                        }
                        view.setLayoutParams(params);
                        point_layout.addView(view);
                    }
                    GuideAdapter adapter = new GuideAdapter(imageViewList);
                    viewPager.setAdapter(adapter);
                    viewPager.setOnPageChangeListener(GuideActivity.this);
                    break;
            }
        }
    };

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if (pWidth == 0){
            pWidth = point_layout.getChildAt(1).getLeft()  - point_layout.getChildAt(0).getLeft();
        }
        int pos = (int) (pWidth * (position + positionOffset));
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) point_view.getLayoutParams();
        params.leftMargin = pos;
        point_view.setLayoutParams(params);
    }

    @Override
    public void onPageSelected(int position) {
        if (position == imageViewList.size() - 1){
            start_btnll.setVisibility(View.VISIBLE);
        }else {
            start_btnll.setVisibility(View.GONE);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //进入首页按钮
            case R.id.start_btn:
                Intent intent = new Intent(GuideActivity.this, MainActivity.class);
                startActivity(intent);
                sp = getSharedPreferences("isFirst",Context.MODE_PRIVATE);
                editor = sp.edit();
                editor.putBoolean("isFirst",false);
                editor.commit();
                finish();
                break;
            //网络设置
            case R.id.start_btn2:
                AlertDialog dialog;
                LayoutInflater factory = LayoutInflater.from(GuideActivity.this);
                final View ll = factory.inflate(R.layout.dialog_wl,null);
                final EditText et_IP = ll.findViewById(R.id.et_ip);
                final EditText et_number = ll.findViewById(R.id.et_number);
                AlertDialog.Builder builder = new AlertDialog.Builder(this)
                        .setTitle("网络设置")
                        .setIcon(R.mipmap.ic_launcher)
                        .setView(ll)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                String ip = et_IP.getText().toString();
                                String number = et_number.getText().toString();
                                if (TextUtils.isEmpty(ip) || TextUtils.isEmpty(number)){
                                    Toast.makeText(GuideActivity.this, "输入有空", Toast.LENGTH_SHORT).show();
                                }
                                Toast.makeText(GuideActivity.this, "保存成功", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });
                dialog = builder.create();
                dialog.show();
                break;
        }

    }
}