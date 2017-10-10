package com.example.proyecto.aplicacion_docente.Actividades;


import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.example.proyecto.aplicacion_docente.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;

import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;

import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by Daniel on 27/04/2017.
 */

public class ubicacion extends FragmentActivity implements OnMapReadyCallback{
    private GoogleMap mMap;
    private Marker marcador;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        Puntos(googleMap);
    }


    public void Puntos (GoogleMap googleMap) {
        mMap = googleMap;

        final LatLng punto1 = new LatLng(39.5697838,-0.3286616);
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        //final LatLng punto2 = new LatLng(38.4190531,-3.6936194);
        mMap.addMarker(new MarkerOptions().position(punto1).title("Colegio").icon(BitmapDescriptorFactory.fromResource(R.drawable.marcadorcole)).snippet( "CEIP San Juan Evangelista ").alpha(0.8f));
        //mMap.addMarker(new MarkerOptions().position(punto2).title("Punto2").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
         mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(punto1,15));

    }

   /* public static final LatLng CEIP= new LatLng(39.5697838,-0.3286616);
    private GoogleMap mMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maps);


        setUpMapIfNeeded();
        setMarker(CEIP, "CEIP San Juan Evangelista", "Calle Baldomero García\n" +
                "46130 Massamagrell\n" +
                "Valencia"); // Agregamos el marcador verde
    }






    private void setUpMapIfNeeded() {
// Configuramos el objeto GoogleMaps con valores iniciales.
        if (mMap == null) {
            //Instanciamos el objeto mMap a partir del MapFragment definido bajo el Id "map"
         //no se por que falla   mMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
            // Chequeamos si se ha obtenido correctamente una referencia al objeto GoogleMap
            if (mMap != null) {
                // El objeto GoogleMap ha sido referenciado correctamente
                //ahora podemos manipular sus propiedades

                //Seteamos el tipo de mapa
                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

                //Activamos la capa o layer MyLocation
             //sigue fallando   mMap.setMyLocationEnabled(true);
            }
        }
    }
    private void setMarker(LatLng position, String titulo, String info) {
        // Agregamos marcadores para indicar sitios de interéses.
        Marker myMaker = mMap.addMarker(new MarkerOptions()
                .position(position)
                .title(titulo)  //Agrega un titulo al marcador
                .snippet(info)   //Agrega información detalle relacionada con el marcador
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))); //Color del marcador
    }*/
}
