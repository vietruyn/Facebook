package info.androidhive.listviewfeed.checkin;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import info.androidhive.listviewfeed.R;


/**
 * Created by Rom on 4/14/2015.
 */
public class MapPlaces extends FragmentActivity implements GoogleMap.OnMapLoadedCallback {

    private GoogleMap mMap;
    private ProgressDialog mProgress;
    public static double lngStation =0.0;
    public static double latStation =0.0;

    GPSTracker gps;

    private String url = "https://maps.googleapis.com/maps/api/place/search/" +
            "json?location=16.0151544,108.1985165&radius=10000&rankBy=distance" +
            "&types=atm&sensor=false&key=AIzaSyBimSeIHlcJaHkGH2H1Z65d5Wb7osNxjCc";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_maps_station);
        setUpMapIfNeeded();
        initProgress();
    }

    private void initProgress() {
        mProgress = new ProgressDialog(this);
        mProgress.setTitle("Loading Map ...");
        mProgress.setMessage("Please wait...");
        mProgress.setCancelable(true);
        mProgress.show();
    }


    @Override
    public void onMapLoaded() {
        mProgress.dismiss();

    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.frgMap))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }
    private void setUpMap() {
        gps = new GPSTracker(MapPlaces.this);
        LatLng my = new LatLng(gps.getLatitude(), gps.getLongitude());

//        LatLng sation = new LatLng(latStation,lngStation);

        mMap.setMyLocationEnabled(true);
//        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sation, 12));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(my, 12));
        mMap.addMarker(new MarkerOptions()
                .title("Hello")
                .snippet("I am Here.")
                .position(my));
        mMap.animateCamera(CameraUpdateFactory.zoomIn());

    }
}

