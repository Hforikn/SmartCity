package cn.itcast.smartcity2.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import cn.itcast.smartcity2.GetSet.MingXi;
import cn.itcast.smartcity2.R;

public class MingXiAdapter extends RecyclerView.Adapter<MingXiAdapter.MyViewHolder> {
    private Context mContext;
    private List<MingXi> mingXiList;
    public MingXiAdapter(Context mContext, List<MingXi> list) {
        this.mContext = mContext;
        this.mingXiList = list;
    }

    @NonNull
    @Override
    public MingXiAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(mContext)
                .inflate(R.layout.item_ming_xi,parent,false));
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MingXiAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.title.setText(mingXiList.get(position).getChangeType());
        holder.time.setText(mingXiList.get(position).getChangeTime());

        if (mingXiList.get(position).getChangeType().equals("收入")){
            holder.money.setText("+" + mingXiList.get(position).getChangeAmount());
            holder.money.setTextColor(Color.parseColor("#eea9b8"));
        }else if (mingXiList.get(position).getChangeType().equals("支出")){
            holder.money.setText("-" + mingXiList.get(position).getChangeAmount());
            holder.money.setTextColor(Color.parseColor("#ff0000"));
        }
    }

    @Override
    public int getItemCount() {
        return mingXiList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView time;
        TextView money;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.mingxi_title);
            time = itemView.findViewById(R.id.mingxi_time);
            money = itemView.findViewById(R.id.mingxi_money);
        }
    }
}
