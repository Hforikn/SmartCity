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

public class IconAdapter extends RecyclerView.Adapter<IconAdapter.MyViewHolder> {
    private Context mCContext;
    private OnItemClickListener mListener;
    private List<Icon> iconList;

    public IconAdapter(Context mCContext, List<Icon> iconList, OnItemClickListener mListener) {
        this.mCContext = mCContext;
        this.mListener = mListener;
        this.iconList = iconList;
    }

    @NonNull
    @Override
    public IconAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(mCContext)
                .inflate(R.layout.item_icon,parent,false));
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull IconAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        Glide.with(mCContext)
                .load(iconList.get(position).getImgUrl())
                .into(holder.iv);
        holder.tv.setText(iconList.get(position).getServiceName());
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onClick(position);
            }
        });
        if (position == 7){
            holder.tv.setText("更多服务");
        }
    }

    @Override
    public int getItemCount() {
        return 8;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        LinearLayout layout;
        ImageView iv;
        TextView tv;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView.findViewById(R.id.icon_layout);
            iv = itemView.findViewById(R.id.icon_iv);
            tv = itemView.findViewById(R.id.icon_tv);
        }
    }
    public interface OnItemClickListener{
        void onClick(int pos);
    }
}
