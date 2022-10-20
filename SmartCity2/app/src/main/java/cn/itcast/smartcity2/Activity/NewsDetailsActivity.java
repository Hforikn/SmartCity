package cn.itcast.smartcity2.Activity;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import cn.itcast.smartcity2.R;

public class NewsDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView news_details_return;
    private TextView title;
    private ImageView iv;
    private TextView content;
    private TextView pinglun;
    private TextView time;
    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);
        initView();
    }

    public void initView(){
        news_details_return = findViewById(R.id.news_details_return);
        title = findViewById(R.id.news_details_title);
        iv = findViewById(R.id.news_details_iv);
        content = findViewById(R.id.news_details_content);
        pinglun = findViewById(R.id.news_details_pinglun);
        time = findViewById(R.id.news_details_time);

        news_details_return.setOnClickListener(NewsDetailsActivity.this);

        bundle = getIntent().getExtras();
        title.setText(bundle.getString("title"));
        content.setText(Html.fromHtml(bundle.getString("content")));
        pinglun.setText("评论:" +bundle.getString("pinglun"));
        time.setText("发布日期:" + bundle.getString("time"));
        Glide.with(NewsDetailsActivity.this)
                .load(bundle.getString("img"))
                .into(iv);
    }

    @Override
    public void onClick(View v) {
        onBackPressed();
    }
}