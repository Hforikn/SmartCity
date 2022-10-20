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

import cn.itcast.smartcity2.GetSet.WaiMai.Wai_DianPu;
import cn.itcast.smartcity2.R;

public class DianPuListAdapter extends RecyclerView.Adapter<DianPuListAdapter.MyViewHolder> {
    private Context mContext;
    private OnItemClickListener mListener;
    private List<Wai_DianPu> dianPuList;

    public DianPuListAdapter(Context mContext, List<Wai_DianPu> dianPuList, OnItemClickListener mListener) {
        this.mContext = mContext;
        this.mListener = mListener;
        this.dianPuList = dianPuList;
    }

    @NonNull
    @Override
    public DianPuListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(mContext)
                .inflate(R.layout.item_dianpu_list,parent,false));
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull DianPuListAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.title.setText(dianPuList.get(position).getName());
        holder.pingfen.setText("评分: " + dianPuList.get(position).getScore());
        holder.xiaoliang.setText("月销量: " + dianPuList.get(position).getSaleQuantity());
        holder.renjun.setText("人均: " + dianPuList.get(position).getAvgCost() + "元");
        holder.juli.setText("距离: " + dianPuList.get(position).getDistance() + "m");
        holder.time.setText("到店时间: " + dianPuList.get(position).getDeliveryTime() + "min");
        Glide.with(mContext)
                .load(dianPuList.get(position).getImgUrl())
                .into(holder.iv);

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dianPuList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        LinearLayout layout;
        TextView title;
        TextView pingfen;
        TextView xiaoliang;
        TextView renjun;
        TextView juli;
        TextView time;
        ImageView iv;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView.findViewById(R.id.dianpu_layout);
            title = itemView.findViewById(R.id.dianpu_title);
            pingfen = itemView.findViewById(R.id.dianpu_pingfen);
            xiaoliang = itemView.findViewById(R.id.dianpu_xiaoliang);
            renjun = itemView.findViewById(R.id.dianpu_renjun);
            juli = itemView.findViewById(R.id.dianpu_juli);
            time = itemView.findViewById(R.id.dianpu_time);
            iv = itemView.findViewById(R.id.dianpu_iv);
        }
    }
    public interface OnItemClickListener{
        void onClick(int pos);
    }
}
