package com.atchyutmaddukuri.mapsclustering.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.atchyutmaddukuri.mapsclustering.R;
import com.atchyutmaddukuri.mapsclustering.models.MarkerContent;
import com.atchyutmaddukuri.mapsclustering.models.MarkerItem;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterManager;
import com.google.maps.android.clustering.view.DefaultClusterRenderer;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,
        GoogleMap.OnCameraIdleListener,
        ClusterManager.OnClusterClickListener<MarkerItem>,
        ClusterManager.OnClusterItemClickListener<MarkerItem> {

    private GoogleMap mMap;
    private ClusterManager<MarkerItem> mClusterManager;
    ArrayList<MarkerContent> markerContents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        createMarkerData();
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Creates some dummy markers array list. You need to get your own ArrayList of markers from
     * your API calls.
     */
    public void createMarkerData() {
        markerContents = new ArrayList<>();
        //Creates 6 nearby markers in France
        for (int i = 0; i < 6; i++) {
            MarkerContent markerContent = new MarkerContent();
            markerContent.setLat(48.871422 + i);
            markerContent.setLon(2.344306 - i);
            markerContent.setTitle("Marker" + i);
            markerContents.add(markerContent);
        }
        //Creates 6 nearby markers in Spain
        for (int i = 0; i < 5; i++) {
            MarkerContent markerContent = new MarkerContent();
            markerContent.setLat(17.412955 + i);
            markerContent.setLon(78.437563 + i);
            markerContent.setTitle("Marker" + i);
            markerContents.add(markerContent);
        }
        //Creates 6 nearby markers in India
        for (int i = 0; i < 8; i++) {
            MarkerContent markerContent = new MarkerContent();
            markerContent.setLat(38.403673 - i);
            markerContent.setLon(5.300630 + i);
            markerContent.setTitle("Marker");
            markerContents.add(markerContent);
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
        mMap = googleMap;
        mClusterManager = new ClusterManager<>(this, googleMap);
        mClusterManager.setRenderer(new DefaultClusterRenderer<MarkerItem>(this,
                googleMap, mClusterManager));

        googleMap.setOnCameraIdleListener(new GoogleMap.OnCameraIdleListener() {
            @Override
            public void onCameraIdle() {
                clusterMarkers();
            }
        });
    }

    /**
     * Iterates the list of Markers and add the markers to ClusterManager.
     * Cluster Manager class will take care of grouping markers to cluster.
     */
    private void clusterMarkers() {
        mClusterManager.clearItems();
        for (int i = 0; i < markerContents.size(); i++) {
            double lat = markerContents.get(i).getLat();
            double lon = markerContents.get(i).getLon();
            MarkerContent mContent = markerContents.get(i);
            mClusterManager.addItem(new MarkerItem(lat, lon, "", "", mContent));
        }
        mClusterManager.cluster();
    }

    @Override
    public void onCameraIdle() {

    }

    @Override
    public boolean onClusterClick(Cluster<MarkerItem> cluster) {
        return false;
    }

    @Override
    public boolean onClusterItemClick(MarkerItem markerItem) {
        return false;
    }
}
