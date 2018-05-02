package com.sekolah.admin.system.adapter.adapter_kepsek;

import android.app.Activity;
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
import com.sekolah.admin.system.ui_kepsek.data_pertanyaan;
import com.sekolah.admin.system.oop.Item;
import com.valdesekamdem.library.mdtoast.MDToast;

import java.util.List;

public class adapter_list_kategori extends RecyclerView.Adapter<adapter_list_kategori.ViewHolder> {
    private ImageLoader imageLoader;
    private Context context;
    List<Item> dftr;

    public adapter_list_kategori(List<Item> dftr, Context context){
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

            Bundle bundle = ((Activity)context).getIntent().getExtras();
            nip = bundle.getString("nip");
            MDToast mdToast = MDToast.makeText(context, nip, MDToast.LENGTH_LONG, MDToast.TYPE_WARNING);
            mdToast.show();

            kat_liner.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent in = new Intent(context,data_pertanyaan.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("id_kat",txtid_kategori.getText().toString());
                    bundle.putString("kirim_nip",nip);
                    in.putExtras(bundle);
                    v.getContext().startActivity(in);
                }
            });

        }
    }
}