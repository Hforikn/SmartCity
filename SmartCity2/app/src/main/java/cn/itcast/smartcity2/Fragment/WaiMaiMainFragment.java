package cn.itcast.smartcity2.Fragment;


import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSONObject;
import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import cn.itcast.smartcity2.Activity.WaiMai.DianPuListActivity1;
import cn.itcast.smartcity2.Activity.WaiMai.DianPuListActivity2;
import cn.itcast.smartcity2.Adapter.WaiMai.DianPuListAdapter;
import cn.itcast.smartcity2.Adapter.WaiMai.WaiIconAdapter;
import cn.itcast.smartcity2.Adapter.WaiMai.WaiMaiHotAdapter;
import cn.itcast.smartcity2.GetSet.BannerImg;
import cn.itcast.smartcity2.GetSet.WaiMai.Wai_DianPu;
import cn.itcast.smartcity2.GetSet.WaiMai.Wai_FenLei;
import cn.itcast.smartcity2.R;
import cn.itcast.smartcity2.Tools.JsonParse;
import cn.itcast.smartcity2.Tools.SendRequest;

public class WaiMaiMainFragment extends Fragment implements TextView.OnEditorActionListener, View.OnClickListener {

    private View view;
    private EditText search_et;
    private Button search_btn;
    private Banner banner;
    private List<String> bannerList;
    private RecyclerView icon_recyclerview;
    private WaiIconAdapter iconAdapter;
    private RecyclerView waiHot_recyclerview;
    private WaiMaiHotAdapter waihotadapter;
    private RecyclerView dianList_recyclerview;
    private DianPuListAdapter dianPuListAdapter;
    private List<Wai_DianPu> dianPuList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_main_waimai,container,false);
        initView();
        return view;
    }

    public void initView(){
        search_et = view.findViewById(R.id.wai_search_et);
        search_btn = view.findViewById(R.id.wai_search_btn);
        banner = view.findViewById(R.id.wai_banner);
        icon_recyclerview = view.findViewById(R.id.wai_icon_recyclerview);
        waiHot_recyclerview = view.findViewById(R.id.wai_hot_recyclerview);
        dianList_recyclerview = view.findViewById(R.id.dianList_recyclerview);

        search_et.setOnEditorActionListener(WaiMaiMainFragment.this);
        search_btn.setOnClickListener(WaiMaiMainFragment.this);

        //外卖轮播图
        SendRequest.get_request("/prod-api/api/takeout/rotation/list",0,mHandler);

        //全部服务
        SendRequest.get_request("/prod-api/api/takeout/theme/list",1,mHandler);

        //推荐店家
        SendRequest.get_request("/prod-api/api/takeout/seller/list?recommend=Y",2,mHandler);

        //店铺列表
        SendRequest.get_request("/prod-api/api/takeout/seller/list",3,mHandler);
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
                    List<BannerImg> list = JsonParse.getBanner(rows);
                    bannerList = new ArrayList<>();
                    for (int i = 0; i < list.size(); i++) {
                        bannerList.add(list.get(i).getAdvImg());
                    }
                    banner.setDelayTime(3000);
                    banner.setImages(bannerList)
                            .setImageLoader(new BannerList())
                            .start();
                    banner.setOnBannerListener(new OnBannerListener() {
                        @Override
                        public void OnBannerClick(int position) {
                            Toast.makeText(getActivity(), "点击成功", Toast.LENGTH_SHORT).show();
                        }
                    });
                    break;
                case 1:
                    String data1 = (String) msg.obj;
                    JSONObject jsonObject1 = JSONObject.parseObject(data1);
                    String rows1 = jsonObject1.getString("data");
                    List<Wai_FenLei> iconList = JsonParse.getWai_FenLei(rows1);
                    icon_recyclerview.setLayoutManager(new GridLayoutManager(getActivity(),5));
                    iconAdapter = new WaiIconAdapter(getActivity(), iconList, new WaiIconAdapter.OnItemClickListener() {
                        @Override
                        public void onClick(int pos) {
                            Intent intent = new Intent(getActivity(), DianPuListActivity1.class);
                            Bundle bundle = new Bundle();
                            bundle.putInt("id", pos+1);
                            intent.putExtras(bundle);
                            startActivity(intent);
                            //Toast.makeText(getActivity(), "点击" + pos + "号图标成功成功", Toast.LENGTH_SHORT).show();
                        }
                    });
                    icon_recyclerview.setAdapter(iconAdapter);
                    break;
                case 2:
                    String data2 = (String) msg.obj;
                    JSONObject jsonObject2 = JSONObject.parseObject(data2);
                    String rows2 = jsonObject2.getString("rows");
                    final List<Wai_DianPu> newsList = JsonParse.getWai_DainPu(rows2);
                    waiHot_recyclerview.setLayoutManager(new GridLayoutManager(getActivity(),2));
                    waiHot_recyclerview.setNestedScrollingEnabled(false);
                    waihotadapter = new WaiMaiHotAdapter(getActivity(), newsList, new WaiMaiHotAdapter.OnItemClickListener() {
                        @Override
                        public void onClick(int pos) {
                            startActivity(JsonParse.chDianPuData(getActivity(),pos,newsList));
                            //Toast.makeText(getActivity(), "点击成功", Toast.LENGTH_SHORT).show();
                        }
                    });
                    waiHot_recyclerview.setAdapter(waihotadapter);
                    break;
                case 3:
                    String data3 = (String) msg.obj;
                    JSONObject jsonObject3 = JSONObject.parseObject(data3);
                    String rows3 = jsonObject3.getString("rows");
                    dianPuList = JsonParse.getWai_DainPu(rows3);
                    dianList_recyclerview.setLayoutManager(new GridLayoutManager(getActivity(),1));
                    dianPuListAdapter = new DianPuListAdapter(getActivity(), dianPuList, new DianPuListAdapter.OnItemClickListener() {
                        @Override
                        public void onClick(int pos) {
                            startActivity(JsonParse.chDianPuData(getActivity(),pos,dianPuList));
                            //Toast.makeText(getActivity(), "点击" + pos + "号店铺成功", Toast.LENGTH_SHORT).show();
                        }
                    });
                    dianList_recyclerview.setAdapter(dianPuListAdapter);
                    break;
            }
        }
    };

    //搜索框监听事件
    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_SEARCH || actionId == EditorInfo.IME_ACTION_UNSPECIFIED){
            String data = search_et.getText().toString();
            if (!(TextUtils.isEmpty(data))){
                Toast.makeText(getActivity(), "搜索  "+data+"  店铺中", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), DianPuListActivity2.class);
                Bundle bundle = new Bundle();
                bundle.putString("title",data);
                intent.putExtras(bundle);
                startActivity(intent);
                return true;
            }
            Toast.makeText(getActivity(), "请输入搜索内容", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    //搜索按钮监听事件
    @Override
    public void onClick(View v) {
        String data = search_et.getText().toString();
        if (TextUtils.isEmpty(data)){
            Toast.makeText(getActivity(), "请输入搜索内容", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(getActivity(), "搜索  "+data+"  店铺中", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getActivity(), DianPuListActivity2.class);
            Bundle bundle = new Bundle();
            bundle.putString("title",data);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }

    class BannerList extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context)
                    .load(path)
                    .into(imageView);
        }
    }

    public boolean isPad(Context context){
        return (context.getResources().getConfiguration().screenLayout &
                Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }
}