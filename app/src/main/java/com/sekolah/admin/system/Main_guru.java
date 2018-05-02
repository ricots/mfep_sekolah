package com.sekolah.admin.system;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.sekolah.admin.system.koneksi.config;
import com.sekolah.admin.system.oop.Item;
import com.sekolah.admin.system.ui_admin.list_penilaian;
import com.sekolah.admin.system.ui_guru.data_guru;
import com.sekolah.admin.system.ui_guru.hasil_penilaian;
import com.valdesekamdem.library.mdtoast.MDToast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Main_guru extends AppCompatActivity {
    SharedPreferences sp;
    SharedPreferences.Editor spe;
    LinearLayout guru, hasil, btn_data_guru, keluar;
    TextView log_guru;
    RequestQueue requestQueue;
    List<Item> datanya;
    String welcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_guru);
        log_guru = (TextView) findViewById(R.id.log_guru);
        sp = getSharedPreferences(config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        welcome = sp.getString(config.nipkepsek_SHARED_PREF, "Not Available");
        MDToast mdToast = MDToast.makeText(this, welcome, MDToast.LENGTH_LONG, MDToast.TYPE_ERROR);
        mdToast.show();
        guru = (LinearLayout) findViewById(R.id.btn_score);
        guru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main_guru.this, hasil_penilaian.class);
                startActivity(intent);
            }
        });

        btn_data_guru = (LinearLayout) findViewById(R.id.btn_data_guru);
        btn_data_guru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main_guru.this, data_guru.class);
                startActivity(intent);
            }
        });

        keluar = (LinearLayout) findViewById(R.id.btn_keluar);
        keluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Main_guru.this);
                alertDialogBuilder.setMessage("apakah anda yakin ingin keluar ?");
                alertDialogBuilder.setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {

                                //Getting out sharedpreferences
                                SharedPreferences preferences = getSharedPreferences(config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
                                //Getting editor
                                SharedPreferences.Editor editor = preferences.edit();

                                //Puting the value false for loggedin
                                editor.putBoolean(config.LOGGEDIN_SHARED_PREF, false);

                                //Putting blank value to email
                                editor.putString(config.EMAIL_SHARED_PREF, "");

                                //Saving the sharedpreferences
                                editor.commit();

                                //Starting login activity
                                Intent intent = new Intent(Main_guru.this, login.class);
                                startActivity(intent);
                                finish();
                            }
                        });

                alertDialogBuilder.setNegativeButton("No",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {

                            }
                        });

                //Showing the alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });

        datanya = new ArrayList<Item>();
        requestQueue = Volley.newRequestQueue(this);
        getData();
    }

    public void getData() {
        final ProgressDialog loading = ProgressDialog.show(Main_guru.this,"Loading Data", "Please wait...",false,false);
        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.GET, config.DETAIL_GURU + welcome,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        loading.dismiss();
                        Log.d("hasilnya ", response.toString());
                        try {

                            JSONArray data = response.getJSONArray("guru");

                            for (int a = 0; a < data.length(); a++) {
                                JSONObject json = data.getJSONObject(a);
                                log_guru.setText(json.getString(config.KEY_nama_guru));
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
                        MDToast mdToast = MDToast.makeText(Main_guru.this, error.getMessage(), MDToast.LENGTH_LONG, MDToast.TYPE_WARNING);
                        mdToast.show();
                    }
                });

        requestQueue.add(jsonRequest);
    }
}
