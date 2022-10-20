package cn.itcast.smartcity2.Activity.Bus;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import cn.itcast.smartcity2.R;

public class BSDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView bs_details_title;
    private TextView bs_details_money;
    private TextView bs_details_address;
    private TextView bs_details_time;
    private TextView bs_details_licheng;
    private Button bs_details_btn;
    private Bundle bundle;
    private ImageView bs_details_return;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b_s_details);
        initView();
    }

    private void initView() {
        bs_details_title = (TextView) findViewById(R.id.bs_details_title);
        bs_details_money = (TextView) findViewById(R.id.bs_details_money);
        bs_details_address = (TextView) findViewById(R.id.bs_details_address);
        bs_details_time = (TextView) findViewById(R.id.bs_details_time);
        bs_details_licheng = (TextView) findViewById(R.id.bs_details_licheng);
        bs_details_btn = (Button) findViewById(R.id.bs_details_btn);
        bs_details_return = (ImageView) findViewById(R.id.bs_details_return);

        bundle = getIntent().getExtras();
        bs_details_title.setText(bundle.getString("title"));
        bs_details_address.setText(bundle.getString("address"));
        bs_details_licheng.setText(bundle.getString("licheng"));
        bs_details_time.setText(bundle.getString("time"));
        bs_details_money.setText(bundle.getString("money"));

        bs_details_btn.setOnClickListener(this);
        bs_details_return.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bs_details_btn:
                Toast.makeText(this, "提交成功", Toast.LENGTH_SHORT).show();
                onBackPressed();
                break;
            case R.id.bs_details_return:
                onBackPressed();
                break;

        }
    }
}