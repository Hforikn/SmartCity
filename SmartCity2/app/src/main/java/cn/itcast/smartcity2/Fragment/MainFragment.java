package cn.itcast.smartcity2.Fragment;


import android.content.Context;
import android.content.Intent;
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
import androidx.viewpager.widget.ViewPager;

import com.alibaba.fastjson.JSONObject;
import com.bumptech.glide.Glide;
import com.google.android.material.tabs.TabLayout;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.itcast.smartcity2.Activity.Bus.BSActivity;
import cn.itcast.smartcity2.Activity.MainActivity;
import cn.itcast.smartcity2.Activity.NewsListActivity;
import cn.itcast.smartcity2.Activity.TingN.TingNActivity;
import cn.itcast.smartcity2.Activity.WaiMai.WaiMaiActivity;
import cn.itcast.smartcity2.Adapter.FragmentAdapter;
import cn.itcast.smartcity2.Adapter.HotNewsAdapter;
import cn.itcast.smartcity2.Adapter.IconAdapter;
import cn.itcast.smartcity2.GetSet.BannerImg;
import cn.itcast.smartcity2.GetSet.Icon;
import cn.itcast.smartcity2.GetSet.News;
import cn.itcast.smartcity2.GetSet.News_FenLei;
import cn.itcast.smartcity2.R;
import cn.itcast.smartcity2.Tools.JsonParse;
import cn.itcast.smartcity2.Tools.SendRequest;

public class MainFragment extends Fragment implements TextView.OnEditorActionListener, View.OnClickListener {

    private View view;
    private EditText search_et;
    private Button search_btn;
    private Banner banner;
    private List<String> bannerList;
    private RecyclerView icon_recyclerview;
    private IconAdapter iconAdapter;
    private RecyclerView hotNews_recyclerview;
    private HotNewsAdapter hotNewsAdapter;
    private List<String> titles;
    private List<News_FenLei> leiList;
    private List<Fragment> fragments;
    private ViewPager news_list_Viewpager;
    private TabLayout tabLayout;
    private List<News> news;
    private AllServiceFragment allServiceFragment;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_main,container,false);
        initView();
        return view;
    }

    public void initView(){
        search_et = view.findViewById(R.id.search_et);
        search_btn = view.findViewById(R.id.search_btn);
        banner = view.findViewById(R.id.banner);
        icon_recyclerview = view.findViewById(R.id.icon_recyclerview);
        hotNews_recyclerview = view.findViewById(R.id.hotNews_recyclerview);
        news_list_Viewpager = view.findViewById(R.id.news_viewpager);
        tabLayout = view.findViewById(R.id.tabLayout);

        search_et.setOnEditorActionListener(MainFragment.this);
        search_btn.setOnClickListener(MainFragment.this);



        //新闻分类
        String fenlei_Url = "/prod-api/press/category/list";
        SendRequest.get_request(fenlei_Url,3,mHandler);

        //新闻列表
        String newsList_Url = "/prod-api/press/press/list";
        SendRequest.get_request(newsList_Url,4,mHandler);
    }

    private final Handler mHandler = new Handler(Looper.myLooper()){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            String data = (String) msg.obj;
            JSONObject jsonObject = JSONObject.parseObject(data);
            switch (msg.what){
                case 0:
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
                            startActivity(JsonParse.chNewsData(getActivity(),position,news));
                        }
                    });
                    break;
                case 1:
                    String rows1 = jsonObject.getString("rows");
                    final List<Icon> iconList = JsonParse.getIcon(rows1);
                    icon_recyclerview.setLayoutManager(new GridLayoutManager(getActivity(),4));
                    iconAdapter = new IconAdapter(getActivity(), iconList, new IconAdapter.OnItemClickListener() {
                        @Override
                        public void onClick(int pos) {
                            switch (pos){
                                case 0:
                                    Intent intent = new Intent(getActivity(), TingNActivity.class);
                                    startActivity(intent);
                                    break;
                                case 2:
                                    Intent intent1 = new Intent(getActivity(), BSActivity.class);
                                    startActivity(intent1);
                                    break;
                                case 6:
                                    Intent intent6 = new Intent(getActivity(), WaiMaiActivity.class);
                                    startActivity(intent6);
                                    break;
                                case 7:
                                    if (allServiceFragment == null){
                                        allServiceFragment = new AllServiceFragment(iconList);
                                    }
                                    getFragmentManager()
                                            .beginTransaction()
                                            .replace(R.id.main_FrameLayout,allServiceFragment)
                                            .commitAllowingStateLoss();
                                    MainActivity.set(1);
                                    break;
                            }
                            //Toast.makeText(getActivity(), "点击" + pos + "号图标成功成功", Toast.LENGTH_SHORT).show();
                        }
                    });
                    icon_recyclerview.setAdapter(iconAdapter);
                    break;
                case 2:
                    String rows2 = jsonObject.getString("rows");
                    final List<News> newsList = JsonParse.getNews(rows2);
                    hotNews_recyclerview.setLayoutManager(new GridLayoutManager(getActivity(),2));
                    hotNews_recyclerview.setNestedScrollingEnabled(false);
                    hotNewsAdapter = new HotNewsAdapter(getActivity(), newsList, new HotNewsAdapter.OnItemClickListener() {
                        @Override
                        public void onClick(int pos) {
                            Intent intent = JsonParse.chNewsData(getActivity(),pos,newsList);
                            startActivity(intent);
                        }
                    });
                    hotNews_recyclerview.setAdapter(hotNewsAdapter);
                    break;
                case 3:
                    String rows3 = jsonObject.getString("data");
                    leiList = JsonParse.getNews_FenLei(rows3);
                    titles = new ArrayList<>();
                    for (int i = 0; i < leiList.size(); i++) {
                        titles.add(leiList.get(i).getName());
                    }
                    break;
                case 4:
                    String rows4 = jsonObject.getString("rows");
                    news = JsonParse.getNews(rows4);
                    fragments = new ArrayList<>();
                    Map<Integer ,ArrayList<News>> newsMap=new HashMap<Integer ,ArrayList<News>>();
                    for (int i = 0; i < news.size(); i++) {
                        int type=Integer.valueOf(news.get(i).getType());
                        if(!newsMap.containsKey(type)){
                            newsMap.put(type,new ArrayList<News>());
                        }
                        newsMap.get(type).add(news.get(i));
                    }

                    for (int i = 0; i < leiList.size(); i++) {
                        fragments.add(new NewsListFragment(newsMap.get(leiList.get(i).getId())));
                    }

                    FragmentAdapter fragmentAdapter = new FragmentAdapter(getChildFragmentManager(),titles,fragments);
                    news_list_Viewpager.setAdapter(fragmentAdapter);
                    tabLayout.setupWithViewPager(news_list_Viewpager);

                    //轮播图
                    String bannerUrl = "/prod-api/api/rotation/list";
                    SendRequest.get_request(bannerUrl,0,mHandler);

                    //全部服务
                    String serviceUrl = "/prod-api/api/service/list";
                    SendRequest.get_request(serviceUrl,1,mHandler);

                    //热门新闻
                    String hotNewsUrl = "/prod-api/press/press/list?hot=Y";
                    SendRequest.get_request(hotNewsUrl,2,mHandler);
                    MainActivity.bar_recyclerview.setVisibility(View.VISIBLE);
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
                Intent intent = new Intent(getActivity(), NewsListActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("title",data);
                intent.putExtras(bundle);
                startActivity(intent);
                Toast.makeText(getActivity(), "搜索  " + data +"  中", Toast.LENGTH_SHORT).show();
                search_et.setText("");
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
            Intent intent = new Intent(getActivity(), NewsListActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("title",data);
            intent.putExtras(bundle);
            startActivity(intent);
            Toast.makeText(getActivity(), "搜索  " + data +"  中", Toast.LENGTH_SHORT).show();
            search_et.setText("");
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
}