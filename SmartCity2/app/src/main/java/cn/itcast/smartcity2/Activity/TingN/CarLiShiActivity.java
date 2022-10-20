package cn.itcast.smartcity2.Activity.TingN;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import cn.itcast.smartcity2.R;

public class CarLiShiActivity extends AppCompatActivity {

    private ImageView lishi_return;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_li_shi);

        lishi_return = findViewById(R.id.lishi_return);
        lishi_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}