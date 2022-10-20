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

import java.util.List;

import cn.itcast.smartcity2.GetSet.WaiMai.Wai_FenLei;
import cn.itcast.smartcity2.R;

public class WaiIconAdapter extends RecyclerView.Adapter<WaiIconAdapter.MyViewHolder> {
    private Context mCContext;
    private OnItemClickListener mListener;
    private List<Wai_FenLei> iconList;

    public WaiIconAdapter(Context mCContext, List<Wai_FenLei> iconList, OnItemClickListener mListener) {
        this.mCContext = mCContext;
        this.mListener = mListener;
        this.iconList = iconList;
    }

    @NonNull
    @Override
    public WaiIconAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(mCContext)
                .inflate(R.layout.item_icon,parent,false));
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull WaiIconAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        Glide.with(mCContext)
                .load(iconList.get(position).getImgUrl())
                .into(holder.iv);
        holder.tv.setText(iconList.get(position).getThemeName());
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
            layout = itemView.findViewById(R.id.icon_layout);
            iv = itemView.findViewById(R.id.icon_iv);
            tv = itemView.findViewById(R.id.icon_tv);
        }
    }
    public interface OnItemClickListener{
        void onClick(int pos);
    }
}
