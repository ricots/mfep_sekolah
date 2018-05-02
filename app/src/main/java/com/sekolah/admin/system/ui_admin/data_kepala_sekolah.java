package com.sekolah.admin.system.ui_admin;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.sekolah.admin.system.R;
import com.sekolah.admin.system.koneksi.config;
import com.valdesekamdem.library.mdtoast.MDToast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class data_kepala_sekolah extends AppCompatActivity {
    EditText nik_kepsek,nama_kepsek, alamat_kepsek, telp_kepsek, pass_kepsek;
    Button btnkepsek;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_kepala_sekolah);
        Toolbar toolbar1 = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar1);
        toolbar1.setTitleTextColor(Color.WHITE);
        toolbar1.setSubtitleTextColor(Color.WHITE);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Data Kepala Sekolah");

        requestQueue = Volley.newRequestQueue(this);

        nik_kepsek = (EditText) findViewById(R.id.nik_kepsek);
        nama_kepsek = (EditText) findViewById(R.id.nama_kepsek);
        alamat_kepsek = (EditText) findViewById(R.id.alamat_kepsek);
        telp_kepsek = (EditText) findViewById(R.id.telp_kepsek);
        pass_kepsek = (EditText) findViewById(R.id.pas_kepsek);
        btnkepsek = (Button) findViewById(R.id.btnkepsek);
        btnkepsek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btnkepsek.getText().toString().equalsIgnoreCase("tampilkan data")){
                    getData();
                    btnkepsek.setText("Update Data");
                }else{
                    simpan_data();
                }
            }
        });
    }

    public void getData() {
        final ProgressDialog loading = ProgressDialog.show(data_kepala_sekolah.this,"Loading Data", "Please wait...",false,false);
        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, config.LIST_KEPSEK,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        loading.dismiss();
                        Log.d("hasilnya ", response.toString());
                        try {

                            JSONArray data = response.getJSONArray("list_kepsek");

                            for (int a = 0; a < data.length(); a++) {
                                JSONObject json = data.getJSONObject(a);
                                nik_kepsek.setText(json.getString(config.KEY_nip_kepsek));
                                nama_kepsek.setText(json.getString(config.KEY_NAMA));
                                alamat_kepsek.setText(json.getString(config.KEY_alamat));
                                telp_kepsek.setText(json.getString(config.KEY_telp));
                                pass_kepsek.setText(json.getString(config.KEY_pass));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            System.out.println("ini kesalahannya " + e.getMessage());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        loading.dismiss();
                        Log.d("ini kesalahannya",error.toString());
                        System.out.println("ini kesalahannya " + error.getMessage());
                    }
                });

        requestQueue.add(jsonRequest);
    }

    public void simpan_data(){
        final ProgressDialog loading = ProgressDialog.show(data_kepala_sekolah.this,"Loading Data", "Please wait...",false,false);
            StringRequest postRequest = new StringRequest(Request.Method.POST, config.UPDATE_KEPSEK,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            loading.dismiss();
                            MDToast mdToast = MDToast.makeText(data_kepala_sekolah.this, response.toString(), MDToast.LENGTH_LONG, MDToast.TYPE_SUCCESS);
                            mdToast.show();
                            //Toast.makeText(data_kepala_sekolah.this, response.toString(), Toast.LENGTH_LONG).show();
                            Log.d("laporan ", response.toString());
                            btnkepsek.setText("Tampilkan Data");
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    loading.dismiss();
                    MDToast mdToast = MDToast.makeText(data_kepala_sekolah.this, error.toString(), MDToast.LENGTH_LONG, MDToast.TYPE_ERROR);
                    mdToast.show();
//                    Toast.makeText(data_kepala_sekolah.this,
//                            error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put(config.KEY_nip_kepsek, nik_kepsek.getText().toString());
                    params.put(config.KEY_NAMA, nama_kepsek.getText().toString());
                    params.put(config.KEY_alamat, alamat_kepsek.getText().toString());
                    params.put(config.KEY_telp, telp_kepsek.getText().toString());
                    params.put(config.KEY_PASSWORD, pass_kepsek.getText().toString());
                    return params;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(data_kepala_sekolah.this);
            requestQueue.add(postRequest);
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
