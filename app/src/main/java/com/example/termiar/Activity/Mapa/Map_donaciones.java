package com.example.termiar.Activity.Mapa;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.termiar.Activity.General.Generl;
import com.example.termiar.Activity.PerfilGeneral.Perfil;
import com.example.termiar.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.termiar.databinding.ActivityMapDonacionesBinding;

public class Map_donaciones extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapDonacionesBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapDonacionesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map_don);
        mapFragment.getMapAsync(this);

        BotonInicio();
        BotonPerfil();
        Button_lugar();


    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng pD_1 = new LatLng(-7.183321,-78.4878502);
        googleMap.addMarker(new MarkerOptions().position(pD_1).title("Hospital Regional de Cajamarca").snippet("Horarios de Donaciones: 7:00 AM - 6:00 PM"));

        googleMap.moveCamera(CameraUpdateFactory.newLatLng(pD_1));


        LatLng pD_2 = new LatLng(-7.155231,-78.5130959);
        googleMap.addMarker(new MarkerOptions().position(pD_2).title("Ex Hospital Regional de Cajamarca").snippet("Horarios de Donaciones: 8:00 AM - 5:00 PM"));

    }


    private void BotonInicio() {
        LinearLayout Inicio =findViewById(R.id.inicioBtn);
        Inicio.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Map_donaciones.this, Generl.class));
            }
        });
    }

    private void BotonPerfil() {
        LinearLayout profileBtn=findViewById(R.id.profileBtn);
        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Map_donaciones.this, Perfil.class));
            }
        });
    }

    private void Button_lugar(){
        LinearLayout lugarBtn=findViewById(R.id.lugarBtn);
        lugarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Map_donaciones.this, Map_donaciones.class));
            }
        });
    }


}