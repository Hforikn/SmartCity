package cn.itcast.smartcity2.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import cn.itcast.smartcity2.GetSet.News;
import cn.itcast.smartcity2.R;

public class HotNewsAdapter extends RecyclerView.Adapter<HotNewsAdapter.MyViewHolder> {
    private Context mContext;
    private List<News> newsList;
    private OnItemClickListener mListener;

    public HotNewsAdapter(Context mContext, List<News> newsList, OnItemClickListener mListener) {
        this.mContext = mContext;
        this.newsList = newsList;
        this.mListener = mListener;
    }

    @NonNull
    @Override
    public HotNewsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(mContext)
                .inflate(R.layout.item_hotnews,parent,false));
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HotNewsAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        RequestOptions options = new RequestOptions()
                .error(R.mipmap.ic_launcher)
                .bitmapTransform(new RoundedCorners(50));
        Glide.with(mContext)
                .load(newsList.get(position).getCover())
                .apply(options)
                .into(holder.iv);
        holder.tv.setText(newsList.get(position).getTitle());
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        LinearLayout layout;
        ImageView iv;
        TextView tv;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            layout = itemView.findViewById(R.id.hot_news_layout);
            iv = itemView.findViewById(R.id.hot_news_iv);
            tv = itemView.findViewById(R.id.hot_news_tv);
        }
    }

    public interface OnItemClickListener{
        void onClick(int pos);
    }
}
