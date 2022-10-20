package cn.itcast.smartcity2.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import cn.itcast.smartcity2.R;

public class PersonDetailsAdapter extends RecyclerView.Adapter<PersonDetailsAdapter.MyViewHolder> {
    private Context mContext;
    private OnItemClickListener mListener;

    public PersonDetailsAdapter(Context mContext, OnItemClickListener mListener) {
        this.mContext = mContext;
        this.mListener = mListener;
    }
    private String[] titles = {"个人信息","电子钱包","订单列表","修改密码","意见反馈","退出登录"};
    @NonNull
    @Override
    public PersonDetailsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(mContext)
                .inflate(R.layout.item_person_details,parent,false));
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull PersonDetailsAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.tv.setText(titles[position]);
        holder.relative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return titles.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout relative;
        TextView tv;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            relative = itemView.findViewById(R.id.details_relative);
            tv = itemView.findViewById(R.id.details_tv);
        }
    }

    public interface OnItemClickListener{
        void onClick(int pos);
    }

}
