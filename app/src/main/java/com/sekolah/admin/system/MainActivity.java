package com.sekolah.admin.system;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.sekolah.admin.system.koneksi.config;
import com.sekolah.admin.system.ui_admin.daftar_kategori;
import com.sekolah.admin.system.ui_admin.data_kepala_sekolah;
import com.sekolah.admin.system.ui_admin.list_penilaian;

public class MainActivity extends AppCompatActivity {
    SharedPreferences sp;
    SharedPreferences.Editor spe;
    LinearLayout kepala_Sekolah, kategori, guru, keluar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sp = getSharedPreferences(config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        String welcome = sp.getString(config.EMAIL_SHARED_PREF, "Not Available");
        Toast.makeText(getApplicationContext(),"selamat datang " + welcome,Toast.LENGTH_LONG).show();
        kepala_Sekolah = (LinearLayout) findViewById(R.id.kepala_sekolah);
        kepala_Sekolah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, data_kepala_sekolah.class);
                startActivity(intent);
            }
        });

        kategori = (LinearLayout) findViewById(R.id.in_kategori);
        kategori.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, daftar_kategori.class);
                startActivity(intent);
            }
        });

        guru = (LinearLayout) findViewById(R.id.guru);
        guru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, list_penilaian.class);
                startActivity(intent);
            }
        });

        keluar = (LinearLayout) findViewById(R.id.keluar);
        keluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
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
                                Intent intent = new Intent(MainActivity.this, login.class);
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
    }
}
