package com.example.termiar.Formulario;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

//@Database(entities = {Formulario.class},version = 1)
public abstract class FormularioDatabase extends RoomDatabase{
    /*
    public abstract FormularioDao formularioDao();
    private static volatile FormularioDatabase INSTANCE;
    public static FormularioDatabase getDatabase(final Context context){
        if(INSTANCE==null){
            synchronized (FormularioDatabase.class){
                if(INSTANCE==null){
                    INSTANCE= Room.databaseBuilder(context.getApplicationContext(),
                    FormularioDatabase.class,"formulario_database")
                            .fallbackToDestructiveMigration()
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

     */
}
