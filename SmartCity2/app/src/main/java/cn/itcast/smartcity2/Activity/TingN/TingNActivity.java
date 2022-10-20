package cn.itcast.smartcity2.Activity.TingN;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

import cn.itcast.smartcity2.Adapter.CarListAdapter;
import cn.itcast.smartcity2.GetSet.Car;
import cn.itcast.smartcity2.R;
import cn.itcast.smartcity2.Tools.JsonParse;
import cn.itcast.smartcity2.Tools.SendRequest;

public class TingNActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView ting_return;
    private RecyclerView ting_recyclerView;
    private TextView ting_list;
    private CarListAdapter carListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ting_nactivity);
        initView();
    }

    public void initView(){
        ting_return = findViewById(R.id.ting_return);
        ting_recyclerView = findViewById(R.id.ting_recyclerview);
        ting_list = findViewById(R.id.ting_list);

        ting_return.setOnClickListener(this);
        ting_list.setOnClickListener(this);
        SendRequest.get_request("/prod-api/api/park/lot/list",0,mHandler);
    }

    public final Handler mHandler = new Handler(Looper.myLooper()){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0){
                String data = (String) msg.obj;
                JSONObject jsonObject = JSONObject.parseObject(data);
                String rows = jsonObject.getString("rows");
                final List<Car> carList = JsonParse.getCar(rows);
                ting_recyclerView.setLayoutManager(new GridLayoutManager(TingNActivity.this,1));
                carListAdapter = new CarListAdapter(TingNActivity.this, carList, new CarListAdapter.OnItemClickListener() {
                    @Override
                    public void onClick(int pos) {
                        //Toast.makeText(TingNActivity.this, "点击成功", Toast.LENGTH_SHORT).show();
                        startActivity(JsonParse.chCarData(TingNActivity.this,pos,carList));
                    }
                });
                ting_recyclerView.setAdapter(carListAdapter);
            }
        }
    };
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.ting_return){
            onBackPressed();
        }else if (view.getId() == R.id.ting_list){
            Intent intent = new Intent(TingNActivity.this, CarLiShiActivity.class);
            startActivity(intent);
        }

    }
}