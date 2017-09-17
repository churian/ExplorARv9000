package explorar.explorarv9000;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
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
        //set mMap to GoogleMap view
        mMap = googleMap;

        //set default camera to UNSW latlong
        LatLng unsw = new LatLng(-33.919728, 151.234095);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(unsw));
        mMap.moveCamera(CameraUpdateFactory.zoomTo(14.0f)); //you must optimise this


        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-33.919728, 151.234095);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in UNSW"));
    }
}


//need to build listener

//need to build zoom

//would be nice to build orientation as well