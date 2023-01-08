package com.example.tinytoes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Map;


public class ViewStats extends AppCompatActivity {

    LineChart lineChart,lineChart1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_stats);

        lineChart =(LineChart) findViewById(R.id.lineChart);
        LineDataSet lineDataSet = new LineDataSet(datavalues1(),"Weekly Stats");
        lineDataSet.setColors(ColorTemplate.rgb("#FFF"));
        ArrayList<ILineDataSet> dataSets= new ArrayList<>();
        dataSets.add(lineDataSet);

        LineData data = new LineData(dataSets);
        lineChart.setData(data);
        lineChart.invalidate();

        lineChart1 =(LineChart) findViewById(R.id.lineChart2);
        LineDataSet lineDataSet1 = new LineDataSet(datavalues2(),"Monthy Stats");
        lineDataSet1.setColors(ColorTemplate.rgb("#FFF"));
        ArrayList<ILineDataSet> dataSets1= new ArrayList<>();
        dataSets1.add(lineDataSet1);

        LineData data1 = new LineData(dataSets1);
        lineChart1.setData(data1);
        lineChart1.invalidate();

    }
    private ArrayList<Entry> datavalues1(){
        ArrayList<Entry> datavals = new ArrayList<Entry>();

        datavals.add(new Entry(0,20));
        datavals.add(new Entry(1,22));
        datavals.add(new Entry(2,17));
        datavals.add(new Entry(3,13));
        datavals.add(new Entry(4,10));
        datavals.add(new Entry(5,18));

        datavals.add(new Entry(6,12));
        datavals.add(new Entry(7,15));
        datavals.add(new Entry(8,12));
        datavals.add(new Entry(9,19));
        datavals.add(new Entry(10,15));
        datavals.add(new Entry(11,17));

        return datavals;
    }
    private ArrayList<Entry> datavalues2(){
        ArrayList<Entry> datavals = new ArrayList<Entry>();

        datavals.add(new Entry(0,19));
        datavals.add(new Entry(1,18));
        datavals.add(new Entry(2,16));
        datavals.add(new Entry(3,18));
        datavals.add(new Entry(4,17));
        datavals.add(new Entry(5,12));

        datavals.add(new Entry(6,15));
        datavals.add(new Entry(7,13));
        datavals.add(new Entry(8,15));
        datavals.add(new Entry(9,16));
        datavals.add(new Entry(10,14));
        datavals.add(new Entry(11,12));

        return datavals;
    }

}