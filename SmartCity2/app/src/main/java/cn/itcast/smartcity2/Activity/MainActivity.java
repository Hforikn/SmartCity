package cn.itcast.smartcity2.Activity;

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
import com.bumptech.glide.Glide;

import java.util.List;

import cn.itcast.smartcity2.Adapter.BarAdapter;
import cn.itcast.smartcity2.Fragment.AllServiceFragment;
import cn.itcast.smartcity2.Fragment.MainFragment;
import cn.itcast.smartcity2.Fragment.NewsFragment;
import cn.itcast.smartcity2.Fragment.PersonCenterFragment;
import cn.itcast.smartcity2.Fragment.PersonDetailsFragment;
import cn.itcast.smartcity2.GetSet.BannerImg;
import cn.itcast.smartcity2.GetSet.Icon;
import cn.itcast.smartcity2.R;
import cn.itcast.smartcity2.Tools.JsonParse;
import cn.itcast.smartcity2.Tools.SaveData;
import cn.itcast.smartcity2.Tools.SendRequest;

public class MainActivity extends AppCompatActivity {

    private FrameLayout frameLayout;
    public static RecyclerView bar_recyclerview;
    private static BarAdapter barAdapter;
    private MainFragment mainFragment;
    private AllServiceFragment allServiceFragment;
    private PersonCenterFragment personCenterFragment;
    private PersonDetailsFragment personDetailsFragment;
    private NewsFragment newsFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    public void initView(){
        frameLayout = findViewById(R.id.main_FrameLayout);
        bar_recyclerview = findViewById(R.id.bar_recyclerview);


        mainFragment = new MainFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.main_FrameLayout,mainFragment)
                .commitAllowingStateLoss();
        MainActivity.bar_recyclerview.setVisibility(View.GONE);


        SendRequest.get_request("/prod-api/api/service/list",0,mHandler);

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
                    final List<Icon> iconList = JsonParse.getIcon(rows);
                    bar_recyclerview.setLayoutManager(new GridLayoutManager(MainActivity.this,4));
                    barAdapter = new BarAdapter(MainActivity.this, iconList, new BarAdapter.OnItemClickListener() {
                        @Override
                        public void onClick(int pos) {
                            switch (pos){
                                //首页
                                case 0:
                                    if (mainFragment == null){
                                        mainFragment = new MainFragment();
                                    }
                                    getSupportFragmentManager()
                                            .beginTransaction()
                                            .replace(R.id.main_FrameLayout,mainFragment)
                                            .commitAllowingStateLoss();
                                    bar_recyclerview.setVisibility(View.GONE);
                                    break;
                                case 1:
                                    if (allServiceFragment == null){
                                        allServiceFragment = new AllServiceFragment(iconList);
                                    }
                                    getSupportFragmentManager()
                                            .beginTransaction()
                                            .replace(R.id.main_FrameLayout,allServiceFragment)
                                            .commitAllowingStateLoss();
                                    break;
                                case 2:
                                    if (newsFragment == null){
                                        newsFragment = new NewsFragment();
                                    }
                                    getSupportFragmentManager()
                                            .beginTransaction()
                                            .replace(R.id.main_FrameLayout,newsFragment)
                                            .commitAllowingStateLoss();
                                    break;
                                case 3:
                                    if (SaveData.get_login(MainActivity.this)){
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
                            //Toast.makeText(MainActivity.this, "点击成功", Toast.LENGTH_SHORT).show();
                            barAdapter.setmPosition(pos);
                            barAdapter.notifyDataSetChanged();
                        }
                    });
                    bar_recyclerview.setAdapter(barAdapter);
                    break;
            }
        }
    };

    public static void set(int pos){
        barAdapter.setmPosition(pos);
        barAdapter.notifyDataSetChanged();
    }
}