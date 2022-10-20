package cn.itcast.smartcity2.Activity.WaiMai;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSONObject;
import com.bumptech.glide.Glide;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.itcast.smartcity2.Adapter.WaiMai.CaiPinListAdapter;
import cn.itcast.smartcity2.GetSet.WaiMai.Wai_CaiPin;
import cn.itcast.smartcity2.R;
import cn.itcast.smartcity2.Tools.JsonParse;
import cn.itcast.smartcity2.Tools.SaveData;
import cn.itcast.smartcity2.Tools.SendRequest;

public class DianPuDetailsActivity extends AppCompatActivity{

    private ImageView back,iv;
    private TextView title,money,pingfen,yue;
    private RecyclerView recyclerView;
    private Bundle bundle;
    private CaiPinListAdapter caiPinListAdapter;
    private int categoryId;
    private int sellerId;
    private TextView shou;
    private String token;
    private boolean isShou;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dian_pu_details);
        initView();
    }

    public void initView(){
        back = findViewById(R.id.dianpu_details_return);
        iv = findViewById(R.id.dianpu_details_iv);
        title = findViewById(R.id.dianpu_details_title);
        money = findViewById(R.id.dianpu_details_money);
        pingfen = findViewById(R.id.dianpu_details_pingfen);
        yue = findViewById(R.id.dianpu_details_yue);
        recyclerView = findViewById(R.id.dianpu_details_recyclerview);
        shou = findViewById(R.id.dianpu_details_shou);

        backSetBtn();
        shouSetBtn();
        setData();
    }

    public void setData(){
        bundle = getIntent().getExtras();
        Glide.with(DianPuDetailsActivity.this)
                .load(bundle.getString("img"))
                .into(iv);
        title.setText(bundle.getString("title"));
        money.setText(bundle.getString("money"));
        pingfen.setText(bundle.getString("pingfen"));
        yue.setText(bundle.getString("yue"));

        categoryId = bundle.getInt("categoryId");
        sellerId = bundle.getInt("sellerId");
        SendRequest.get_request("/prod-api/api/takeout/product/list?categoryId=" + categoryId +"&sellerId=" + sellerId,0,mHandler);

        if (SaveData.get_login(DianPuDetailsActivity.this)){
            token = SaveData.get_token(DianPuDetailsActivity.this);
            SendRequest.get_request_token("/prod-api/api/takeout/collect/check?sellerId=" + sellerId,1,mHandler, token);
        }else {
            shou.setVisibility(View.GONE);
            Toast.makeText(this, "登录后查看是否收藏", Toast.LENGTH_SHORT).show();
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
                    String rows = jsonObject.getString("data");
                    List<Wai_CaiPin> caiPinList = JsonParse.getWai_CaiPinXQ(rows);
                    recyclerView.setLayoutManager(new GridLayoutManager(DianPuDetailsActivity.this,1));
                    recyclerView.setNestedScrollingEnabled(false);
                    caiPinListAdapter = new CaiPinListAdapter(DianPuDetailsActivity.this, caiPinList, new CaiPinListAdapter.OnItemClickListener() {
                        @Override
                        public void onClick(int pos) {
                            Toast.makeText(DianPuDetailsActivity.this, "点击成功", Toast.LENGTH_SHORT).show();
                        }
                    });
                    recyclerView.setAdapter(caiPinListAdapter);
                    break;
                case 1:
                    String data1 = (String) msg.obj;
                    JSONObject jsonObject1 = JSONObject.parseObject(data1);
                    isShou = jsonObject1.getBoolean("isCollect");
                    isShouC();
                    break;
                case 2:
                    String data2 = (String) msg.obj;
                    JSONObject jsonObject2 = JSONObject.parseObject(data2);
                    break;
            }
        }
    };
    public void shouSetBtn(){
        shou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isShou){
                    shou.setText("已收藏");
                    shou.setTextColor(Color.parseColor("#ffffff"));
                    shou.setBackgroundColor(Color.parseColor("#eea9b8"));
                    shou.postInvalidate();
                    isShouC();
                }else {
                    shou.setText("收藏");
                    shou.setTextColor(Color.parseColor("#000000"));
                    shou.setBackgroundColor(Color.parseColor("#eea9b8"));
                    shou.postInvalidate();
                    Map<String, Object> map = new HashMap<>();
                    map.put("sellerId",sellerId);
                    isShou = true;
                    SendRequest.post_request_token("/prod-api/api/takeout/collect",2,mHandler,map,token);
                    isShouC();
                }
            }
        });
    }

    public void backSetBtn(){
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }


    public void isShouC(){
        if (isShou){
            shou.setText("已收藏");
            shou.setTextColor(Color.parseColor("#ffffff"));
            shou.setBackgroundColor(Color.parseColor("#eea9b8"));
            shou.postInvalidate();
        }else {
            shou.setText("收藏");
            shou.setTextColor(Color.parseColor("#000000"));
            shou.setBackgroundColor(Color.parseColor("#eea9b8"));
            shou.postInvalidate();
        }
    }
}