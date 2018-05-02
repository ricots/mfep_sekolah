package com.sekolah.admin.system.ui_guru;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.sekolah.admin.system.Main_guru;
import com.sekolah.admin.system.R;
import com.sekolah.admin.system.koneksi.config;
import com.valdesekamdem.library.mdtoast.MDToast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class data_guru extends AppCompatActivity {
    EditText nip_guru,nuptk,nrg,nama,tempat_lahir,tgl_lahir,masa_kerja,almt,pass_guru;
    MaterialSpinner pendidikan,spesialis,jk;
    private SimpleDateFormat dateFormatter;
    private DatePickerDialog fromDatePickerDialog;
    private DatePickerDialog toDatePickerDialog;
    Button btn_data_upd;
    Spinner tgl_mulai,thn_nilai;
    SharedPreferences sp;
    SharedPreferences.Editor spe;
    String welcome;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_guru);
        sp = getSharedPreferences(config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        welcome = sp.getString(config.nipkepsek_SHARED_PREF, "Not Available");
        MDToast mdToast = MDToast.makeText(this, welcome, MDToast.LENGTH_LONG, MDToast.TYPE_ERROR);
        mdToast.show();
        dateFormatter = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        nip_guru = (EditText) findViewById(R.id.upd_nip_guru);
        nrg = (EditText) findViewById(R.id.upd_nrg);
        tempat_lahir = (EditText) findViewById(R.id.upd_tempat_lahir);

        tgl_lahir = (EditText) findViewById(R.id.upd_tgl_lahir);
        tgl_lahir.setInputType(InputType.TYPE_NULL);
        setDateTimeField();

        masa_kerja = (EditText) findViewById(R.id.upd_masa_kerja);
        almt = (EditText) findViewById(R.id.upd_almt);
        nuptk = (EditText) findViewById(R.id.upd_nuptk);
        nama = (EditText) findViewById(R.id.upd_nama);
        pass_guru = (EditText) findViewById(R.id.upd_pass_guru);
        pendidikan = (MaterialSpinner) findViewById(R.id.upd_pendidikan);
        spesialis = (MaterialSpinner) findViewById(R.id.upd_spesialis);
        thn_nilai = (Spinner) findViewById(R.id.upd_thn_nilai);
        tgl_mulai = (Spinner) findViewById(R.id.upd_tgl_mulai);
        jk = (MaterialSpinner) findViewById(R.id.upd_jk);
        btn_data_upd = (Button) findViewById(R.id.btn_data_upd);

        String[] ITEMS_pendidikan = {"Pilih","SMA", "S1", "S2"};
        ArrayAdapter<String> adapter_pendidikan = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, ITEMS_pendidikan);
        adapter_pendidikan.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        pendidikan.setAdapter(adapter_pendidikan);

        String[] ITEMS_spesialis = {"Pilih","BAHASA", "MATEMATIKA", "OLARAGA"};
        ArrayAdapter<String> adapter_spesialis = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, ITEMS_spesialis);
        adapter_pendidikan.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spesialis.setAdapter(adapter_spesialis);

        ArrayList<String> years = new ArrayList<String>();
        //int thisYear = Calendar.getInstance().get(Calendar.YEAR);
        for (int i = 1990; i <= 2017; i++) {
            years.add(Integer.toString(i));
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.layout_spinner, R.id.weekofday, years);
        tgl_mulai.setAdapter(adapter);
        thn_nilai.setAdapter(adapter);

        String[] ITEMS_jk = {"Pilih","LAKI LAKI","PEREMPUAN"};
        ArrayAdapter<String> adapter_jk= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, ITEMS_jk);
        adapter_jk.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        jk.setAdapter(adapter_jk);

        requestQueue = Volley.newRequestQueue(this);
        getData();

        btn_data_upd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog loading = ProgressDialog.show(data_guru.this, "Update Data", "Please wait...", false, false);
                StringRequest postRequest = new StringRequest(Request.Method.POST, config.UPDDATE_DATA_GURU,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                loading.dismiss();
                                MDToast mdToast = MDToast.makeText(data_guru.this, response.toString(), MDToast.LENGTH_LONG, MDToast.TYPE_SUCCESS);
                                mdToast.show();
                                Intent in =  new Intent(data_guru.this,Main_guru.class);
                                startActivity(in);
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        loading.dismiss();
                        MDToast mdToast = MDToast.makeText(data_guru.this, error.toString(), MDToast.LENGTH_LONG, MDToast.TYPE_ERROR);
                        mdToast.show();
//                    Toast.makeText(data_kepala_sekolah.this,
//                            error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put(config.KEY_nip_guru, nip_guru.getText().toString());
                        params.put(config.KEY_nuptk, nuptk.getText().toString());
                        params.put(config.KEY_nrg, nrg.getText().toString());
                        params.put(config.KEY_NAMA, nama.getText().toString());
                        params.put(config.KEY_tmpt_lahir, tempat_lahir.getText().toString());
                        params.put(config.KEY_tgl_lahir, tgl_lahir.getText().toString());
                        params.put(config.KEY_masa_kerja, masa_kerja.getText().toString());
                        params.put(config.KEY_alamat, almt.getText().toString());
                        params.put(config.KEY_pass_guru, pass_guru.getText().toString());
                        params.put(config.KEY_pendidikan_akhir, pendidikan.getText().toString());
                        params.put(config.KEY_spesialis, spesialis.getText().toString());
                        params.put(config.KEY_thn_penialian, thn_nilai.getSelectedItem().toString());
                        params.put(config.KEY_jk, jk.getText().toString());
                        params.put(config.KEY_mulai_kerja, tgl_mulai.getSelectedItem().toString());
                        return params;
                    }
                };
                RequestQueue requestQueue = Volley.newRequestQueue(data_guru.this);
                requestQueue.add(postRequest);
            }
        });
    }

    private void setDateTimeField() {
        tgl_lahir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toDatePickerDialog.show();
            }
        });
        Calendar newCalendar = Calendar.getInstance();
        fromDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                tgl_lahir.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        toDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                tgl_lahir.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
    }

    public void getData() {
        final ProgressDialog loading = ProgressDialog.show(data_guru.this,"Loading Data", "Please wait...",false,false);
        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.GET, config.DATA_GURU + welcome,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        loading.dismiss();
                        Log.d("hasilnya ", response.toString());
                        try {

                            JSONArray data = response.getJSONArray("list_guru");

                            for (int a = 0; a < data.length(); a++) {
                                JSONObject json = data.getJSONObject(a);
                                nip_guru.setText(json.getString(config.KEY_nip_guru));
                                nuptk.setText(json.getString(config.KEY_nuptk));
                                nrg.setText(json.getString(config.KEY_nrg));
                                nama.setText(json.getString(config.KEY_nama_guru));
                                tempat_lahir.setText(json.getString(config.KEY_tmpt_lahir));
                                tgl_lahir.setText(json.getString(config.KEY_tgl_lahir));
                                masa_kerja.setText(json.getString(config.KEY_masa_kerja));
                                almt.setText(json.getString(config.KEY_alamat));
                                pass_guru.setText(json.getString(config.KEY_pass_guru));
//                                pendidikan.setText(config.KEY_pendidikan_akhir);
//                                spesialis.setText(config.KEY_spesialis);
//                                jk.setText(config.KEY_jk);
//                                tgl_mulai.setSelection(Integer.parseInt(config.KEY_mulai_kerja));
//                                thn_nilai.setSelection(Integer.parseInt(config.KEY_thn_penialian));
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
                        MDToast mdToast = MDToast.makeText(data_guru.this, error.getMessage(), MDToast.LENGTH_LONG, MDToast.TYPE_WARNING);
                        mdToast.show();
                    }
                });

        requestQueue.add(jsonRequest);
    }
}
