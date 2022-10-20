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
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.interfaces.datasets.IPieDataSet;

import java.util.ArrayList;
import java.util.List;

import cn.itcast.smartcity2.R;

public class FenXiActivity extends AppCompatActivity {

    private BarChart bar_chart;
    private LineChart line_chart;
    private PieChart pie_chart;
    private float width = 0.2f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fen_xi);
        bar_chart =  findViewById(R.id.bar_chart);
        line_chart =  findViewById(R.id.line_chart);
        pie_chart =  findViewById(R.id.pie_chart);
        setLine();
        setBar();
        setPie();
    }

    private void setBar() {
        List<BarEntry> values1 = new ArrayList<>();
        values1.add(new BarEntry(1,1));
        values1.add(new BarEntry(2,2));
        values1.add(new BarEntry(3,3));
        values1.add(new BarEntry(4,4));

        List<BarEntry> values2 = new ArrayList<>();
        values2.add(new BarEntry(1+width,2));
        values2.add(new BarEntry(2+width,3));
        values2.add(new BarEntry(3+width,4));
        values2.add(new BarEntry(4+width,5));

        BarDataSet set1 = new BarDataSet(values1,"A");
        BarDataSet set2 = new BarDataSet(values2,"B");
        set1.setValueTextColor(Color.BLUE);
        set1.setColor(Color.BLACK);
        set2.setValueTextColor(Color.BLACK);
        set2.setColor(Color.RED);

        BarData data = new BarData();
        data.addDataSet(set1);
        data.addDataSet(set2);
        data.setValueTextSize(10f);
        data.setBarWidth(0.2f);
        bar_chart.setData(data);
        bar_chart.invalidate();
    }

    public void setLine(){

        List<Entry> values1 = new ArrayList<>();
        values1.add(new Entry(1,1));
        values1.add(new Entry(2,2));
        values1.add(new Entry(3,3));
        values1.add(new Entry(4,4));
        LineDataSet set1 = new LineDataSet(values1,"Data1");


        List<Entry> values2 = new ArrayList<>();
        values2.add(new Entry(1+width,1));
        values2.add(new Entry(2+width,2));
        values2.add(new Entry(3+width,3));
        values2.add(new Entry(4+width,4));
        LineDataSet set2 = new LineDataSet(values2,"Data2");

        LineData data = new LineData();
        data.addDataSet(set1);
        data.addDataSet(set2);
        line_chart.setData(data);
        line_chart.invalidate();

    }

    public void setPie(){
        List<PieEntry> values1 = new ArrayList<>();
        values1.add(new PieEntry(1,"一"));
        values1.add(new PieEntry(2,"二"));
        values1.add(new PieEntry(3,"三"));
        values1.add(new PieEntry(4,"四"));

        List<Integer> colors = new ArrayList<>();
        colors.add(Color.BLUE);
        colors.add(Color.YELLOW);
        colors.add(Color.RED);
        colors.add(Color.GREEN);

        PieDataSet dataSet = new PieDataSet(values1,"Data3");
        dataSet.setColors(colors);
        PieData pieData = new PieData();
        pieData.addDataSet(dataSet);
        pieData.setValueTextSize(18);
        pieData.setValueFormatter(new PercentFormatter(pie_chart));
        pie_chart.setData(pieData);
        pie_chart.setUsePercentValues(true);
        pie_chart.setDrawHoleEnabled(false);
        pie_chart.invalidate();
    }

}