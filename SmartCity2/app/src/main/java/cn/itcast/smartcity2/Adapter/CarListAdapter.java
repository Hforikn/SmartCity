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

import cn.itcast.smartcity2.GetSet.Car;
import cn.itcast.smartcity2.R;

public class CarListAdapter extends RecyclerView.Adapter<CarListAdapter.MyViewHolder> {
    private Context mCContext;
    private OnItemClickListener mListener;
    private List<Car> carList;

    public CarListAdapter(Context mCContext, List<Car> carList, OnItemClickListener mListener) {
        this.mCContext = mCContext;
        this.mListener = mListener;
        this.carList = carList;
    }

    @NonNull
    @Override
    public CarListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(mCContext)
                .inflate(R.layout.item_car_list,parent,false));
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CarListAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.title.setText(carList.get(position).getParkName());
        holder.money.setText("收费:" + carList.get(position).getRates());
        holder.address.setText("地址:" + carList.get(position).getAddress());
        holder.juli.setText("距离:" + carList.get(position).getDistance() + "m");
        holder.kongwei.setText("空位:" + carList.get(position).getVacancy());

        holder.relative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return carList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout relative;
        TextView title;
        TextView money;
        TextView address;
        TextView juli;
        TextView kongwei;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            relative = itemView.findViewById(R.id.car_relative);
            title = itemView.findViewById(R.id.car_title);
            money = itemView.findViewById(R.id.car_money);
            address = itemView.findViewById(R.id.car_address);
            juli = itemView.findViewById(R.id.car_juli);
            kongwei = itemView.findViewById(R.id.car_kongwei);
        }
    }
    public interface OnItemClickListener{
        void onClick(int pos);
    }
}
