package pe.area51.proyecto;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapaActivity extends AppCompatActivity {

    public GoogleMap googleMapp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }


        final int cod_map = getIntent().getIntExtra("codMap", -1);

        Toast.makeText(this, "" + cod_map, Toast.LENGTH_SHORT).show();

        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.fragMap);

        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(final GoogleMap googleMap) {
                googleMapp = googleMap;

                googleMap.getUiSettings().setMyLocationButtonEnabled(true);
                googleMap.getUiSettings().setZoomControlsEnabled(true);

                //GPSTracker
                googleMap.setOnMyLocationButtonClickListener(
                        new GoogleMap.OnMyLocationButtonClickListener() {
                            @Override
                            public boolean onMyLocationButtonClick() {

                                Location location = googleMap.getMyLocation();
                                location.getLatitude();
                                location.getLongitude();

                                return true;
                            }
                        });

                // BOTON VER MAPA
                if (cod_map == 0) {

                    googleMap.addMarker(
                            new MarkerOptions()
                                    .title("Tienda 01")
                                    .snippet("Direccion de tienda 01")
                                    .position(new LatLng(-12.102162, -77.0292478))
                                    .icon(BitmapDescriptorFactory.defaultMarker()));

                    googleMap.addMarker(
                            new MarkerOptions()
                                    .title("Tienda 02")
                                    .snippet("Direccion de tienda 02")
                                    .position(new LatLng(-12.101365, -77.023637))
                                    .icon(BitmapDescriptorFactory.defaultMarker()));

                    googleMap.addMarker(
                            new MarkerOptions()
                                    .title("Tienda 03")
                                    .snippet("Direccion de tienda 03")
                                    .position(new LatLng(-12.107810, -77.024254))
                                    .icon(BitmapDescriptorFactory.defaultMarker()));
                    // BOTON MI UBICACION
                } else {
                    /*
                    LocationManager locationManager=(LocationManager) getSystemService(Context.LOCATION_SERVICE);
                    Criteria criteria= new Criteria();
                    Location location=locationManager.getLastKnownLocation(locationManager.getBestProvider(criteria,false));
                    double latitude=location.getLatitude();
                    double longitude=location.getLongitude();
                    */

                    googleMap.setMyLocationEnabled(true);
                }

                googleMap.moveCamera(
                        CameraUpdateFactory.newLatLngZoom(
                                new LatLng(-12.1021498, -77.0276599), 15));

            }
        });
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();
        overridePendingTransition(R.anim.zoom_forward_in, R.anim.zoom_forward_out);
        return super.onOptionsItemSelected(item);
    }
}
