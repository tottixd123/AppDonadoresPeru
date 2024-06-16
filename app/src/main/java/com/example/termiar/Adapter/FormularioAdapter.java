package com.example.termiar.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.termiar.Formulario.Formulario;
import com.example.termiar.R;

import java.util.List;

public class FormularioAdapter extends RecyclerView.Adapter<FormularioAdapter.FormularioViewHolder> {
    private List<Formulario> formularios;

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
}
