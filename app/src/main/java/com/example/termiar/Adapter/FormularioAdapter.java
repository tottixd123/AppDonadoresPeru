package com.example.termiar.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.termiar.Formulario.Formulario;
import com.example.termiar.R;

import java.util.List;

public class FormularioAdapter extends RecyclerView.Adapter<FormularioAdapter.ViewHolder> {
    private List<Formulario> formularios;


    public FormularioAdapter (List<Formulario> itemList){
        this.formularios=itemList;

    }

    @Override
    public int getItemCount(){return formularios.size();}

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType ){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder,int position){
        View view = holder.itemView;
        Formulario formu = formularios.get(position);

        TextView textViewUsuario, textViewTalla, textViewPeso, textViewFechaUltimaDonacion, textViewSexo, textViewTipoSangre,
                textViewImpedimentos, textViewAceptoTerminos;

        textViewUsuario = view.findViewById(R.id.textViewUsuario);
        textViewTalla = view.findViewById(R.id.textViewTalla);
        textViewPeso = view.findViewById(R.id.textViewPeso);
        textViewFechaUltimaDonacion = view.findViewById(R.id.textViewFechaUltimaDonacion);
        textViewSexo = view.findViewById(R.id.textViewSexo);
        textViewTipoSangre = view.findViewById(R.id.textViewTipoSangre);
        textViewImpedimentos = view.findViewById(R.id.textViewImpedimentos);
        textViewAceptoTerminos = view.findViewById(R.id.textViewAceptoTerminos);


        textViewUsuario.setText("Usuario: " + formu.getUsuario());
        textViewTalla.setText("Talla: " + formu.getTalla());
        textViewPeso.setText("Peso: " + formu.getPeso());
        textViewFechaUltimaDonacion.setText("Fecha última donación: " + formu.getFechaUltimaDonacion());
        textViewSexo.setText("Sexo: " + formu.getSexo());
        textViewTipoSangre.setText("Tipo de sangre: " + formu.getTipoSangre());
        textViewImpedimentos.setText("Impedimentos: " + (formu.isImpedimentos() ? "Sí" : "No"));
        textViewAceptoTerminos.setText("Acepto términos: " + (formu.isAceptoTerminos() ? "Sí" : "No"));

    }
    class ViewHolder extends RecyclerView.ViewHolder{
        public ViewHolder(@NonNull View itemView){
            super(itemView);
        }
    }

    public void setItems(List<Formulario>items ){formularios=items;}

    /*
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView textViewUsuario, textViewTalla, textViewPeso, textViewFechaUltimaDonacion, textViewSexo, textViewTipoSangre,
                textViewImpedimentos, textViewAceptoTerminos;

        ViewHolder(View itemView){
            super(itemView);
            textViewUsuario = itemView.findViewById(R.id.textViewUsuario);
            textViewTalla = itemView.findViewById(R.id.textViewTalla);
            textViewPeso = itemView.findViewById(R.id.textViewPeso);
            textViewFechaUltimaDonacion = itemView.findViewById(R.id.textViewFechaUltimaDonacion);
            textViewSexo = itemView.findViewById(R.id.textViewSexo);
            textViewTipoSangre = itemView.findViewById(R.id.textViewTipoSangre);
            textViewImpedimentos = itemView.findViewById(R.id.textViewImpedimentos);
            textViewAceptoTerminos = itemView.findViewById(R.id.textViewAceptoTerminos);
        }
        void binData(final Formulario item){
            textViewUsuario.setText("Usuario: " + item.getUsuario());
            textViewTalla.setText("Talla: " + item.getTalla());
            textViewPeso.setText("Peso: " + item.getPeso());
            textViewFechaUltimaDonacion.setText("Fecha última donación: " + item.getFechaUltimaDonacion());
            textViewSexo.setText("Sexo: " + item.getSexo());
            textViewTipoSangre.setText("Tipo de sangre: " + item.getTipoSangre());
            textViewImpedimentos.setText("Impedimentos: " + (item.isImpedimentos() ? "Sí" : "No"));
            textViewAceptoTerminos.setText("Acepto términos: " + (item.isAceptoTerminos() ? "Sí" : "No"));
        }
    }
*/




     /*

    public FormularioAdapter(List<Formulario> formularios) {
        this.formularios = formularios;
    }

    @NonNull
    @Override
    public FormularioAdapter.FormularioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_mostarformularios, parent, false);
        return new FormularioViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FormularioAdapter.FormularioViewHolder holder, int position) {
        Formulario formulario = formularios.get(position);
        holder.textViewUsuario.setText("Usuario: " + formulario.getUsuario());
        holder.textViewTalla.setText("Talla: " + formulario.getTalla());
        holder.textViewPeso.setText("Peso: " + formulario.getPeso());
        holder.textViewFechaUltimaDonacion.setText("Fecha última donación: " + formulario.getFechaUltimaDonacion());
        holder.textViewSexo.setText("Sexo: " + formulario.getSexo());
        holder.textViewTipoSangre.setText("Tipo de sangre: " + formulario.getTipoSangre());
        holder.textViewImpedimentos.setText("Impedimentos: " + (formulario.isImpedimentos() ? "Sí" : "No"));
        holder.textViewAceptoTerminos.setText("Acepto términos: " + (formulario.isAceptoTerminos() ? "Sí" : "No"));
    }

    @Override
    public int getItemCount() {
        return formularios.size();
    }

    public static class FormularioViewHolder extends RecyclerView.ViewHolder {
        TextView textViewUsuario, textViewTalla,
                textViewPeso, textViewFechaUltimaDonacion,
                textViewSexo, textViewTipoSangre, textViewImpedimentos,
                textViewAceptoTerminos;

        public FormularioViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewUsuario = itemView.findViewById(R.id.textViewUsuario);
            textViewTalla = itemView.findViewById(R.id.textViewTalla);
            textViewPeso = itemView.findViewById(R.id.textViewPeso);
            textViewFechaUltimaDonacion = itemView.findViewById(R.id.textViewFechaUltimaDonacion);
            textViewSexo = itemView.findViewById(R.id.textViewSexo);
            textViewTipoSangre = itemView.findViewById(R.id.textViewTipoSangre);
            textViewImpedimentos = itemView.findViewById(R.id.textViewImpedimentos);
            textViewAceptoTerminos = itemView.findViewById(R.id.textViewAceptoTerminos);
        }
    }

      */
}
