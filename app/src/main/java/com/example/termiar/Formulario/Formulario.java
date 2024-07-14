package com.example.termiar.Formulario;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "formularios")
public class Formulario {
@PrimaryKey(autoGenerate = true)
    private int id;
    private String usuario;
    private double talla;
    private double  peso;
    private String sexo;
    private String fechaUltimaDonacion;
    private String tipoSangre;
    private boolean impedimentos;
    private boolean aceptoTerminos;


    public Formulario() {
    }

    public Formulario(int id, String usuario, double talla, double peso, String sexo, String fechaUltimaDonacion, String tipoSangre, boolean impedimentos, boolean aceptoTerminos) {
        this.id = id;
        this.usuario = usuario;
        this.talla = talla;
        this.peso = peso;
        this.sexo = sexo;
        this.fechaUltimaDonacion = fechaUltimaDonacion;
        this.tipoSangre = tipoSangre;
        this.impedimentos = impedimentos;
        this.aceptoTerminos = aceptoTerminos;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public double getTalla() {
        return talla;
    }

    public void setTalla(double talla) {
        this.talla = talla;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getFechaUltimaDonacion() {
        return fechaUltimaDonacion;
    }

    public void setFechaUltimaDonacion(String fechaUltimaDonacion) {
        this.fechaUltimaDonacion = fechaUltimaDonacion;
    }

    public String getTipoSangre() {
        return tipoSangre;
    }

    public void setTipoSangre(String tipoSangre) {
        this.tipoSangre = tipoSangre;
    }

    public boolean isImpedimentos() {
        return impedimentos;
    }

    public void setImpedimentos(boolean impedimentos) {
        this.impedimentos = impedimentos;
    }

    public boolean isAceptoTerminos() {
        return aceptoTerminos;
    }

    public void setAceptoTerminos(boolean aceptoTerminos) {
        this.aceptoTerminos = aceptoTerminos;
    }
}
