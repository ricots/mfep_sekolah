package com.sekolah.admin.system.ui_admin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.sekolah.admin.system.R;
import com.sekolah.admin.system.adapter.adapter_admin.adapter_data_kategori;
import com.sekolah.admin.system.adapter.adapter_kepsek.adapter_list_kategori;
import com.sekolah.admin.system.koneksi.config;
import com.sekolah.admin.system.oop.Item;
import com.valdesekamdem.library.mdtoast.MDToast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class daftar_kategori extends AppCompatActivity {
    RecyclerView list_daftar;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adp_daftar;
    RequestQueue requestQueue;
    List<Item> datanya;
    String nip;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_kategori);
//        bundle = getIntent().getExtras();
//        String txtkirim = bundle.getString("idkirim");
//
//        if (txtkirim.equals("dari_guru")){
//            bundle = getIntent().getExtras();
//            nip = bundle.getString("nip");
//            MDToast mdToast = MDToast.makeText(daftar_kategori.this, nip, MDToast.LENGTH_LONG, MDToast.TYPE_WARNING);
//            mdToast.show();
//
//        }

        list_daftar = (RecyclerView) findViewById(R.id.recyle_kat);
        list_daftar.setHasFixedSize(true);

        Toolbar toolbar1 = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar1);
        toolbar1.setTitleTextColor(Color.WHITE);
        toolbar1.setSubtitleTextColor(Color.WHITE);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Daftar kategori");

//        layoutManager = new LinearLayoutManager(this);
//        list_daftar.setLayoutManager(layoutManager);

        layoutManager = new GridLayoutManager(this, 2);
        list_daftar.setLayoutManager(layoutManager);

        datanya = new ArrayList<Item>();
        requestQueue = Volley.newRequestQueue(this);
        getData();
        adp_daftar = new adapter_data_kategori(datanya, this);
        list_daftar.setAdapter(adp_daftar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(daftar_kategori.this,add_kategori.class);
                startActivity(in);
            }
        });
    }

    public void getData() {
        final ProgressDialog loading = ProgressDialog.show(daftar_kategori.this,"Loading Data", "Please wait...",false,false);
        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.GET, config.LIST_KATEGORI,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        loading.dismiss();
                        Log.d("hasilnya ", response.toString());
                        try {

                            JSONArray data = response.getJSONArray("list_kategori");

                            for (int a = 0; a < data.length(); a++) {
                                JSONObject json = data.getJSONObject(a);
                                Item item = new Item();
                                item.setId_kategori(json.getString(config.KEY_id_kategori));
                                item.setKategori(json.getString(config.KEY_kategori));
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
                        MDToast mdToast = MDToast.makeText(daftar_kategori.this, error.getMessage(), MDToast.LENGTH_LONG, MDToast.TYPE_WARNING);
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
                MDToast mdToast = MDToast.makeText(daftar_kategori.this, "cek koneksi internet", MDToast.LENGTH_LONG, MDToast.TYPE_WARNING);
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
