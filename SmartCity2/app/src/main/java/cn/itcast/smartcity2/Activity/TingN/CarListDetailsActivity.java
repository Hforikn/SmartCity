package cn.itcast.smartcity2.Activity.TingN;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import cn.itcast.smartcity2.R;

public class CarListDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView iv;
    private ImageView details_return;
    private TextView title;
    private TextView address;
    private TextView juli;
    private TextView open;
    private TextView allPark;
    private TextView kongwei;
    private TextView money;
    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_list_details);
        initView();
    }

    private void initView(){
        iv = findViewById(R.id.car_details_iv);
        title = findViewById(R.id.car_details_title);
        address = findViewById(R.id.car_details_address);
        juli = findViewById(R.id.car_details_juli);
        open = findViewById(R.id.car_details_open);
        allPark = findViewById(R.id.car_details_allPark);
        kongwei = findViewById(R.id.car_details_kongwei);
        money = findViewById(R.id.car_details_money);
        details_return = findViewById(R.id.car_details_return);

        details_return.setOnClickListener(this);

        bundle = getIntent().getExtras();
        Glide.with(CarListDetailsActivity.this)
                .load(bundle.getString("img"))
                .into(iv);
        title.setText(bundle.getString("title"));
        address.setText("地址:" + bundle.getString("address"));
        juli.setText("距离:" + bundle.getString("juli") + "m");

        if (bundle.getString("open").equals("Y")){
            open.setText("是否对外开放: 是");
        }else {
            open.setText("是否对外开放: 否");
        }
        allPark.setText("车位总数:" + bundle.getString("allPark")+"个");
        kongwei.setText("空位:" + bundle.getString("kongwei") + "个");
        money.setText("收费:" + bundle.getString("money") + "元/小时");
    }

    @Override
    public void onClick(View view) {
        onBackPressed();
    }
}