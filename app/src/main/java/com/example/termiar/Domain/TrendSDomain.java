package com.example.termiar.Domain;

public class TrendSDomain {

    private int id;
    private String title;
    private String imagen;
    private String descripcion;

    private String url;

    public TrendSDomain() {
    }

    public TrendSDomain(int id, String title, String imagen, String descripcion, String url) {
        this.id = id;
        this.title = title;
        this.imagen = imagen;
        this.descripcion = descripcion;
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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
