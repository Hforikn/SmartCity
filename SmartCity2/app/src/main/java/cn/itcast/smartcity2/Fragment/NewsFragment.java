package cn.itcast.smartcity2.Fragment;


import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

import cn.itcast.smartcity2.Adapter.NewsListAdapter;
import cn.itcast.smartcity2.GetSet.News;
import cn.itcast.smartcity2.R;
import cn.itcast.smartcity2.Tools.JsonParse;
import cn.itcast.smartcity2.Tools.SendRequest;

public class NewsFragment extends Fragment {
    private View view;
    private RecyclerView news_recyclerview;
    private NewsListAdapter newsListAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_news,container,false);
        initView();
        return view;
    }

    public void initView(){
        news_recyclerview = view.findViewById(R.id.news_recyclerview);
        SendRequest.get_request("/prod-api/press/press/list",0,mHandler);
    }

    private final Handler mHandler = new Handler(Looper.myLooper()){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0){
                String data = (String) msg.obj;
                JSONObject jsonObject = JSONObject.parseObject(data);
                String rows = jsonObject.getString("rows");
                final List<News> newsList = JsonParse.getNews(rows);
                news_recyclerview.setLayoutManager(new GridLayoutManager(getActivity(),1));
                newsListAdapter = new NewsListAdapter(getActivity(), newsList, new NewsListAdapter.OnItemClickListener() {
                    @Override
                    public void onClick(int pos) {
                        startActivity(JsonParse.chNewsData(getActivity(),pos,newsList));
                    }
                });
                news_recyclerview.setAdapter(newsListAdapter);
            }
        }
    };
}