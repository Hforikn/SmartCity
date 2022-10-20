package cn.itcast.smartcity2.Activity.GeRenZX;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

import cn.itcast.smartcity2.Adapter.MingXiAdapter;
import cn.itcast.smartcity2.GetSet.MingXi;
import cn.itcast.smartcity2.R;
import cn.itcast.smartcity2.Tools.JsonParse;
import cn.itcast.smartcity2.Tools.SaveData;
import cn.itcast.smartcity2.Tools.SendRequest;

public class MingXiActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView mingxi_return;
    private RecyclerView mingxi_recyclerView;
    private MingXiAdapter mingXiAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ming_xi);
        initView();
    }

    public void initView(){
        mingxi_return = findViewById(R.id.mingxi_return);
        mingxi_recyclerView = findViewById(R.id.mingxi_recyclerview);

        mingxi_return.setOnClickListener(this);

        String token = SaveData.get_token(MingXiActivity.this);
        SendRequest.get_request_token("/prod-api/api/common/balance/list",0,mHandler,token);

    }
    private final Handler mHandler = new Handler(Looper.myLooper()){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0){
                String data = (String) msg.obj;
                JSONObject jsonObject = JSONObject.parseObject(data);
                String rows = jsonObject.getString("rows");
                List<MingXi> mingXiList = JsonParse.getMingXi(rows);
                mingxi_recyclerView.setLayoutManager(new GridLayoutManager(MingXiActivity.this,1));
                mingXiAdapter = new MingXiAdapter(MingXiActivity.this,mingXiList);
                mingxi_recyclerView.setAdapter(mingXiAdapter);
            }
        }
    };

    @Override
    public void onClick(View view) {
        onBackPressed();
    }
}