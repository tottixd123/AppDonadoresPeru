package com.example.termiar.Activity.Main;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.termiar.R;

public class SplashInicio extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN); //Para hace que este full pantalla a la hora de ingresar

        setContentView(R.layout.activity_splash_inicio);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //Animaciones
        Animation animacion1 = AnimationUtils.loadAnimation(this,R.anim.desplazar_arriba);
        Animation animacion2 = AnimationUtils.loadAnimation(this,R.anim.desplazar_abajo);

        TextView texto = findViewById(R.id.textoPrin);
        ImageView imagens = findViewById(R.id.imagenLogoP);

        texto.setAnimation(animacion2);
        imagens.setAnimation(animacion1);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashInicio.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        },4000);

    }
}