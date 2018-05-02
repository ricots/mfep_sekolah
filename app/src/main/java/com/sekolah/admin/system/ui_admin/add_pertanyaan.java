package com.sekolah.admin.system.ui_admin;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.sekolah.admin.system.R;
import com.sekolah.admin.system.koneksi.config;
import com.valdesekamdem.library.mdtoast.MDToast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class add_pertanyaan extends AppCompatActivity {
    EditText add_tanya,bobot;
    Button btnaddtanya;
    TextView idkat,persen;
    MaterialSpinner spin_kat;
    private JSONArray result;
    private ArrayList<String> daftar;
    RequestQueue RequestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pertanyaan);
        Toolbar toolbar1 = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar1);
        toolbar1.setTitleTextColor(Color.WHITE);
        toolbar1.setSubtitleTextColor(Color.WHITE);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Tambah Pertanyaan");
        daftar = new ArrayList<String>();
        RequestQueue = Volley.newRequestQueue(add_pertanyaan.this);
        idkat = (TextView) findViewById(R.id.idkat);
        persen = (TextView) findViewById(R.id.persen);
        spin_kat = (MaterialSpinner) findViewById(R.id.spin_kat);
        add_tanya = (EditText) findViewById(R.id.add_tanya);
        bobot = (EditText) findViewById(R.id.add_bobot);
        btnaddtanya = (Button) findViewById(R.id.btnaddtanya);
        btnaddtanya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simpan_data();
            }
        });

        spin_kat.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner materialSpinner, int position, long l, Object o) {
                idkat.setText(getid_kat(position));
            }
        });

        getData();

        //persen.setText( String.format( "Value of a: %.2f", bobot.getText().toString() ) );
    }

    public void simpan_data() {
        if (add_tanya.getText().toString().equals("")) {
            MDToast mdToast = MDToast.makeText(add_pertanyaan.this, "pertanyaan harap di isi", MDToast.LENGTH_LONG, MDToast.TYPE_WARNING);
            mdToast.show();
        }else if (bobot.getText().toString().equals("")) {
                MDToast mdToast = MDToast.makeText(add_pertanyaan.this, "bobot harap di isi", MDToast.LENGTH_LONG, MDToast.TYPE_WARNING);
                mdToast.show();
        } else {
            final ProgressDialog loading = ProgressDialog.show(add_pertanyaan.this, "Loading Data", "Please wait...", false, false);
            StringRequest postRequest = new StringRequest(Request.Method.POST, config.ADD_TANYA,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            loading.dismiss();
                            MDToast mdToast = MDToast.makeText(add_pertanyaan.this, response.toString(), MDToast.LENGTH_LONG, MDToast.TYPE_SUCCESS);
                            mdToast.show();
//                            Intent in = new Intent(add_pertanyaan.this, daftar_pertanyaan.class);
//                            startActivity(in);
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    loading.dismiss();
                    MDToast mdToast = MDToast.makeText(add_pertanyaan.this, error.toString(), MDToast.LENGTH_LONG, MDToast.TYPE_ERROR);
                    mdToast.show();
//                    Toast.makeText(data_kepala_sekolah.this,
//                            error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put(config.KEY_id_kategori, idkat.getText().toString());
                    params.put(config.KEY_pertanyaan, add_tanya.getText().toString());
                    params.put(config.KEY_bobot, bobot.getText().toString());
                    return params;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(add_pertanyaan.this);
            requestQueue.add(postRequest);
        }
    }

    private void getData(){
        //Creating a string request
        final ProgressDialog loading = ProgressDialog.show(add_pertanyaan.this,"Loading Data", "Please wait...",false,false);
        StringRequest stringRequest = new StringRequest(config.LIST_KATEGORI,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        loading.dismiss();
                        JSONObject j = null;
                        try {
                            //Parsing the fetched Json String to JSON Object
                            j = new JSONObject(response);

                            result = j.getJSONArray("list_kategori");

                            getkat(result);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        //Creating a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(add_pertanyaan.this);

        //Adding request to the queue
        requestQueue.add(stringRequest);
        stringRequest.setRetryPolicy(new RetryPolicy() {
            @Override
            public int getCurrentTimeout() {
                return 50000;
            }

            @Override
            public int getCurrentRetryCount() {
                return 50000;
            }

            @Override
            public void retry(VolleyError error) throws VolleyError {
                loading.dismiss();
                MDToast mdToast = MDToast.makeText(add_pertanyaan.this, error.toString(), MDToast.LENGTH_LONG, MDToast.TYPE_ERROR);
                mdToast.show();
            }
        });
    }

    private void getkat(JSONArray j){
        for(int i=0;i<j.length();i++){
            try {
                JSONObject json = j.getJSONObject(i);

                daftar.add(json.getString(config.KEY_kategori));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        spin_kat.setAdapter(new ArrayAdapter<String>(add_pertanyaan.this, android.R.layout.simple_spinner_dropdown_item, daftar));
    }

    private String getid_kat(int position){
        String id_kat="";
        try {
            JSONObject json = result.getJSONObject(position);
            id_kat = json.getString(config.KEY_id_kategori);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return id_kat;
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        menu.findItem(R.id.load).setVisible(false);
        return true;
    }
}
