package com.sekolah.admin.system.adapter.adapter_admin;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.sekolah.admin.system.ui_admin.daftar_pertanyaan;
import com.valdesekamdem.library.mdtoast.MDToast;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class adapter_data_kategori extends RecyclerView.Adapter<adapter_data_kategori.ViewHolder> {
    private ImageLoader imageLoader;
    private Context context;
    List<Item> dftr;

    public adapter_data_kategori(List<Item> dftr, Context context){
        super();
        //Getting all the superheroes
        this.dftr = dftr;
        this.context = context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_kategori, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Item daftar =  dftr.get(position);
        holder.txtid_kategori.setText(daftar.getId_kategori());
        holder.txt_kategori.setText(daftar.getKategori());
        holder.item = daftar;
    }

    @Override
    public int getItemCount() {
        return dftr.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        final TextView txtid_kategori,txt_kategori;
        LinearLayout kat_liner;
        Item item;
        String nip;

        public ViewHolder(View itemView) {
            super(itemView);
            txtid_kategori = (TextView) itemView.findViewById(R.id.txtid_kategori);
            txt_kategori = (TextView) itemView.findViewById(R.id.txt_kategori);
            kat_liner = (LinearLayout) itemView.findViewById(R.id.kat_liner);

            kat_liner.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent in = new Intent(context,daftar_pertanyaan.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("id_kat",txtid_kategori.getText().toString());
                    in.putExtras(bundle);
                    v.getContext().startActivity(in);
                }
            });

            kat_liner.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                    alertDialogBuilder.setMessage("apakah anda yakin ingin mengahapus ?");
                    alertDialogBuilder.setPositiveButton("Yes",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface arg0, int arg1) {
                                    final ProgressDialog loading = ProgressDialog.show(context, "Delete Data", "Please wait...", false, false);
                                    StringRequest postRequest = new StringRequest(Request.Method.POST, config.DELETE_KATEGORI + txtid_kategori.getText().toString(),
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
                                            params.put(config.KEY_id_kategori, txtid_kategori.getText().toString());
                                            return params;
                                        }
                                    };
                                    RequestQueue requestQueue = Volley.newRequestQueue(context);
                                    requestQueue.add(postRequest);
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
                    return false;
            }
            });
        }
    }
}