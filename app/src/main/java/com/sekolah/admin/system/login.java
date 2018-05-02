package com.sekolah.admin.system;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.sekolah.admin.system.koneksi.config;
import com.sekolah.admin.system.ui_guru.regis;
import com.valdesekamdem.library.mdtoast.MDToast;

import java.util.HashMap;
import java.util.Map;


public class login extends AppCompatActivity {
    EditText user, pass;
    Button btn_login;
    private boolean loggedIn = false;
    SharedPreferences sp;
    SharedPreferences.Editor spe;
    MaterialSpinner spinner;
    TextView txtregis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        user = (EditText) findViewById(R.id.username);
        pass = (EditText) findViewById(R.id.pass);
        txtregis = (TextView) findViewById(R.id.txtregis);
        String[] ITEMS = {"Pilih","Admin", "Wakil kepsek", "Guru"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, ITEMS);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner = (MaterialSpinner) findViewById(R.id.level);
        spinner.setAdapter(adapter);

        sp = this.getSharedPreferences("isi data", 0);
        spe = sp.edit();

        txtregis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(login.this, regis.class);
                startActivity(intent);
            }
        });

        spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner materialSpinner, int i, long l, Object o) {
                if(spinner.getText().toString().equalsIgnoreCase("Pilih")){
                    txtregis.setVisibility(View.GONE);
                }else if(spinner.getText().toString().equalsIgnoreCase("Admin")){
                    txtregis.setVisibility(View.GONE);
                }else if(spinner.getText().toString().equalsIgnoreCase("Wakil kepsek")){
                    txtregis.setVisibility(View.GONE);
                }else{
                    txtregis.setVisibility(View.VISIBLE);
                }
            }
        });

        btn_login = (Button) findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(spinner.getSelectedIndex()==0){
                    Toast.makeText(getApplicationContext(),"silahkan pilih level",Toast.LENGTH_LONG).show();
                }else
                if (user.getText().toString().equals("")){
                    MDToast mdToast = MDToast.makeText(getApplicationContext(), "username harap di isi", MDToast.LENGTH_LONG, MDToast.TYPE_ERROR);
                    mdToast.show();
                    //Toast.makeText(getApplicationContext(),"username harap di isi",Toast.LENGTH_LONG).show();
                }else if (pass.getText().toString().equals("")){
                    MDToast mdToast = MDToast.makeText(getApplicationContext(), "password harap di isi", MDToast.LENGTH_LONG, MDToast.TYPE_ERROR);
                    mdToast.show();
                }else if ((spinner.getSelectedIndex()==1) && (user.getText().toString().equalsIgnoreCase("admin")
                        && (pass.getText().toString().equals("admin")))) {
                    MDToast mdToast = MDToast.makeText(getApplicationContext(), "login sukses", MDToast.LENGTH_LONG, MDToast.TYPE_SUCCESS);
                    mdToast.show();
                    Toast.makeText(getApplicationContext(), "login sukses", Toast.LENGTH_LONG).show();
                    Intent in = new Intent(login.this, MainActivity.class);
                    sp = login.this.getSharedPreferences(config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
                    spe = sp.edit();
                    spe.putBoolean(config.LOGGEDIN_SHARED_PREF, true);
                    spe.putString(config.EMAIL_SHARED_PREF, user.getText().toString());
                    spe.commit();
                    startActivity(in);
                }else if (spinner.getSelectedIndex() ==2){
                    login_kepsek();
                }else if (spinner.getSelectedIndex() ==3) {
                    login_guru();
                }else{
                    MDToast mdToast = MDToast.makeText(getApplicationContext(), "login gagal", MDToast.LENGTH_LONG, MDToast.TYPE_ERROR);
                    mdToast.show();
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences = getSharedPreferences(config.SHARED_PREF_NAME, Context.MODE_PRIVATE);

        loggedIn = sharedPreferences.getBoolean(config.LOGGEDIN_SHARED_PREF, false);

        if((loggedIn) && (spinner.getSelectedIndex()==1)){
            Intent intent = new Intent(login.this, MainActivity.class);
            startActivity(intent);
        }else if((loggedIn) && (spinner.getSelectedIndex()==2)){
            Intent intent = new Intent(login.this, Main_kepsek.class);
            startActivity(intent);
        }else if((loggedIn) && (spinner.getSelectedIndex()==3)){
            Intent intent = new Intent(login.this, Main_guru.class);
            startActivity(intent);
        }
    }

    private void login_kepsek() {
        //Getting values from edit texts
        final ProgressDialog loading = ProgressDialog.show(login.this,"Login", "Please wait...",false,false);
            //Creating a string request
            StringRequest stringRequest = new StringRequest(Request.Method.POST, config.LOGIN_KEPSEK,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            //If we are getting success from server
                            if (response.contains(config.LOGIN_SUCCESS)) {
                                loading.dismiss();
                                sp = login.this.getSharedPreferences(config.SHARED_PREF_NAME, Context.MODE_PRIVATE);

                                //Creating editor to store values to shared preferences
                                spe = sp.edit();

                                //Adding values to editor
                                spe.putBoolean(config.LOGGEDIN_SHARED_PREF, true);
                                spe.putString(config.nipkepsek_SHARED_PREF, user.getText().toString());

                                spe.commit();
                                Intent intent = new Intent(login.this, Main_kepsek.class);
                                startActivity(intent);

                            } else {
                                loading.dismiss();
                                MDToast mdToast = MDToast.makeText(getApplicationContext(), "Invalid username or password", MDToast.LENGTH_LONG, MDToast.TYPE_ERROR);
                                mdToast.show();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            //You can handle error here if you want
                            loading.dismiss();
                            MDToast mdToast = MDToast.makeText(getApplicationContext(), "The server unreachable", MDToast.LENGTH_LONG, MDToast.TYPE_ERROR);
                            mdToast.show();

                        }
                    }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    //Adding parameters to request
                    params.put(config.KEY_nip_kepsek, user.getText().toString());
                    params.put(config.KEY_PASSWORD, pass.getText().toString());

                    return params;
                }
            };

            Volley.newRequestQueue(this).add(stringRequest);
    }

    private void login_guru() {
        //Getting values from edit texts
        final ProgressDialog loading = ProgressDialog.show(login.this,"Login", "Please wait...",false,false);
        //Creating a string request
        StringRequest stringRequest = new StringRequest(Request.Method.POST, config.LOGIN_GURU,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        //If we are getting success from server
                        if (response.contains(config.LOGIN_SUCCESS)) {
                            loading.dismiss();
                            sp = login.this.getSharedPreferences(config.SHARED_PREF_NAME, Context.MODE_PRIVATE);

                            //Creating editor to store values to shared preferences
                            spe = sp.edit();

                            //Adding values to editor
                            spe.putBoolean(config.LOGGEDIN_SHARED_PREF, true);
                            spe.putString(config.nipkepsek_SHARED_PREF, user.getText().toString());

                            spe.commit();
                            Intent intent = new Intent(login.this, Main_guru.class);
                            startActivity(intent);

                        } else {
                            loading.dismiss();
                            MDToast mdToast = MDToast.makeText(getApplicationContext(), "Invalid username or password", MDToast.LENGTH_LONG, MDToast.TYPE_ERROR);
                            mdToast.show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //You can handle error here if you want
                        loading.dismiss();
                        MDToast mdToast = MDToast.makeText(getApplicationContext(), "The server unreachable", MDToast.LENGTH_LONG, MDToast.TYPE_ERROR);
                        mdToast.show();

                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                //Adding parameters to request
                params.put(config.KEY_nip_guru, user.getText().toString());
                params.put(config.KEY_pass_guru, pass.getText().toString());

                return params;
            }
        };

        Volley.newRequestQueue(this).add(stringRequest);
    }
}
