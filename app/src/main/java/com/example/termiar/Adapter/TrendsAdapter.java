package com.example.termiar.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners;
import com.example.termiar.Domain.TrendSDomain;
import com.example.termiar.Formulario.Formulario;
import com.example.termiar.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class TrendsAdapter  extends RecyclerView.Adapter<TrendsAdapter.Viewholder> {


    private List<TrendSDomain> items;

    public TrendsAdapter (List<TrendSDomain> itemNoticia){
        this.items=itemNoticia;
    }


    @Override
    public int getItemCount(){return items.size();}

    @Override
    public TrendsAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType ){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_trend_list,parent,false);
        TrendsAdapter.Viewholder holder = new TrendsAdapter.Viewholder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position){
        View view = holder.itemView;
        TrendSDomain noticia = items.get(position);

        TextView title, descripcion;
        ImageView imagen;

        title = view.findViewById(R.id.titleTxt);
        descripcion = view.findViewById(R.id.subtitleTxt);
        imagen = view.findViewById(R.id.pic);

        title.setText(noticia.getTitle());
        descripcion.setText(noticia.getDescripcion());

        Picasso.get().load(noticia.getImagen()).into(imagen);

        /*
        int drawableResourceId = view.getResources().getIdentifier(noticia.getImagen(),"drawable",view.getContext().getPackageName());

        /*
        Glide.with(view.getContext())
                .load(drawableResourceId)
                .transform(new GranularRoundedCorners(30,30,0,0))
                .into(imagen);*/

    }

    class Viewholder extends RecyclerView.ViewHolder{
        public Viewholder(@NonNull View itemView){
            super(itemView);
        }
    }

    /*


    ArrayList<TrendSDomain> items;
    Context context;

    public TrendsAdapter(ArrayList<TrendSDomain> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public TrendsAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflator= LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_trend_list,parent,false);
        context = parent.getContext();
        return new Viewholder(inflator);
    }

    @Override
    public void onBindViewHolder(@NonNull TrendsAdapter.Viewholder holder, int position) {

    holder.title.setText(items.get(position).getTitle());
    holder.descripcion.setText(items.get(position).getDescripcion());

    int drawableResourceId = holder.itemView.getResources().getIdentifier(items.get(position).getImagen(),"drawable",holder.itemView.getContext().getPackageName());
    Glide.with(holder.itemView.getContext())
            .load(drawableResourceId)
            .transform(new GranularRoundedCorners(30,30,0,0))
            .into(holder.imagen);

    Picasso.get().load(peli.img).into(holder.imagen);

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder{
        TextView title, descripcion;
        ImageView imagen;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.titleTxt);
            descripcion = itemView.findViewById(R.id.subtitleTxt);
            imagen = itemView.findViewById(R.id.pic);
        }
    }

     */

    /*
    int drawableResourceId = holder.itemView.getResources().getIdentifier(items.get(position).getImagen(),"drawable",holder.itemView.getContext().getPackageName());
    Glide.with(holder.itemView.getContext())
            .load(drawableResourceId)
            .transform(new GranularRoundedCorners(30,30,0,0))
            .into(holder.imagen);


     */
}
