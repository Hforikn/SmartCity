package cn.itcast.smartcity2.Activity.WaiMai;

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

import cn.itcast.smartcity2.Adapter.WaiMai.DianPuListAdapter;
import cn.itcast.smartcity2.GetSet.WaiMai.Wai_DianPu;
import cn.itcast.smartcity2.R;
import cn.itcast.smartcity2.Tools.JsonParse;
import cn.itcast.smartcity2.Tools.SendRequest;

public class DianPuListActivity2 extends AppCompatActivity implements View.OnClickListener {

    private ImageView iv_return;
    private RecyclerView recyclerView;
    private DianPuListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dian_pu_list2);
        initView();
    }

    public void initView(){
        iv_return = findViewById(R.id.dianpu_list2_return);
        recyclerView = findViewById(R.id.dianpu_list2_recyclerview);

        iv_return.setOnClickListener(this);
        Bundle bundle = getIntent().getExtras();
        String data = bundle.getString("title");
        SendRequest.get_request("/prod-api/api/takeout/seller/list?name=" + data,0,mHandler);
    }

    private final Handler mHandler = new Handler(Looper.myLooper()){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0){
                String data = (String) msg.obj;
                JSONObject jsonObject = JSONObject.parseObject(data);
                String rows = jsonObject.getString("rows");
                final List<Wai_DianPu> dianPuList = JsonParse.getWai_DainPu(rows);
                recyclerView.setLayoutManager(new GridLayoutManager(DianPuListActivity2.this,1));
                adapter = new DianPuListAdapter(DianPuListActivity2.this, dianPuList, new DianPuListAdapter.OnItemClickListener() {
                    @Override
                    public void onClick(int pos) {
                        startActivity(JsonParse.chDianPuData(DianPuListActivity2.this,pos,dianPuList));
                        //Toast.makeText(DianPuListActivity2.this, "点击成功", Toast.LENGTH_SHORT).show();
                    }
                });
                recyclerView.setAdapter(adapter);
            }
        }
    };

    @Override
    public void onClick(View view) {
        onBackPressed();
    }
}