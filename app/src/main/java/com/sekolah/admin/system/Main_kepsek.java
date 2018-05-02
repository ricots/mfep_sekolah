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

import com.sekolah.admin.system.koneksi.config;
import com.sekolah.admin.system.ui_admin.list_penilaian;
import com.sekolah.admin.system.ui_kepsek.daftar_guru;
import com.sekolah.admin.system.ui_kepsek.persentase;

public class Main_kepsek extends AppCompatActivity {
    SharedPreferences sp;
    SharedPreferences.Editor spe;
    LinearLayout guru, hasil, persen, keluar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_kepsek);
        sp = getSharedPreferences(config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        String welcome = sp.getString(config.nipkepsek_SHARED_PREF, "Not Available");
        guru = (LinearLayout) findViewById(R.id.btn_nilai);
        guru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main_kepsek.this, daftar_guru.class);
                startActivity(intent);
            }
        });

        hasil = (LinearLayout) findViewById(R.id.btn_hasil);
        hasil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main_kepsek.this, list_penilaian.class);
                startActivity(intent);
            }
        });

        persen = (LinearLayout) findViewById(R.id.btn_persen);
        persen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main_kepsek.this, persentase.class);
                startActivity(intent);
            }
        });

        keluar = (LinearLayout) findViewById(R.id.btn_keluar);
        keluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Main_kepsek.this);
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
                                Intent intent = new Intent(Main_kepsek.this, login.class);
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
