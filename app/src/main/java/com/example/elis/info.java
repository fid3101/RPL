package com.example.elis;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.protobuf.NullValue;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link info#newInstance} factory method to
 * create an instance of this fragment.
 */
public class info extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public info() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment info.
     */
    // TODO: Rename and change types and number of parameters
    public static info newInstance(String param1, String param2) {
        info fragment = new info();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    BarChart barChart;

    @SuppressLint("ResourceAsColor")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info, container, false);
        barChart = view.findViewById(R.id.charthariini);
        barChart.setDrawBarShadow(false);
        barChart.setDrawValueAboveBar(true);
        barChart.setMaxVisibleValueCount(4);
        barChart.setPinchZoom(false);
        barChart.setBackgroundColor(android.R.color.darker_gray);
        barChart.setGridBackgroundColor(android.R.color.white);
        barChart.getDescription().setEnabled(false);
        barChart.setExtraOffsets(5, 10 ,5, 5);
        barChart.setDragDecelerationFrictionCoef(0.95f);

        ArrayList<BarEntry> yValues = new ArrayList<>();
        yValues.add(new BarEntry(1,40f));
        yValues.add(new BarEntry(2,30f));
        yValues.add(new BarEntry(3,22f));
        yValues.add(new BarEntry(4,53f));
        yValues.add(new BarEntry(5,49f));
        yValues.add(new BarEntry(6,37f));

        BarDataSet dataSet = new BarDataSet(yValues,"asal");
        dataSet.setBarShadowColor(android.R.color.background_dark);
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);

        BarData data = new BarData(dataSet);
        data.setValueTextSize(10f);
        data.setValueTextColor(android.R.color.white);
        data.setBarWidth(0.7f);


        barChart.setData(data);


        return view;


    }
}
