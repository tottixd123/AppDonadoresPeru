package com.example.termiar.Campanas;

public class Campana {
    private int id;
    private String tituloC;
    private String imagenC;
    private String descripcionC;

    public Campana() {
    }

    public Campana(int id, String tituloC, String imagenC, String descripcionC) {
        this.id = id;
        this.tituloC = tituloC;
        this.imagenC = imagenC;
        this.descripcionC = descripcionC;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTituloC() {
        return tituloC;
    }

    public void setTituloC(String tituloC) {
        this.tituloC = tituloC;
    }

    public String getImagenC() {
        return imagenC;
    }

    public void setImagenC(String imagenC) {
        this.imagenC = imagenC;
    }

    public String getDescripcionC() {
        return descripcionC;
    }

    public void setDescripcionC(String descripcionC) {
        this.descripcionC = descripcionC;
    }
}
