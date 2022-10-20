package cn.itcast.smartcity2.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import cn.itcast.smartcity2.R;

public class LeftServiceAdapter extends RecyclerView.Adapter<LeftServiceAdapter.MyViewHolder> {
    private Context mContext;
    private OnItemClickListener mListener;

    public LeftServiceAdapter(Context mContext, OnItemClickListener mListener) {
        this.mContext = mContext;
        this.mListener = mListener;
    }

    private String[] titles = {"车主服务","生活服务","便民服务","全部服务"};
    @NonNull
    @Override
    public LeftServiceAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(mContext)
                .inflate(R.layout.item_left_service,parent,false));
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull LeftServiceAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {
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
            holder.tv.setTextColor(Color.parseColor("#737272"));
        }
    }

    @Override
    public int getItemCount() {
        return titles.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        LinearLayout layout;
        TextView tv;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView.findViewById(R.id.left_service_layout);
            tv = itemView.findViewById(R.id.left_service_title);
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
