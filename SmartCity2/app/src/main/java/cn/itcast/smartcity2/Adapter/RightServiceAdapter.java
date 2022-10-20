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

import java.util.List;

import cn.itcast.smartcity2.GetSet.Icon;
import cn.itcast.smartcity2.R;

public class RightServiceAdapter extends RecyclerView.Adapter<RightServiceAdapter.MyViewHolder> {
    private Context mContext;
    private List<Icon> iconList;
    private OnItemClickListener mListener;

    public RightServiceAdapter(Context mContext, List<Icon> iconList, OnItemClickListener mListener) {
        this.mContext = mContext;
        this.iconList = iconList;
        this.mListener = mListener;
    }

    @NonNull
    @Override
    public RightServiceAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(mContext)
                .inflate(R.layout.item_right_icon,parent,false));
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RightServiceAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        Glide.with(mContext)
                .load(iconList.get(position).getImgUrl())
                .into(holder.iv);
        holder.tv.setText(iconList.get(position).getServiceName());
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return iconList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        LinearLayout layout;
        ImageView iv;
        TextView tv;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView.findViewById(R.id.right_icon_layout);
            iv = itemView.findViewById(R.id.right_icon_iv);
            tv = itemView.findViewById(R.id.right_icon_tv);
        }
    }
    public interface OnItemClickListener{
        void onClick(int pos);
    }
}
