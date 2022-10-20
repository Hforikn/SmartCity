package cn.itcast.smartcity2.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import cn.itcast.smartcity2.Adapter.NewsListAdapter;
import cn.itcast.smartcity2.GetSet.News;
import cn.itcast.smartcity2.R;
import cn.itcast.smartcity2.Tools.JsonParse;

public class NewsListFragment extends Fragment {
    private View view;
    private RecyclerView newsList_recyclerview;
    private List<News> newsList;
    private NewsListAdapter newsListAdapter;

    public NewsListFragment(List<News> list){
        this.newsList = list;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_news_list,container,false);
        initView();
        return view;
    }

    public void initView(){
        newsList_recyclerview = view.findViewById(R.id.newsList_recyclerview);
        newsList_recyclerview.setLayoutManager(new GridLayoutManager(getContext(),1));
        newsList_recyclerview.setNestedScrollingEnabled(false);
        newsListAdapter = new NewsListAdapter(getActivity(), newsList, new NewsListAdapter.OnItemClickListener() {
            @Override
            public void onClick(int pos) {
                //Toast.makeText(getActivity(), "点击成功", Toast.LENGTH_SHORT).show();
                Intent intent = JsonParse.chNewsData(getActivity(),pos,newsList);
                startActivity(intent);
            }
        });
        newsList_recyclerview.setAdapter(newsListAdapter);
    }
}