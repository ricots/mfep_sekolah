package com.sekolah.admin.system.adapter.adapter_admin;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
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
import com.sekolah.admin.system.oop.Item;
import com.valdesekamdem.library.mdtoast.MDToast;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import info.hoang8f.widget.FButton;

public class adapter_list_pertanyaan extends RecyclerView.Adapter<adapter_list_pertanyaan.ViewHolder> {
    private ImageLoader imageLoader;
    private Context context;
    List<Item> dftr;
    private static int currentPosition = 0;

    public adapter_list_pertanyaan(List<Item> dftr, Context context){
        super();
        //Getting all the superheroes
        this.dftr = dftr;
        this.context = context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_pertanyaan, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        Item daftar =  dftr.get(position);
        holder.list_id_pertanyaan.setText(daftar.getId_pertanyaan());
        holder.list_id_kat.setText(daftar.getId_kategori());
        holder.lis_pertanyaan.setText("pertanyaan : " + daftar.getPertanyaan());
        holder.list_bobot.setText(daftar.getBobot());

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
        final EditText list_bobot;
        final TextView list_id_kat,lis_pertanyaan;
        Button list_id_pertanyaan;
        LinearLayout linearLayout;
        FButton btnhapus_tanya,btnupdate_tanya;
        Item item;

        public ViewHolder(View itemView) {
            super(itemView);
            list_id_pertanyaan = (Button)  itemView.findViewById(R.id.list_id_pertanyaan);
            list_id_kat = (TextView) itemView.findViewById(R.id.list_id_kat);
            lis_pertanyaan = (TextView) itemView.findViewById(R.id.lis_pertanyaan);
            list_bobot = (EditText) itemView.findViewById(R.id.list_bobot);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.linearLayout);
            btnupdate_tanya =(FButton) itemView.findViewById(R.id.btnupdate_tanya);
            btnupdate_tanya.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final ProgressDialog loading = ProgressDialog.show(context, "Update Data", "Please wait...", false, false);
                    StringRequest postRequest = new StringRequest(Request.Method.POST, config.UPDATE_BOBOT,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    loading.dismiss();
                                    MDToast mdToast = MDToast.makeText(context, response.toString(), MDToast.LENGTH_LONG, MDToast.TYPE_SUCCESS);
                                    mdToast.show();
//                            Intent in = new Intent(add_pertanyaan.this, daftar_pertanyaan.class);
//                            startActivity(in);
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
                            params.put(config.KEY_bobot, list_bobot.getText().toString());
                            return params;
                        }
                    };
                    RequestQueue requestQueue = Volley.newRequestQueue(context);
                    requestQueue.add(postRequest);
                }
            });

            btnhapus_tanya =(FButton) itemView.findViewById(R.id.btnhapus_tanya);
            btnhapus_tanya.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final ProgressDialog loading = ProgressDialog.show(context, "Delete Data", "Please wait...", false, false);
                    StringRequest postRequest = new StringRequest(Request.Method.POST, config.DELETE_pertanyaan +
                            list_id_pertanyaan.getText().toString(),
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    loading.dismiss();
                                    MDToast mdToast = MDToast.makeText(context, response.toString(), MDToast.LENGTH_LONG, MDToast.TYPE_SUCCESS);
                                    mdToast.show();
//                            Intent in = new Intent(add_pertanyaan.this, daftar_pertanyaan.class);
//                            startActivity(in);
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