package cn.itcast.smartcity2.Activity.Bus;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

import cn.itcast.smartcity2.Adapter.BSListAdapter;
import cn.itcast.smartcity2.GetSet.BS;
import cn.itcast.smartcity2.R;
import cn.itcast.smartcity2.Tools.JsonParse;
import cn.itcast.smartcity2.Tools.SendRequest;

public class BSActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView bs_return;
    private RecyclerView bs_recyclerview;
    private BSListAdapter bsListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b_s);
        initView();
    }

    private void initView() {
        bs_return = (ImageView) findViewById(R.id.bs_return);
        bs_recyclerview = (RecyclerView) findViewById(R.id.bs_recyclerview);

        bs_return.setOnClickListener(this);
        SendRequest.get_request("/prod-api/api/bus/line/list",0,mHandler);
    }

    private Handler mHandler = new Handler(Looper.myLooper()){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0){
                String data = (String) msg.obj;
                JSONObject jsonObject = JSONObject.parseObject(data);
                String rows = String.valueOf(jsonObject.get("rows"));
                final List<BS> bsList = JsonParse.getBS(rows);
                bs_recyclerview.setLayoutManager(new GridLayoutManager(BSActivity.this,1));
                bsListAdapter = new BSListAdapter(BSActivity.this, bsList, new BSListAdapter.OnItemClickListener() {
                    @Override
                    public void onClick(int pos) {
                        //Toast.makeText(BSActivity.this, "点击成功", Toast.LENGTH_SHORT).show();
                        startActivity(JsonParse.chBS(BSActivity.this,pos,bsList));
                    }
                });
                bs_recyclerview.setAdapter(bsListAdapter);
            }
        }
    };

    @Override
    public void onClick(View view) {
        onBackPressed();
    }
}