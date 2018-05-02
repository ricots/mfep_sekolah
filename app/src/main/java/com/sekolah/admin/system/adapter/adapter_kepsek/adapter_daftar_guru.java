package com.sekolah.admin.system.adapter.adapter_kepsek;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.sekolah.admin.system.R;
import com.sekolah.admin.system.ui_kepsek.daftar_kat;
import com.sekolah.admin.system.oop.Item;

import java.util.List;

public class adapter_daftar_guru extends RecyclerView.Adapter<adapter_daftar_guru.ViewHolder> {
    private ImageLoader imageLoader;
    private Context context;
    List<Item> dftr;

    public adapter_daftar_guru(List<Item> dftr, Context context){
        super();
        //Getting all the superheroes
        this.dftr = dftr;
        this.context = context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_guru, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Item daftar =  dftr.get(position);
        holder.txtnip_guru.setText(daftar.getNip_guru());
        holder.txtnama_guru.setText(daftar.getNama_guru());
        holder.item = daftar;
    }

    @Override
    public int getItemCount() {
        return dftr.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        final TextView txtnip_guru,txtnama_guru;
        LinearLayout nilai_liner;
        Item item;

        public ViewHolder(View itemView) {
            super(itemView);
            txtnip_guru = (TextView) itemView.findViewById(R.id.txtnip_guru);
            txtnama_guru = (TextView) itemView.findViewById(R.id.txtnama_guru);
            nilai_liner = (LinearLayout) itemView.findViewById(R.id.nilailiner);
            nilai_liner.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent in = new Intent(context,daftar_kat.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("nip",txtnip_guru.getText().toString());
                    in.putExtras(bundle);
                    v.getContext().startActivity(in);
                }
            });

//            kat_liner.setOnLongClickListener(new View.OnLongClickListener() {
//                @Override
//                public boolean onLongClick(View v) {
//                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
//                    alertDialogBuilder.setMessage("apakah anda yakin ingin menghapus data ?");
//                    alertDialogBuilder.setPositiveButton("Yes",
//                            new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface arg0, int arg1) {
//                                    final ProgressDialog loading = ProgressDialog.show(context, "Delete Data", "Please wait...", false, false);
//                                    StringRequest postRequest = new StringRequest(Request.Method.POST, config.DELETE_KATEGORI +
//                                            txtid_kategori.getText().toString(),
//                                            new Response.Listener<String>() {
//                                                @Override
//                                                public void onResponse(String response) {
//                                                    loading.dismiss();
//                                                    MDToast mdToast = MDToast.makeText(context, response.toString(), MDToast.LENGTH_LONG, MDToast.TYPE_SUCCESS);
//                                                    mdToast.show();
////                            Intent in = new Intent(add_pertanyaan.this, daftar_pertanyaan.class);
////                            startActivity(in);
//                                                }
//                                            }, new Response.ErrorListener() {
//                                        @Override
//                                        public void onErrorResponse(VolleyError error) {
//                                            loading.dismiss();
//                                            MDToast mdToast = MDToast.makeText(context, error.toString(), MDToast.LENGTH_LONG, MDToast.TYPE_ERROR);
//                                            mdToast.show();
////                    Toast.makeText(data_kepala_sekolah.this,
////                            error.getMessage(), Toast.LENGTH_SHORT).show();
//                                        }
//                                    }) {
//                                        @Override
//                                        protected Map<String, String> getParams() {
//                                            Map<String, String> params = new HashMap<String, String>();
//                                            params.put(config.KEY_id_pertanyaan, txtid_kategori.getText().toString());
//                                            return params;
//                                        }
//                                    };
//                                    RequestQueue requestQueue = Volley.newRequestQueue(context);
//                                    requestQueue.add(postRequest);
//                                }
//                            });
//
//                    alertDialogBuilder.setNegativeButton("No",
//                            new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface arg0, int arg1) {
//
//                                }
//                            });
//
//                    //Showing the alert dialog
//                    AlertDialog alertDialog = alertDialogBuilder.create();
//                    alertDialog.show();
//                    return false;
//                }
//            });
           }
       }
}