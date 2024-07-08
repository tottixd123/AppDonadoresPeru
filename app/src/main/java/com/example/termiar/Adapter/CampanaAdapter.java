package com.example.termiar.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.termiar.Campanas.Campana;
import com.example.termiar.Domain.TrendSDomain;
import com.example.termiar.Donacion.Donacion_Const;
import com.example.termiar.Formulario.Formulario;
import com.example.termiar.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CampanaAdapter extends RecyclerView.Adapter<CampanaAdapter.ViewHolder> {

    private List<Campana> campanas;


    public CampanaAdapter(List<Campana> itemList) {
        this.campanas = itemList;
    }

    @Override
    public int getItemCount(){return campanas.size();}

    @Override
    public CampanaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.campana_diseno,parent,false);
        CampanaAdapter.ViewHolder holder = new CampanaAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        View view = holder.itemView;
        Campana camp = campanas.get(position);

        TextView tituloc, descC;
        ImageView imagenC;

        tituloc = view.findViewById(R.id.titulocamp);
        descC = view.findViewById(R.id.descricamp);
        imagenC = view.findViewById(R.id.piccamp);

        tituloc.setText(camp.getTituloC());
        descC.setText(camp.getDescripcionC());

        Picasso.get().load(camp.getImagenC()).into(imagenC);
    }


    class ViewHolder extends RecyclerView.ViewHolder{
        public ViewHolder(@NonNull View itemView){
            super(itemView);
        }
    }

    public void setItems(List<Campana>items ){campanas=items;}



}
