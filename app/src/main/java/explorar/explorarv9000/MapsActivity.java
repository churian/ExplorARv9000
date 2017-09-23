package explorar.explorarv9000;

import android.Manifest;

import android.content.IntentSender;
import android.content.pm.PackageManager;

import android.location.Location;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;

import com.google.android.gms.common.api.GoogleApiClient;

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


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMyLocationButtonClickListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, com.google.android.gms.location.LocationListener {

    private GoogleApiClient mGoogleApiClient;
    public static final String TAG = MapsActivity.class.getSimpleName();
    private final static int CONNECTION_FAILURE_RESOLUTION_REQUEST = 9000;
    private LocationRequest mLocationRequest;

    private static final int DEFAULT_ZOOM = 15;


    private FusedLocationProviderClient mFusedLocationClient;
    private Location mCurrentlocation;
//    private LocationCallback mLocationCallback;

    private GoogleMap mMap;

    private static final int REQUEST_PERMISSION_ACCESS_FINE_LOCATION = 1000; //This is an abitrary int that is used in onRequestPermissionsResult for handling permission results

//    private static final int REQUEST_CHECK_SETTINGS = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        // Create the LocationRequest object
        mLocationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(1 * 1000)        // 1 second, in milliseconds
                .setFastestInterval(1 * 1000); // 1 second, in milliseconds

        //Instantiate mGoogleApiClient
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
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

        //set default camera to UNSWlatlng and move the camera
        LatLng unswLatLng = new LatLng(-33.917378, 151.230205);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(unswLatLng));
        mMap.moveCamera(CameraUpdateFactory.zoomTo(14.0f)); //TODO: Make the zoom look pretty
        //TODO: Would be nice to twist the orientation as well so that you look up main walkway

        // Add a marker in Sydney
        LatLng sydney = new LatLng(-33.919728, 151.234095);
        mMap.addMarker(new MarkerOptions().position(sydney).title("This is an example event"));

        //Checking for location permissions and enabling current location or requesting necessary permissions
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_PERMISSION_ACCESS_FINE_LOCATION);

        //Remove the location icon on the top right
        //mMap.getUiSettings().setMyLocationButtonEnabled(false); //TODO : Once finished and if location is automatic, Remove the location button in the top right

        //Setting Toast to appear on location button click
        mMap.setOnMyLocationButtonClickListener(this);


//                //Create location service client
//        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
////
//        //Getting last known Location and storing it in a Location object
//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            //Permission check failed
//            Toast permissionsToast = new Toast(this);
//            permissionsToast = Toast.makeText(this, "No permissions", Toast.LENGTH_LONG);
//            permissionsToast.show();
//            return;
//        } else {
//            mFusedLocationClient.getLastLocation()
//                    .addOnSuccessListener(this, new OnSuccessListener<Location>() {
//
//                        @Override
//                        public void onSuccess(Location location) {
//                            //Got last known location. In some rare situations this can be null
//                            if (location != null) {
//                                //Logic to handle location object - generate toast
//                                Toast.makeText(getBaseContext(), "the last location is" + location, Toast.LENGTH_LONG).show();
//
//                                //Set last known location to current location
//                                mCurrentlocation = location;
//
//                            }
//                        }
//                    });
//        }
//        //Plotting last known location into map
//        handleNewLocation(mCurrentlocation);

    }

    @Override  //Defines what happens when you click the location button
    public boolean onMyLocationButtonClick() {
        Toast.makeText(this, "MyLocation button clicked", Toast.LENGTH_LONG).show();
        Log.d(TAG, "onMyLocationButtonClick Button Clicked");

        if (mGoogleApiClient != null) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
            else{
                Log.i(TAG, "LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this); evoked");
                LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
            }
        }
        return false;

    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        Log.i(TAG, "Location services connected.");
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        } else {

            mCurrentlocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
//            if (location == null) {
                LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
//            } else {
//                handleNewLocation(location);
//            }
            handleNewLocation(mCurrentlocation);


        }
    }

    private void handleNewLocation(Location location) {
        Log.d(TAG, location.toString());
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
                new LatLng(location.getLatitude(),
                        location.getLongitude()),
                DEFAULT_ZOOM
        ));

    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.i(TAG, "Location services suspended. Please reconnect.");
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        if (connectionResult.hasResolution()) {
            try {
                // Start an Activity that tries to resolve the error
                connectionResult.startResolutionForResult(this, CONNECTION_FAILURE_RESOLUTION_REQUEST);
            } catch (IntentSender.SendIntentException e) {
                e.printStackTrace();
            }
        } else {
            Log.i(TAG, "Location services connection failed with code " + connectionResult.getErrorCode());
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded(); //Maybe useless
        mGoogleApiClient.connect();
    }

    private void setUpMapIfNeeded(){ //Maybe useless
    Log.i (TAG, "setUpMapIfNeeded evoked");


    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mGoogleApiClient.isConnected()) {
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
            mGoogleApiClient.disconnect();
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        handleNewLocation(location);
    }

}



//TODO: Make the user's location appear upon opening of the app


/*
        //Place current location marker
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title("Current Position");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
        mCurrLocationMarker = mMap.addMarker(markerOptions);



//

--in oncreate--
        //Get Current Location Settings - https://developer.android.com/training/location/change-location-settings.html#prompt
//        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
//                .addLocationRequest(mLocationRequest);
//
//        SettingsClient client = LocationServices.getSettingsClient(this);
//        Task<LocationSettingsResponse> task = client.checkLocationSettings(builder.build());
//
//        //Prompt the User to Change Location Settings - https://developer.android.com/training/location/change-location-settings.html#prompt
//        task.addOnSuccessListener(this, new OnSuccessListener<LocationSettingsResponse>() {
//            @Override
//            public void onSuccess(LocationSettingsResponse locationSettingsResponse) {
//                // All location settings are satisfied. The client can initialize location requests here.
//                // ...
//            }
//        });
//
//        task.addOnFailureListener(this, new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                int statusCode = ((ApiException) e).getStatusCode();
//                switch (statusCode) {
//                    case CommonStatusCodes.RESOLUTION_REQUIRED:
//                        // Location settings are not satisfied, but this can be fixed
//                        // by showing the user a dialog.
//                        try {
//                            // Show the dialog by calling startResolutionForResult(),
//                            // and check the result in onActivityResult().
//                            ResolvableApiException resolvable = (ResolvableApiException) e;
//                            resolvable.startResolutionForResult(MapsActivity.this,
//                                    REQUEST_CHECK_SETTINGS);
//                        } catch (IntentSender.SendIntentException sendEx) {
//                            // Ignore the error.
//                        }
//                        break;
//                    case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
//                        // Location settings are not satisfied. However, we have no way
//                        // to fix the settings so we won't show the dialog.
//                        break;
//                }
//            }
//        });
 */