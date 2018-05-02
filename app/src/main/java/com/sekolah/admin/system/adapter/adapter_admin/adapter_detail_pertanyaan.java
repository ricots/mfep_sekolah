package com.sekolah.admin.system.adapter.adapter_admin;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.sekolah.admin.system.R;
import com.sekolah.admin.system.oop.Item;

import java.util.List;

public class adapter_detail_pertanyaan extends RecyclerView.Adapter<adapter_detail_pertanyaan.ViewHolder> {
    private ImageLoader imageLoader;
    private Context context;
    List<Item> dftr;
    private static int currentPosition = 0;

    public adapter_detail_pertanyaan(List<Item> dftr, Context context){
        super();
        //Getting all the superheroes
        this.dftr = dftr;
        this.context = context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_detail_penilaian, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        Item daftar =  dftr.get(position);
        holder.list_id_detail_pertanyaan.setText(daftar.getId_pertanyaan());
        holder.detail_lis_pertanyaan.setText("pertanyaan : " + daftar.getPertanyaan());
        holder.detail_semester.setText("semester : " + daftar.getSemester());
        holder.detail_keterangan.setText("keterangan : " + daftar.getKeterangan());
        holder.detail_skor.setText("total skor : " + daftar.getTotal_skor());

        holder.linearLayout.setVisibility(View.GONE);
        if (currentPosition == position) {
            Animation slideDown = AnimationUtils.loadAnimation(context, R.anim.slide_down);
            holder.linearLayout.setVisibility(View.VISIBLE);
            holder.linearLayout.startAnimation(slideDown);
        }

        holder.list_id_detail_pertanyaan.setOnClickListener(new View.OnClickListener() {
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
        final TextView detail_lis_pertanyaan,detail_semester,detail_keterangan,detail_skor;
        Button list_id_detail_pertanyaan;
        LinearLayout linearLayout;
        Item item;

        public ViewHolder(View itemView) {
            super(itemView);
            list_id_detail_pertanyaan = (Button)  itemView.findViewById(R.id.list_id_detail_pertanyaan);
            detail_lis_pertanyaan = (TextView) itemView.findViewById(R.id.detail_lis_pertanyaan);
            detail_semester = (TextView) itemView.findViewById(R.id.detail_semester);
            detail_keterangan = (TextView) itemView.findViewById(R.id.detail_keterangan);
            detail_skor = (TextView) itemView.findViewById(R.id.detail_total);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.detail_linearLayout);
        }
    }
}