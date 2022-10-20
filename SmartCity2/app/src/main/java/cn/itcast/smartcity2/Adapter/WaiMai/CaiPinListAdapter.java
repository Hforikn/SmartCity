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

import cn.itcast.smartcity2.GetSet.WaiMai.Wai_CaiPin;
import cn.itcast.smartcity2.R;

public class CaiPinListAdapter extends RecyclerView.Adapter<CaiPinListAdapter.MyViewHolder> {
    private Context mContext;
    private OnItemClickListener mListener;
    private List<Wai_CaiPin> caiPinList;

    public CaiPinListAdapter(Context mContext, List<Wai_CaiPin> list, OnItemClickListener mListener) {
        this.mContext = mContext;
        this.mListener = mListener;
        this.caiPinList = list;
    }

    @NonNull
    @Override
    public CaiPinListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(mContext)
                .inflate(R.layout.item_caipin_list,parent,false));
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CaiPinListAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.title.setText(caiPinList.get(position).getName());
        holder.haoping.setText("好评率: " + caiPinList.get(position).getFavorRate() + "%");
        holder.xiaoliang.setText("月销量: " + caiPinList.get(position).getSaleQuantity());
        holder.jiage.setText(caiPinList.get(position).getPrice() + "元");
        Glide.with(mContext)
                .load(caiPinList.get(position).getImgUrl())
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
        return caiPinList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        LinearLayout layout;
        TextView title;
        TextView haoping;
        TextView xiaoliang;
        TextView jiage;
        ImageView iv;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView.findViewById(R.id.caipin_layout);
            title = itemView.findViewById(R.id.caipin_title);
            haoping = itemView.findViewById(R.id.caipin_haoping);
            xiaoliang = itemView.findViewById(R.id.caipin_xiaoliang);
            jiage = itemView.findViewById(R.id.caipin_jiage);
            iv = itemView.findViewById(R.id.caipin_iv);
        }
    }
    public interface OnItemClickListener{
        void onClick(int pos);
    }
}
