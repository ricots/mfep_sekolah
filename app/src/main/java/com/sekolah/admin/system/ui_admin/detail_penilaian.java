package com.sekolah.admin.system.ui_admin;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.sekolah.admin.system.R;
import com.sekolah.admin.system.adapter.adapter_admin.adapter_detail_pertanyaan;
import com.sekolah.admin.system.koneksi.config;
import com.sekolah.admin.system.oop.Item;
import com.valdesekamdem.library.mdtoast.MDToast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class detail_penilaian extends AppCompatActivity {
    RecyclerView list_daftar;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adp_daftar;
    RequestQueue requestQueue;
    List<Item> datanya;
    String nip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_penilaian);
        Bundle bundle = getIntent().getExtras();
        nip = bundle.getString("nip");

        Toolbar toolbar1 = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar1);
        toolbar1.setTitleTextColor(Color.WHITE);
        toolbar1.setSubtitleTextColor(Color.WHITE);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("hasil penilaian");

        list_daftar = (RecyclerView) findViewById(R.id.recyle_detail_nilai);
        list_daftar.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        list_daftar.setLayoutManager(layoutManager);

        datanya = new ArrayList<Item>();
        requestQueue = Volley.newRequestQueue(this);
        getData();
        adp_daftar = new adapter_detail_pertanyaan(datanya, this);
        list_daftar.setAdapter(adp_daftar);
    }

    public void getData() {
        final ProgressDialog loading = ProgressDialog.show(detail_penilaian.this,"Loading Data", "Please wait...",false,false);
        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.GET, config.LIST_DETAIL_PENILAIAN + nip,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        loading.dismiss();
                        Log.d("hasilnya ", response.toString());
                        try {

                            JSONArray data = response.getJSONArray("list_detail_penilaian");

                            for (int a = 0; a < data.length(); a++) {
                                JSONObject json = data.getJSONObject(a);
                                Item item = new Item();
                                item.setId_pertanyaan(json.getString(config.KEY_id_pertanyaan));
                                item.setPertanyaan(json.getString(config.KEY_pertanyaan));
                                item.setSemester(json.getString(config.KEY_SEMSETER));
                                item.setKeterangan(json.getString(config.KEY_KET));
                                item.setTotal_skor(json.getString(config.KEY_toatal_skor));
                                datanya.add(item);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            System.out.println("ini kesalahannya " + e.getMessage());
                        }
                        adp_daftar.notifyDataSetChanged();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        loading.dismiss();
                        Log.d("ini kesalahannya",error.toString());
                        System.out.println("ini kesalahannya " + error.getMessage());
                        MDToast mdToast = MDToast.makeText(detail_penilaian.this, error.getMessage(), MDToast.LENGTH_LONG, MDToast.TYPE_WARNING);
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

        if (item.getItemId() == R.id.load){
            //if(!list_detail.isEmpty()){
            datanya.clear();
            datanya.isEmpty();
            getData();

        }else{
            MDToast mdToast = MDToast.makeText(detail_penilaian.this, "cek koneksi internet", MDToast.LENGTH_LONG, MDToast.TYPE_WARNING);
            mdToast.show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}
