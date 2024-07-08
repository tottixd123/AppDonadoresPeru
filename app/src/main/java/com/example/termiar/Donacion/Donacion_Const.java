package com.example.termiar.Donacion;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "donaciones")
public class Donacion_Const {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String fechadedonacion;
    public String lugardedonacion;
    public String horadedonacion;

    public Donacion_Const() {
    }

    public Donacion_Const(int id, String fechadedonacion, String lugardedonacion, String horadedonacion) {
        this.id = id;
        this.fechadedonacion = fechadedonacion;
        this.lugardedonacion = lugardedonacion;
        this.horadedonacion = horadedonacion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFechadedonacion() {
        return fechadedonacion;
    }

    public void setFechadedonacion(String fechadedonacion) {
        this.fechadedonacion = fechadedonacion;
    }

    public String getLugardedonacion() {
        return lugardedonacion;
    }

    public void setLugardedonacion(String lugardedonacion) {
        this.lugardedonacion = lugardedonacion;
    }

    public String getHoradedonacion() {
        return horadedonacion;
    }

    public void setHoradedonacion(String horadedonacion) {
        this.horadedonacion = horadedonacion;
    }
}
