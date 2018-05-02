package com.sekolah.admin.system.adapter.adapter_kepsek;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.sekolah.admin.system.R;
import com.sekolah.admin.system.koneksi.config;
import com.sekolah.admin.system.login;
import com.sekolah.admin.system.oop.Item;
import com.sekolah.admin.system.ui_guru.regis;
import com.valdesekamdem.library.mdtoast.MDToast;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import info.hoang8f.widget.FButton;

public class adapter_data_pertanyaan extends RecyclerView.Adapter<adapter_data_pertanyaan.ViewHolder> {
    private ImageLoader imageLoader;
    private Context context;
    List<Item> dftr;
    private static int currentPosition = 0;

    public adapter_data_pertanyaan(List<Item> dftr, Context context){
        super();
        //Getting all the superheroes
        this.dftr = dftr;
        this.context = context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_data_pertanyaan, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        Item daftar =  dftr.get(position);
        holder.list_id_pertanyaan.setText(daftar.getId_pertanyaan());
        holder.list_dataid_kat.setText(daftar.getId_kategori());
        holder.bbt.setText(daftar.getBobot());
        holder.lis_datapertanyaan.setText("pertanyaan : " + daftar.getPertanyaan());

        holder.linearLayout.setVisibility(View.GONE);
        if (currentPosition == position) {
            Animation slideDown = AnimationUtils.loadAnimation(context, R.anim.slide_down);
            holder.linearLayout.setVisibility(View.VISIBLE);
            holder.linearLayout.startAnimation(slideDown);
        }

        holder.list_id_pertanyaan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentPosition = position;
                notifyDataSetChanged();
            }
        });
        holder.item = daftar;
    }

    @Override
    public int getItemCount() {
        return dftr.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        final TextView list_dataid_kat,lis_datapertanyaan,bbt;
        Button list_id_pertanyaan;
        LinearLayout linearLayout;
        FButton btnsimpan;
        Item item;
        CheckBox sb,b,c,ba,sba;
        String get_nip,ket;
        int skor;
        double bobot;

        public ViewHolder(View itemView) {
            super(itemView);
            list_id_pertanyaan = (Button)  itemView.findViewById(R.id.list_dataid_pertanyaan);
            list_dataid_kat = (TextView) itemView.findViewById(R.id.list_dataid_kat);
            lis_datapertanyaan = (TextView) itemView.findViewById(R.id.lis_datapertanyaan);
            bbt = (TextView) itemView.findViewById(R.id.bbt);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.datalinearLayout);
            btnsimpan = (FButton) itemView.findViewById(R.id.btnsimpan_tanya);
            sb = (CheckBox) itemView.findViewById(R.id.sb);
            b = (CheckBox) itemView.findViewById(R.id.b);
            c = (CheckBox) itemView.findViewById(R.id.c);
            ba = (CheckBox) itemView.findViewById(R.id.ba);
            sba = (CheckBox) itemView.findViewById(R.id.sba);

            final Bundle bundle = ((Activity) context).getIntent().getExtras();
            get_nip = bundle.getString("kirim_nip");
            MDToast mdToast = MDToast.makeText(context, get_nip, MDToast.LENGTH_LONG, MDToast.TYPE_WARNING);
            mdToast.show();

            sb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (sb.isChecked()){
                        skor = 2;
                        ket = "sangat buruk";
                        double getbobot = Double.parseDouble(bbt.getText().toString());
                        bobot = getbobot * skor;
                        b.setEnabled(false);
                        c.setEnabled(false);
                        ba.setEnabled(false);
                        sba.setEnabled(false);
                    }else{
                        sb.setEnabled(true);
                        b.setEnabled(true);
                        c.setEnabled(true);
                        ba.setEnabled(true);
                        sba.setEnabled(true);
                    }
                }
            });

            b.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (b.isChecked()){
                        skor = 4;
                        ket = "buruk";
                        double getbobot = Double.parseDouble(bbt.getText().toString());
                        bobot = getbobot * skor;
                        sb.setEnabled(false);
                        c.setEnabled(false);
                        ba.setEnabled(false);
                        sba.setEnabled(false);
                    }else{
                        sb.setEnabled(true);
                        b.setEnabled(true);
                        c.setEnabled(true);
                        ba.setEnabled(true);
                        sba.setEnabled(true);
                    }
                }
            });

            c.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (c.isChecked()){
                        skor = 6;
                        ket = "cukup";
                        double getbobot = Double.parseDouble(bbt.getText().toString());
                        bobot = getbobot * skor;
                        sb.setEnabled(false);
                        b.setEnabled(false);
                        ba.setEnabled(false);
                        sba.setEnabled(false);
                    }else{
                        sb.setEnabled(true);
                        b.setEnabled(true);
                        c.setEnabled(true);
                        ba.setEnabled(true);
                        sba.setEnabled(true);
                    }
                }
            });

            ba.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (ba.isChecked()){
                        skor = 8;
                        ket = "baik";
                        double getbobot = Double.parseDouble(bbt.getText().toString());
                        bobot = getbobot * skor;
                        sb.setEnabled(false);
                        b.setEnabled(false);
                        c.setEnabled(false);
                        sba.setEnabled(false);
                    }else{
                        sb.setEnabled(true);
                        b.setEnabled(true);
                        c.setEnabled(true);
                        ba.setEnabled(true);
                        sba.setEnabled(true);
                    }
                }
            });

            sba.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (sba.isChecked()){
                        skor = 10;
                        ket = "sangat baik";
                        double getbobot = Double.parseDouble(bbt.getText().toString());
                        bobot = getbobot * skor;
                        sb.setEnabled(false);
                        b.setEnabled(false);
                        c.setEnabled(false);
                        ba.setEnabled(false);
                    }else{
                        sb.setEnabled(true);
                        b.setEnabled(true);
                        c.setEnabled(true);
                        ba.setEnabled(true);
                        sba.setEnabled(true);
                    }
                }
            });

            btnsimpan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final ProgressDialog loading = ProgressDialog.show(context, "Loading Data", "Please wait...", false, false);
                    StringRequest postRequest = new StringRequest(Request.Method.POST, config.PENILAIAN_GURU,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    loading.dismiss();
                                    MDToast mdToast = MDToast.makeText(context, response.toString(), MDToast.LENGTH_LONG, MDToast.TYPE_SUCCESS);;
                                    mdToast.show();
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            loading.dismiss();
                            MDToast mdToast = MDToast.makeText(context, error.toString(), MDToast.LENGTH_LONG, MDToast.TYPE_ERROR);
                            mdToast.show();
//                    Toast.makeText(data_kepala_sekolah.this,
//                            error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }) {
                        @Override
                        protected Map<String, String> getParams() {
                            Map<String, String> params = new HashMap<String, String>();
                            params.put(config.KEY_id_pertanyaan, list_id_pertanyaan.getText().toString());
                            params.put(config.KEY_nip_guru, get_nip);
                            params.put(config.KEY_KET, ket);
                            params.put(config.KEY_skor, String.valueOf(skor));
                            params.put(config.KEY_toatal_skor, String.valueOf(bobot));
                            return params;
                        }
                    };
                    RequestQueue requestQueue = Volley.newRequestQueue(context);
                    requestQueue.add(postRequest);
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }
}