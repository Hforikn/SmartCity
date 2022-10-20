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

import java.util.List;

import cn.itcast.smartcity2.GetSet.BS;
import cn.itcast.smartcity2.GetSet.Car;
import cn.itcast.smartcity2.R;

public class BSListAdapter extends RecyclerView.Adapter<BSListAdapter.MyViewHolder> {
    private Context mCContext;
    private OnItemClickListener mListener;
    private List<BS> bsList;

    public BSListAdapter(Context mCContext, List<BS> bsList, OnItemClickListener mListener) {
        this.mCContext = mCContext;
        this.mListener = mListener;
        this.bsList = bsList;
    }

    @NonNull
    @Override
    public BSListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(mCContext)
                .inflate(R.layout.item_bs_list,parent,false));
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull BSListAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.title.setText(bsList.get(position).getName());
        holder.money.setText("￥" + bsList.get(position).getPrice());
        holder.address.setText(bsList.get(position).getFirst() + "------------->" + bsList.get(position).getEnd());
        holder.time.setText(bsList.get(position).getStartTime() + "至" + bsList.get(position).getEndTime());
        holder.licheng.setText("里程:" + bsList.get(position).getMileage() + "里");

        holder.relative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return bsList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout relative;
        TextView title;
        TextView money;
        TextView address;
        TextView time;
        TextView licheng;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            relative = itemView.findViewById(R.id.bs_relative);
            title = itemView.findViewById(R.id.bs_title);
            money = itemView.findViewById(R.id.bs_money);
            address = itemView.findViewById(R.id.bs_address);
            time = itemView.findViewById(R.id.bs_time);
            licheng = itemView.findViewById(R.id.bs_licheng);
        }
    }
    public interface OnItemClickListener{
        void onClick(int pos);
    }
}
