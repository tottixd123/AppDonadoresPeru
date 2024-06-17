package com.example.termiar.Domain;

public class TrendSDomain {

    private int id;
    private String title;
    private String imagen;
    private String descripcion;

    public TrendSDomain() {
    }

    public TrendSDomain(int id, String title, String imagen, String descripcion) {
        this.id = id;
        this.title = title;
        this.imagen = imagen;
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
