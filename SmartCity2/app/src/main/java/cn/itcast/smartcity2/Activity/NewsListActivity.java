package cn.itcast.smartcity2.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

import cn.itcast.smartcity2.Adapter.NewsListAdapter;
import cn.itcast.smartcity2.GetSet.News;
import cn.itcast.smartcity2.R;
import cn.itcast.smartcity2.Tools.JsonParse;
import cn.itcast.smartcity2.Tools.SendRequest;

public class NewsListActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView news_return;
    private RecyclerView news_recyclerview;
    private NewsListAdapter adapter;
    private List<News> newsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_list);
        initView();
    }

    public void initView(){
        news_return = findViewById(R.id.news_return);
        news_recyclerview = findViewById(R.id.news_recyclerview);

        news_return.setOnClickListener(NewsListActivity.this);
        Bundle bundle = getIntent().getExtras();
        String title = bundle.getString("title");
        SendRequest.get_request("/prod-api/press/press/list?title=" + title,0,mHandler);
    }

    private final Handler mHandler = new Handler(Looper.myLooper()){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0){
                String data = (String) msg.obj;
                JSONObject jsonObject = JSONObject.parseObject(data);
                String rows = jsonObject.getString("rows");
                newsList = JsonParse.getNews(rows);
                news_recyclerview.setLayoutManager(new GridLayoutManager(NewsListActivity.this,1));
                adapter = new NewsListAdapter(NewsListActivity.this, newsList, new NewsListAdapter.OnItemClickListener() {
                    @Override
                    public void onClick(int pos) {
                        Intent intent = JsonParse.chNewsData(NewsListActivity.this,pos, newsList);
                        startActivity(intent);
                    }
                });
                news_recyclerview.setAdapter(adapter);
            }
        }
    };

    @Override
    public void onClick(View v) {
        onBackPressed();
    }
}