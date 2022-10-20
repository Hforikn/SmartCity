package cn.itcast.smartcity2.Adapter.WaiMai;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
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

import cn.itcast.smartcity2.GetSet.WaiMai.Wai_FenLei;
import cn.itcast.smartcity2.R;

public class WaiBarAdapter extends RecyclerView.Adapter<WaiBarAdapter.MyViewHolder> {
    private Context mContext;
    private List<Wai_FenLei> iconList;
    private OnItemClickListener mListener;

    public WaiBarAdapter(Context mContext, List<Wai_FenLei> iconList, OnItemClickListener mListener) {
        this.mContext = mContext;
        this.iconList = iconList;
        this.mListener = mListener;
    }
    private String[] titles = {"首页","订单","我的"};
    @NonNull
    @Override
    public WaiBarAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(mContext)
                .inflate(R.layout.item_bar,parent,false));
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull WaiBarAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        Glide.with(mContext)
                .load(iconList.get(position).getImgUrl())
                .into(holder.iv);
        holder.tv.setText(titles[position]);
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onClick(position);
                notifyDataSetChanged();
            }
        });

        if (position == getmPosition()){
            holder.layout.setBackgroundColor(Color.parseColor("#EFEEEE"));
            holder.tv.setTextColor(Color.parseColor("#1A73E8"));
        }else {
            holder.layout.setBackgroundColor(Color.parseColor("#ffffff"));
            holder.tv.setTextColor(Color.parseColor("#000000"));
        }
    }

    @Override
    public int getItemCount() {
        return titles.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        LinearLayout layout;
        ImageView iv;
        TextView tv;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView.findViewById(R.id.bar_layout);
            iv = itemView.findViewById(R.id.bar_iv);
            tv = itemView.findViewById(R.id.bar_tv);
        }
    }

    public interface OnItemClickListener{
        void onClick(int pos);
    }

    private int mPosition;

    public int getmPosition() {
        return mPosition;
    }

    public void setmPosition(int mPosition) {
        this.mPosition = mPosition;
    }
}
