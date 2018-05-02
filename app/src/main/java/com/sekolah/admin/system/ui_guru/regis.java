package com.sekolah.admin.system.ui_guru;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.sekolah.admin.system.R;
import com.sekolah.admin.system.koneksi.config;
import com.sekolah.admin.system.login;
import com.valdesekamdem.library.mdtoast.MDToast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class regis extends AppCompatActivity {
    EditText nip_guru,nuptk,nrg,nama,tempat_lahir,tgl_lahir,masa_kerja,almt,pass_guru;
    MaterialSpinner pendidikan,spesialis,jk;
    Button btn_regis;
    String angka,angka1;
    private SimpleDateFormat dateFormatter;
    private DatePickerDialog fromDatePickerDialog;
    private DatePickerDialog toDatePickerDialog;
    Spinner tgl_mulai,thn_nilai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regis);
        dateFormatter = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        nip_guru = (EditText) findViewById(R.id.nip_guru);
        nrg = (EditText) findViewById(R.id.nrg);
        tempat_lahir = (EditText) findViewById(R.id.tempat_lahir);

        tgl_lahir = (EditText) findViewById(R.id.tgl_lahir);
        tgl_lahir.setInputType(InputType.TYPE_NULL);
        setDateTimeField();

        masa_kerja = (EditText) findViewById(R.id.masa_kerja);
        almt = (EditText) findViewById(R.id.almt);
        nuptk = (EditText) findViewById(R.id.nuptk);
        nama = (EditText) findViewById(R.id.nama);
        pass_guru = (EditText) findViewById(R.id.pass_guru);
        pendidikan = (MaterialSpinner) findViewById(R.id.pendidikan);
        spesialis = (MaterialSpinner) findViewById(R.id.spesialis);
        thn_nilai = (Spinner) findViewById(R.id.thn_nilai);
        tgl_mulai = (Spinner) findViewById(R.id.tgl_mulai);
        jk = (MaterialSpinner) findViewById(R.id.jk);
        btn_regis = (Button) findViewById(R.id.btn_regis);

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

        btn_regis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog loading = ProgressDialog.show(regis.this, "Loading Data", "Please wait...", false, false);
                StringRequest postRequest = new StringRequest(Request.Method.POST, config.REGIS,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                loading.dismiss();
                                MDToast mdToast = MDToast.makeText(regis.this, response.toString(), MDToast.LENGTH_LONG, MDToast.TYPE_SUCCESS);
                            Intent in = new Intent(regis.this, login.class);
                            startActivity(in);
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        loading.dismiss();
                        MDToast mdToast = MDToast.makeText(regis.this, error.toString(), MDToast.LENGTH_LONG, MDToast.TYPE_ERROR);
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
                RequestQueue requestQueue = Volley.newRequestQueue(regis.this);
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
                //newDate.set(year, monthOfYear, dayOfMonth);
                newDate.set(Calendar.YEAR, year);
                newDate.set(Calendar.MONTH, monthOfYear);
                newDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);
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
}
