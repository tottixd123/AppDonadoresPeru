package com.example.termiar.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
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
import com.example.termiar.Network.RetrofitFactory;
import com.example.termiar.R;
import com.example.termiar.Servicios.NoticiaService;
import com.squareup.picasso.Picasso;

import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

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

        TextView title, descripcion, links;
        ImageView imagen;

        title = view.findViewById(R.id.titleTxt);
        descripcion = view.findViewById(R.id.subtitleTxt);
        links = view.findViewById(R.id.mandourl);
        imagen = view.findViewById(R.id.pic);

        title.setText(noticia.getTitle());
        descripcion.setText(noticia.getDescripcion());
        links.setText(noticia.getUrl());

        Picasso.get().load(noticia.getImagen()).into(imagen);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Retrofit retro = RetrofitFactory.build2();
                NoticiaService serviciosxd = retro.create(NoticiaService.class);
                Call<TrendSDomain> call = serviciosxd.idNoticia(noticia.getId());
                call.enqueue(new Callback<TrendSDomain>() {
                    @Override
                    public void onResponse(Call<TrendSDomain> call, Response<TrendSDomain> response) {
                        String urlNoticia = noticia.getUrl();
                        if (!response.isSuccessful()){
                            Log.e("NOTICIOSA","Error de la APP");
                        }else {
                            Log.i("NOTICIOSA","SI MUESTRA DATOS VOY A LOS IDS");

                            if (urlNoticia != null && !urlNoticia.isEmpty()) {
                                Uri pruebasurl = Uri.parse(urlNoticia);
                                Intent intent = new Intent(Intent.ACTION_VIEW, pruebasurl);
                                view.getContext().startActivity(intent);
                            } else {
                                Log.e("NOTICIOSA", "La URL de la noticia es nula o vacía");
                            }
                        }

                    }

                    @Override
                    public void onFailure(Call<TrendSDomain> call, Throwable t) {
                        Log.e("NOTICIOSA","Error de la APP");
                    }
                });
            }
        });

    }

    class Viewholder extends RecyclerView.ViewHolder{
        public Viewholder(@NonNull View itemView){
            super(itemView);
        }
    }

}
