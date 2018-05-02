package com.sekolah.admin.system.ui_admin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.sekolah.admin.system.R;
import com.sekolah.admin.system.koneksi.config;
import com.valdesekamdem.library.mdtoast.MDToast;

import java.util.HashMap;
import java.util.Map;

public class add_kategori extends AppCompatActivity {
    EditText add_kaetori;
    Button btnadd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_kategori);
        Toolbar toolbar1 = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar1);
        toolbar1.setTitleTextColor(Color.WHITE);
        toolbar1.setSubtitleTextColor(Color.WHITE);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Tambah Kategori");
        add_kaetori = (EditText) findViewById(R.id.add_kategori);
        btnadd = (Button) findViewById(R.id.btnadd);
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simpan_data();
            }
        });
    }

    public void simpan_data() {
        if (add_kaetori.getText().toString().equals("")) {
            MDToast mdToast = MDToast.makeText(add_kategori.this, "nama kategori harap di isi", MDToast.LENGTH_LONG, MDToast.TYPE_WARNING);
            mdToast.show();
        } else {
            final ProgressDialog loading = ProgressDialog.show(add_kategori.this, "Loading Data", "Please wait...", false, false);
            StringRequest postRequest = new StringRequest(Request.Method.POST, config.ADD_KATEGORI,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            loading.dismiss();
                            MDToast mdToast = MDToast.makeText(add_kategori.this, response.toString(), MDToast.LENGTH_LONG, MDToast.TYPE_SUCCESS);
                            mdToast.show();
                            Intent in = new Intent(add_kategori.this, daftar_kategori.class);
                            startActivity(in);
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    loading.dismiss();
                    MDToast mdToast = MDToast.makeText(add_kategori.this, error.toString(), MDToast.LENGTH_LONG, MDToast.TYPE_ERROR);
                    mdToast.show();
//                    Toast.makeText(data_kepala_sekolah.this,
//                            error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put(config.KEY_kategori, add_kaetori.getText().toString());
                    return params;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(add_kategori.this);
            requestQueue.add(postRequest);
        }
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

