package explorar.explorarv9000;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMyLocationButtonClickListener {

    private FusedLocationProviderClient mFusedLocationClient;
    private GoogleMap mMap;
    private static final int REQUEST_PERMISSION_ACCESS_FINE_LOCATION = 1000; //This is an abitrary int that is used in onRequestPermissionsResult for handling permission results

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //Create location service client
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        //Getting last known location
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Toast permissionsToast = new Toast(this);
            permissionsToast = Toast.makeText(this, "No permissions", Toast.LENGTH_LONG);
            permissionsToast.show();
            return;
        }
        else {
            mFusedLocationClient.getLastLocation()
                    .addOnSuccessListener(this, new OnSuccessListener<Location>() {

                        @Override
                        public void onSuccess(Location location) {

                            //Got last known location. In some rare situations this can be null
                            if (location != null) {
                                //Logic to handle location object
                                Toast.makeText(MapsActivity.this, "the last location is" + location, Toast.LENGTH_LONG).show();

                            }
                        }
                    });
        }
    }

    @Override //This handles the permission selection by user
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) { //LEARNING: @NonNull is just a marker notation and has no tangible attributes.
        switch (requestCode) {
            case REQUEST_PERMISSION_ACCESS_FINE_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) { // permission was granted, yay! Do the location-related task you need to do.

                    //Check if permissions are granted
                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        //Not granted - return
                        return;
                    } else {
                        //Granted - Enable location sharing
                        mMap.setMyLocationEnabled(true);
                    }

                } else { // permission denied, boo! Disable the functionality that depends on this permission.

                    //Disable location sharing
                    mMap.setMyLocationEnabled(false);

                }
                return;
            }
        }
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
        mMap.moveCamera(CameraUpdateFactory.zoomTo(14.0f)); //TODO: Make the zoom look pretty
        //TODO: Would be nice to twist the orientation as well so that you look up main walkway


        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-33.919728, 151.234095);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in UNSW"));

        //Checking for location permissions and enabling current location or requesting necessary permissions
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_PERMISSION_ACCESS_FINE_LOCATION);

        //Remove the location icon on the top right
        //mMap.getUiSettings().setMyLocationButtonEnabled(false); //TODO : Once finished, Remove the location button in the top right

        //Setting Toast to appear on location button click
        mMap.setOnMyLocationButtonClickListener(this);







//        createLocationRequest(); Not sure if legit
//        https://developer.android.com/training/location/receive-location-updates.html
//        https://developer.android.com/training/location/change-location-settings.html
//        TODO: you're working on figuring how to make constant location updates
    }

    @Override
    public boolean onMyLocationButtonClick() {
        Toast.makeText(this, "MyLocation button clicked", Toast.LENGTH_LONG).show();
        return false;
    }


//    protected void createLocationRequest() {
//        LocationRequest mLocationRequest = new LocationRequest();
//        mLocationRequest.setInterval(10000);
//        mLocationRequest.setFastestInterval(5000);
//        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
//    }

}


//current location

//need to build listener

