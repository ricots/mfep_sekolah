package com.sekolah.admin.system.ui_kepsek;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.sekolah.admin.system.R;
import com.valdesekamdem.library.mdtoast.MDToast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class persentase extends AppCompatActivity {
    RequestQueue requestQueue;
    ArrayList<BarDataSet> yAxis;
    ArrayList<BarEntry> yValues;
    ArrayList<String> xAxis1;
    BarEntry values ;
    BarChart chart;
    BarData data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persentase);
        Toolbar toolbar1 = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar1);
        toolbar1.setTitleTextColor(Color.WHITE);
        toolbar1.setSubtitleTextColor(Color.WHITE);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Persentase");
        requestQueue = Volley.newRequestQueue(this);
        chart = (BarChart) findViewById(R.id.chart);
        getData();
//        PieChart pieChart = (PieChart) findViewById(R.id.piechart);
//        pieChart.setUsePercentValues(true);
//        ArrayList<Entry> yvalues = new ArrayList<Entry>();
//        yvalues.add(new Entry(8f, 0));
//        yvalues.add(new Entry(15f, 1));
//        yvalues.add(new Entry(12f, 2));
//        yvalues.add(new Entry(25f, 3));
//        yvalues.add(new Entry(23f, 4));
//        yvalues.add(new Entry(17f, 5));
//        PieDataSet dataSet = new PieDataSet(yvalues, "Tahun Ajaran");
//        ArrayList<String> xVals = new ArrayList<String>();
//        xVals.add("2015/ganjil");
//        xVals.add("2015/genap");
//        xVals.add("2016/ganjil");
//        xVals.add("2016/genap");
//        xVals.add("2017/ganjil");
//        xVals.add("2017/genap");
//        PieData data = new PieData(xVals, dataSet);
//        dataSet.setSelectionShift(5f);
//        data.setValueFormatter(new PercentFormatter());
//        pieChart.setData(data);
//        pieChart.setDescription("Persentase penilaian guru");
//        pieChart.setDrawHoleEnabled(true);
//        pieChart.setTransparentCircleRadius(58f);
//        pieChart.setHoleRadius(58f);
//        final int[] MY_COLORS = {Color.rgb(192,0,0), Color.rgb(255,0,0), Color.rgb(255,192,0),
//                Color.rgb(127,127,127), Color.rgb(146,208,80), Color.rgb(0,176,80), Color.rgb(79,129,189)};
//        ArrayList<Integer> colors = new ArrayList<Integer>();
//
//        for(int c: MY_COLORS) colors.add(c);
//        dataSet.setColors(colors);
//        data.setValueTextSize(13f);
//        data.setValueTextColor(Color.DKGRAY);
//        pieChart.animateXY(1400, 1400);
//        pieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
//            @Override
//            public void onValueSelected(Entry entry, int i, Highlight highlight) {
//                if (entry == null)
//                    return;
//                Log.i("VAL SELECTED",
//                        "Value: " + entry.getVal() + ", xIndex: " + entry.getXIndex()
//                                + ", DataSet index: " + i);
//            }
//
//            @Override
//            public void onNothingSelected() {
//
//            }
//        });
    }

    public void getData() {
        String url = "http://mydeveloper.id/sekolah/admin/list_penilaian.php";
        xAxis1 = new ArrayList<>();
        yAxis = null;
        yValues = new ArrayList<>();
        final ProgressDialog loading = ProgressDialog.show(persentase.this,"Loading Data", "Please wait...",false,false);
        JsonObjectRequest jsonRequest = new JsonObjectRequest( url,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        loading.dismiss();
                        Log.d("hasilnya ", response.toString());
                        try {

                            JSONArray datachart = response.getJSONArray("list_penilaian");

                            for (int a = 0; a < datachart.length(); a++) {
                                JSONObject json = datachart.getJSONObject(a);
                                String nip = json.getString("nip_guru");
                                String nama = json.getString("nama");
                                String total = json.getString("total");
                                xAxis1.add(nama);

                                values = new BarEntry(Float.valueOf(total),a);
                                yValues.add(values);

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();


                        }





                        BarDataSet barDataSet1 = new BarDataSet(yValues, "penilaian guru");
                        barDataSet1.setColor(Color.rgb(0, 82, 159));
                        yAxis = new ArrayList<>();
                        yAxis.add(barDataSet1);
                        String names[]= xAxis1.toArray(new String[xAxis1.size()]);
                        data = new BarData(names,yAxis);
                        chart.setData(data);
                        chart.setDescription("");
                        chart.animateXY(2000, 2000);
                        chart.invalidate();
                        loading.dismiss();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        loading.dismiss();
                        Log.d("ini kesalahannya",error.toString());
                        System.out.println("ini kesalahannya " + error.getMessage());
                        MDToast mdToast = MDToast.makeText(persentase.this, error.getMessage(), MDToast.LENGTH_LONG, MDToast.TYPE_WARNING);
                        mdToast.show();
                    }
                });

        requestQueue.add(jsonRequest);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
