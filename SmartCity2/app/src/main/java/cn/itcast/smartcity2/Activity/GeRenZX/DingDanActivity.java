package cn.itcast.smartcity2.Activity.GeRenZX;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import cn.itcast.smartcity2.R;

public class DingDanActivity extends AppCompatActivity {

    private ImageView dingdan_return;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ding_dan);

        dingdan_return = findViewById(R.id.dingdan_return);
        dingdan_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}