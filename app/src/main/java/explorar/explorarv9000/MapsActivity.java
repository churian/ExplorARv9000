package explorar.explorarv9000;

import android.Manifest;
import android.app.IntentService;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.LauncherApps;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;

import com.google.android.gms.location.LocationRequest;

import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMyLocationButtonClickListener {

    private FusedLocationProviderClient mFusedLocationClient;
    private Location mCurrentLocation;
    private LocationRequest mLocationRequest;


    private GoogleMap mMap;

    private static final int REQUEST_PERMISSION_ACCESS_FINE_LOCATION = 1000; //This is an abitrary int that is used in onRequestPermissionsResult for handling permission results

    private static final int REQUEST_CHECK_SETTINGS = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        //Get Current Location Settings - https://developer.android.com/training/location/change-location-settings.html#prompt
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(mLocationRequest);

        SettingsClient client = LocationServices.getSettingsClient(this);
        Task<LocationSettingsResponse> task = client.checkLocationSettings(builder.build());

        //Prompt the User to Change Location Settings - https://developer.android.com/training/location/change-location-settings.html#prompt
        task.addOnSuccessListener(this, new OnSuccessListener<LocationSettingsResponse>() {
            @Override
            public void onSuccess(LocationSettingsResponse locationSettingsResponse) {
                // All location settings are satisfied. The client can initialize location requests here.
                // ...
            }
        });

        task.addOnFailureListener(this, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                int statusCode = ((ApiException) e).getStatusCode();
                switch (statusCode) {
                    case CommonStatusCodes.RESOLUTION_REQUIRED:
                        // Location settings are not satisfied, but this can be fixed
                        // by showing the user a dialog.
                        try {
                            // Show the dialog by calling startResolutionForResult(),
                            // and check the result in onActivityResult().
                            ResolvableApiException resolvable = (ResolvableApiException) e;
                            resolvable.startResolutionForResult(MapsActivity.this,
                                    REQUEST_CHECK_SETTINGS);
                        } catch (IntentSender.SendIntentException sendEx) {
                            // Ignore the error.
                        }
                        break;
                    case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                        // Location settings are not satisfied. However, we have no way
                        // to fix the settings so we won't show the dialog.
                        break;
                }
            }
        });
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

        //Create location service client
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        //Getting last known Location and storing it in a Location object
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            //Permission check failed
            Toast permissionsToast = new Toast(this);
            permissionsToast = Toast.makeText(this, "No permissions", Toast.LENGTH_LONG);
            permissionsToast.show();
            return;
        } else {
            mFusedLocationClient.getLastLocation()
                    .addOnSuccessListener(this, new OnSuccessListener<Location>() {

                        @Override
                        public void onSuccess(Location location) {
                            //Got last known location. In some rare situations this can be null
                            if (location != null) {
                                //Logic to handle location object - generate toast
                                Toast.makeText(getBaseContext(), "the last location is" + location, Toast.LENGTH_LONG).show();

                                //Set last known location to current location
                                mCurrentLocation = location;

                            }
                        }
                    });
        }
    }





//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        if(mRequestingLocationUpdates){ //pulled from onSaveInstanceState method overriden
//            startLocationUpdates();
//        }
//    }
//
//    @Override  //To define mRequestingLocationUpdates ("is the system requesting location updates") - followed https://developer.android.com/training/location/receive-location-updates.html#save-state
//    protected void onSaveInstanceState(Bundle outState) {
//        outState.putBoolean(REQUESTING_LOCATIONS_UPDATES_KEY,mRequestingLocationUpdates);
//        super.onSaveInstanceState(outState);
//    }
//
//    private void updateValuesFromBundle(Bundle savedInstanceState) {
//        //Update the value of mRequestingLocationUpdates from the Bundle
//        if (savedInstanceState.keySet().contains(REQUESTING_LOCATIONS_UPDATES_KEY)){
//            mRequestingLocationUpdates = savedInstanceState.getBoolean(REQUESTING_LOCATIONS_UPDATES_KEY);
//        }
//
//        //TODO: There is an "UpdateUI()" method called in example. Not sure if I need to do this? https://developer.android.com/training/location/receive-location-updates.html#save-state
//    }
//
//    private void startLocationUpdates() {
//
//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            //Permission check failed
//            Toast permissionsToast = new Toast(this);
//            permissionsToast = Toast.makeText(this, "No permissions", Toast.LENGTH_LONG);
//            permissionsToast.show();
//            return;
//        }
//        mFusedLocationClient.requestLocationUpdates(mLocationRequest, mLocationCallback, null);
//    }


    @Override  //Defines what happens when you click the location button
    public boolean onMyLocationButtonClick() {
        Toast.makeText(this, "MyLocation button clicked", Toast.LENGTH_LONG).show();
        return false;
    }

    //Set up location request - https://developer.android.com/training/location/change-location-settings.html#prompt
    protected void createLocationRequest() {
        LocationRequest mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(1000); //interval between location updates in milliseconds
        mLocationRequest.setFastestInterval(5000); //fastest interval = xxx milliseconds per request
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }

}

