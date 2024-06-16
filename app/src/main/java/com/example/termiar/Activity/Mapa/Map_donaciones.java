package com.example.termiar.Activity.Mapa;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

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
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
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
}