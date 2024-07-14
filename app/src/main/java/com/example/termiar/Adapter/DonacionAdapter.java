package com.example.termiar.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.termiar.Donacion.Donacion;
import com.example.termiar.Donacion.Donacion_Const;
import com.example.termiar.R;

import java.util.List;

public class DonacionAdapter extends RecyclerView.Adapter<DonacionAdapter.ViewHolder> {
    private List<Donacion_Const> donaciones;
    public DonacionAdapter(List<Donacion_Const> itemList) {
        this.donaciones = itemList;
    }
    @Override
    public int getItemCount(){return donaciones.size();}
    @Override
    public DonacionAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_item2,parent,false);
        DonacionAdapter.ViewHolder holder = new DonacionAdapter.ViewHolder(view);
        return holder;
    }
    @Override
    public void onBindViewHolder(@NonNull DonacionAdapter.ViewHolder holder, int position){
        View view = holder.itemView;
        Donacion_Const don = donaciones.get(position);

        TextView ultima_don, lug_don, hora_don;
        ultima_don=view.findViewById(R.id.ultima_don);
        lug_don=view.findViewById(R.id.lug_don);
        hora_don=view.findViewById(R.id.hora_don);

        ultima_don.setText("Última donación: "+don.getFechadedonacion());
        lug_don.setText("Lugar de la última donación: "+don.getLugardedonacion());
        hora_don.setText("Hora de la última donación: "+don.getHoradedonacion());
    }
    class ViewHolder extends RecyclerView.ViewHolder{
        public ViewHolder(@NonNull View itemView){
            super(itemView);
        }
    }
    public void setItems(List<Donacion_Const>items ){donaciones=items;}

}
