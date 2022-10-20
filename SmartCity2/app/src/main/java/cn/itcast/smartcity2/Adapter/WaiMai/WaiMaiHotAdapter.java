package cn.itcast.smartcity2.Adapter.WaiMai;

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

import cn.itcast.smartcity2.GetSet.WaiMai.Wai_DianPu;
import cn.itcast.smartcity2.R;

public class WaiMaiHotAdapter extends RecyclerView.Adapter<WaiMaiHotAdapter.MyViewHolder> {
    private Context mContext;
    private List<Wai_DianPu> waiDianPus;
    private OnItemClickListener mListener;

    public WaiMaiHotAdapter(Context mContext, List<Wai_DianPu> newsList, OnItemClickListener mListener) {
        this.mContext = mContext;
        this.waiDianPus = newsList;
        this.mListener = mListener;
    }

    @NonNull
    @Override
    public WaiMaiHotAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(mContext)
                .inflate(R.layout.item_hot_waimai,parent,false));
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull WaiMaiHotAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        RequestOptions options = new RequestOptions()
                .bitmapTransform(new RoundedCorners(30));
        Glide.with(mContext)
                .load(waiDianPus.get(position).getImgUrl())
                .apply(options)
                .into(holder.iv);
        holder.tv.setText(waiDianPus.get(position).getName());
        holder.pingfen.setText("评分:" + waiDianPus.get(position).getScore());
        holder.dingdan.setText("最近3小时下单数:" + waiDianPus.get(position).getSaleNum3hour());
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return waiDianPus.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        LinearLayout layout;
        ImageView iv;
        TextView tv;
        TextView pingfen;
        TextView dingdan;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView.findViewById(R.id.hot_wai_layout);
            iv = itemView.findViewById(R.id.hot_wai_iv);
            tv = itemView.findViewById(R.id.hot_wai_tv);
            pingfen = itemView.findViewById(R.id.hot_wai_pingfen);
            dingdan = itemView.findViewById(R.id.hot_wai_dingdan);
        }
    }

    public interface OnItemClickListener{
        void onClick(int pos);
    }
}
