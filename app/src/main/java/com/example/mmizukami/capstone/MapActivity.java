package com.example.mmizukami.capstone;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceLikelihood;
import com.google.android.gms.location.places.PlaceLikelihoodBuffer;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class MapActivity extends AppCompatActivity
        implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {

    private static final int REQUEST_CODE_ACCESS_FINE_LOCATION = 573;

    private ArrayList<Location> shelterLocationsList;
    // List of Location?
    private GoogleMap shelterMap;
    private GoogleApiClient shelterGoogleApiClient;

    // Member variable to store location requests (how often to update)
    private LocationRequest mLocationRequest;
    // Member variable to store our current location
    private Location myLocation;

    // TODO: Create Marker dog_marker

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.shelterMapFragment);
        mapFragment.getMapAsync(this);

        // Building new GoogleApiClient if it doesn't exist already
        if (shelterGoogleApiClient == null) {
            shelterGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }

        shelterGoogleApiClient.connect();

        mLocationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(20000)
                .setFastestInterval(10000);
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_CODE_ACCESS_FINE_LOCATION);
        }
        myLocation = LocationServices.FusedLocationApi.getLastLocation(shelterGoogleApiClient);
        if (myLocation != null) {
            myLocation.setLatitude(33.671028);
            myLocation.setLongitude(-117.911305);
        }
        LocationServices.FusedLocationApi.requestLocationUpdates(shelterGoogleApiClient, mLocationRequest, this);

        handleNewLocation(myLocation);
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.e("Pet Protector", "Suspended connection from Google Play Services.");
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.e("Pet Protector", "Failed connection to Google Play Services.");
    }

    @Override
    public void onLocationChanged(Location location) {
        handleNewLocation(location);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        shelterMap = googleMap;
        handleNewLocation(myLocation);
    }

    private void handleNewLocation(Location newLocation) {
        shelterMap.clear();
        myLocation = newLocation;

        LatLng myCoordinate = new LatLng(myLocation.getLatitude(), myLocation.getLatitude());
        shelterMap.addMarker(new MarkerOptions()
                .position(myCoordinate)
                .title("Current Location")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.user_location_marker)));
        CameraPosition cameraPosition = new CameraPosition.Builder().target(myCoordinate).zoom(14.0f).build();
        CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);
        shelterMap.moveCamera(cameraUpdate);



        // At this point, the check's already been made against whether we can even make the service
        // calls. It's redundant to check again, but not suppressing the warnings results in an error.
        @SuppressWarnings("MissingPermission")


        // Get the businesses and other points of interest located
        // nearest to the device's current location.
        PendingResult<PlaceLikelihoodBuffer> result = Places.PlaceDetectionApi
                .getCurrentPlace(shelterGoogleApiClient, null);
        result.setResultCallback(new ResultCallback<PlaceLikelihoodBuffer>() {
            @Override
            public void onResult(@NonNull PlaceLikelihoodBuffer likelyPlaces) {
                for (PlaceLikelihood placeLikelihood : likelyPlaces) {
                    // Add a marker for each place near the device's current location, with an
                    // info window showing place information.
                    List<Integer> placeTypes = placeLikelihood.getPlace().getPlaceTypes();
                    for (Integer type : placeTypes)
                    {
                        // Check to see if
                        switch (type) {
                            case Place.TYPE_PET_STORE :
                            case Place.TYPE_VETERINARY_CARE:
                                String attributions = (String) placeLikelihood.getPlace().getAttributions();
                                String snippet = (String) placeLikelihood.getPlace().getAddress();

                                shelterMap.addMarker(new MarkerOptions()
                                        .position(placeLikelihood.getPlace().getLatLng())
                                        .title((String) placeLikelihood.getPlace().getName()));
                                // TODO: Configure markers so that they show up with the custom Marker image
                                // TODO: (in this case, drawable/dog_marker)
                                break;
                        }
                    }

                }
                // Release the place likelihood buffer.
                likelyPlaces.release();
            }
        });
    }
}
