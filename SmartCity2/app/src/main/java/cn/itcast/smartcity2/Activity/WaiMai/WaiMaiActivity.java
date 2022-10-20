package cn.itcast.smartcity2.Activity.WaiMai;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

import cn.itcast.smartcity2.Adapter.WaiMai.WaiBarAdapter;
import cn.itcast.smartcity2.Fragment.PersonCenterFragment;
import cn.itcast.smartcity2.Fragment.PersonDetailsFragment;
import cn.itcast.smartcity2.Fragment.WaiMaiMainFragment;
import cn.itcast.smartcity2.GetSet.WaiMai.Wai_FenLei;
import cn.itcast.smartcity2.R;
import cn.itcast.smartcity2.Tools.JsonParse;
import cn.itcast.smartcity2.Tools.SaveData;
import cn.itcast.smartcity2.Tools.SendRequest;

public class WaiMaiActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView wai_return;
    private FrameLayout wai_frameLayout;
    private RecyclerView wai_recyclerview;
    private WaiBarAdapter waiBarAdapter;
    private WaiMaiMainFragment waiMaiMainFragment;
    private PersonDetailsFragment personDetailsFragment;
    private PersonCenterFragment personCenterFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wai_mai);
        initView();
    }

    public void initView(){
        wai_return = findViewById(R.id.waimai_return);
        wai_frameLayout = findViewById(R.id.main_FrameLayout);
        wai_recyclerview = findViewById(R.id.wai_recyclerview);

        wai_return.setOnClickListener(this);
        SendRequest.get_request("/prod-api/api/takeout/theme/list",0,mHandler);

        if (waiMaiMainFragment == null){
            waiMaiMainFragment = new WaiMaiMainFragment();
        }
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.main_FrameLayout,waiMaiMainFragment)
                .commitAllowingStateLoss();
    }

    private final Handler mHandler = new Handler(Looper.myLooper()){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0){
                String data = (String) msg.obj;
                JSONObject jsonObject = JSONObject.parseObject(data);
                String rows = jsonObject.getString("data");
                List<Wai_FenLei> waiFenLeis = JsonParse.getWai_FenLei(rows);
                wai_recyclerview.setLayoutManager(new GridLayoutManager(WaiMaiActivity.this,3));
                waiBarAdapter = new WaiBarAdapter(WaiMaiActivity.this, waiFenLeis, new WaiBarAdapter.OnItemClickListener() {
                    @Override
                    public void onClick(int pos) {
                        switch (pos){
                            case 0:
                                if (waiMaiMainFragment == null){
                                    waiMaiMainFragment = new WaiMaiMainFragment();
                                }
                                getSupportFragmentManager()
                                        .beginTransaction()
                                        .replace(R.id.main_FrameLayout,waiMaiMainFragment)
                                        .commitAllowingStateLoss();
                                break;
                            case 1:
                                break;
                            case 2:
                                if (SaveData.get_login(WaiMaiActivity.this)){
                                    if (personDetailsFragment == null){
                                        personDetailsFragment = new PersonDetailsFragment();
                                    }
                                    getSupportFragmentManager()
                                            .beginTransaction()
                                            .replace(R.id.main_FrameLayout,personDetailsFragment)
                                            .commitAllowingStateLoss();
                                }else {
                                    if (personCenterFragment == null){
                                        personCenterFragment = new PersonCenterFragment();
                                    }
                                    getSupportFragmentManager()
                                            .beginTransaction()
                                            .replace(R.id.main_FrameLayout,personCenterFragment)
                                            .commitAllowingStateLoss();
                                }
                                break;
                        }
                        waiBarAdapter.setmPosition(pos);
                        waiBarAdapter.notifyDataSetChanged();
                    }
                });
                wai_recyclerview.setAdapter(waiBarAdapter);
            }
        }
    };

    @Override
    public void onClick(View view) {
        onBackPressed();
    }
}