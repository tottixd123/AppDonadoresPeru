package com.example.termiar.Donacion;

import androidx.room.Dao;
import androidx.room.Insert;

import java.util.List;


@Dao
public interface DanacionDao {
    @Insert
    void insert(Donacion_Const donacion);
    List <Donacion_Const>getUnsyncedDonacion();

}
