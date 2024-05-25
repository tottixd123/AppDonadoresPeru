package com.example.termiar.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.termiar.Adapter.TrendsAdapter;
import com.example.termiar.Domain.TrendSDomain;
import com.example.termiar.Formulario.Formulario_Registro;
import com.example.termiar.Mapa.Map_donaciones;
import com.example.termiar.R;

import java.util.ArrayList;

public class Generl extends AppCompatActivity {
    private RecyclerView.Adapter adapterTrendsList;
    private RecyclerView recyclerViewTrends;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_generl);
        initRecyclearView();
        BottomNavigation();
        Button_formu();
        Button_lugar();
    }
    private void Button_lugar(){
        LinearLayout lugarBtn=findViewById(R.id.lugarBtn);
        lugarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Generl.this, Map_donaciones.class));
            }
        });
    }
     private void Button_formu(){
        LinearLayout configuraicon=findViewById(R.id.configuraicon);
         configuraicon.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 startActivity(new Intent(Generl.this, Formulario_Registro.class));
             }
         });
     }
    private void BottomNavigation() {
        LinearLayout profileBtn=findViewById(R.id.profileBtn);
        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Generl.this,Perfil.class));
            }
        });
    }

    private void initRecyclearView(){
        ArrayList<TrendSDomain> items= new ArrayList<>();
        items.add(new TrendSDomain("noticia","Nota","trends"));
        items.add(new TrendSDomain("noticia1","Nota2","trends2"));
        items.add(new TrendSDomain("noticia","Nota","trends"));

        recyclerViewTrends=findViewById(R.id.view1);
        recyclerViewTrends.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        adapterTrendsList=new TrendsAdapter(items);
        recyclerViewTrends.setAdapter(adapterTrendsList);
    }
}