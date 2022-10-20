package cn.itcast.smartcity2.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import cn.itcast.smartcity2.GetSet.News;
import cn.itcast.smartcity2.R;

public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.MyViewHolder> {
    private Context mContext;
    private List<News> news;
    private OnItemClickListener mListener;

    public NewsListAdapter(Context mContext, List<News> news, OnItemClickListener mListener) {
        this.mContext = mContext;
        this.news = news;
        this.mListener = mListener;
    }

    @NonNull
    @Override
    public NewsListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(mContext)
                .inflate(R.layout.item_news_list,parent,false));
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull NewsListAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        Glide.with(mContext)
                .load(news.get(position).getCover())
                .into(holder.iv);
        holder.title.setText(news.get(position).getTitle());
        holder.pinglun.setText("评论: " + news.get(position).getCommentNum());
        holder.time.setText("发布日期: " + news.get(position).getPublishDate());
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onClick(position);
            }
        });
        holder.content.setText(Html.fromHtml(news.get(position).getContent()));
    }

    @Override
    public int getItemCount() {
        return news.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        LinearLayout layout;
        ImageView iv;
        TextView title;
        TextView pinglun;
        TextView time;
        TextView content;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView.findViewById(R.id.news_list_layout);
            iv = itemView.findViewById(R.id.news_list_iv);
            title = itemView.findViewById(R.id.news_list_title);
            pinglun = itemView.findViewById(R.id.news_list_pinglun);
            time = itemView.findViewById(R.id.news_list_time);
            content = itemView.findViewById(R.id.news_list_content);
        }
    }
    public interface OnItemClickListener{
        void onClick(int pos);
    }
}
