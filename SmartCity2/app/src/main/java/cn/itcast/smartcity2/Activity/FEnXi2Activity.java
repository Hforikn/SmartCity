package cn.itcast.smartcity2.Activity;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;

import java.util.ArrayList;
import java.util.List;

import cn.itcast.smartcity2.R;

public class FEnXi2Activity extends AppCompatActivity {

    private BarChart barchart;
    private LineChart linechart;
    private PieChart piechart;

    private float width = 0.2f;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_f_en_xi2);
        initView();
    }

    private void initView() {
        barchart = (BarChart) findViewById(R.id.barchart);
        linechart = (LineChart) findViewById(R.id.linechart);
        piechart = (PieChart) findViewById(R.id.piechart);
        setBar();
        setLine();
        setPie();
    }

    public void setBar(){
        List<BarEntry> values1 = new ArrayList<>();
        values1.add(new BarEntry(1,1));
        values1.add(new BarEntry(2,2));
        values1.add(new BarEntry(3,3));
        values1.add(new BarEntry(4,4));

        List<BarEntry> values2 = new ArrayList<>();
        values2.add(new BarEntry(1+width,1));
        values2.add(new BarEntry(2+width,2));
        values2.add(new BarEntry(3+width,3));
        values2.add(new BarEntry(4+width,4));

        BarDataSet set1 = new BarDataSet(values1,"bar1");
        BarDataSet set2 = new BarDataSet(values2,"bar2");
        set1.setColor(Color.RED);
        set2.setColor(Color.BLACK);

        BarData data = new BarData();
        data.addDataSet(set1);
        data.addDataSet(set2);
        data.setBarWidth(0.2f);
        barchart.setData(data);
        barchart.invalidate();
    }

    public void setLine(){
        List<Entry> values1 = new ArrayList<>();
        values1.add(new Entry(1,1));
        values1.add(new Entry(2,2));
        values1.add(new Entry(3,3));
        values1.add(new Entry(4,4));

        List<Entry> values2 = new ArrayList<>();
        values2.add(new Entry(1+width,1));
        values2.add(new Entry(2+width,2));
        values2.add(new Entry(3+width,3));
        values2.add(new Entry(4+width,4));

        LineDataSet set1 = new LineDataSet(values1,"line1");
        LineDataSet set2 = new LineDataSet(values2,"line2");
        LineData data = new LineData();
        data.addDataSet(set1);
        data.addDataSet(set2);
        linechart.setData(data);
        linechart.invalidate();
    }

    public void setPie(){
        List<PieEntry> values1 = new ArrayList<>();
        values1.add(new PieEntry(2,"一"));
        values1.add(new PieEntry(5,"二"));
        values1.add(new PieEntry(3,"三"));
        values1.add(new PieEntry(2,"四"));

        List<Integer> colors = new ArrayList<>();
        colors.add(Color.RED);
        colors.add(Color.GREEN);
        colors.add(Color.YELLOW);
        colors.add(Color.BLUE);

        PieDataSet set = new PieDataSet(values1,"pie1");
        set.setColors(colors);
        PieData data = new PieData();
        data.addDataSet(set);
        data.setValueTextSize(18);
        data.setValueFormatter(new PercentFormatter(piechart));
        piechart.setDrawHoleEnabled(false);
        piechart.setUsePercentValues(true);
        piechart.setData(data);
        piechart.invalidate();

    }
}