package com.atchyutmaddukuri.mapsclustering.models;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterItem;

/**
 * Created by QuaQua on 15-May-18.
 */

public class MarkerItem implements ClusterItem {
    private final LatLng mPosition;
    private String mTitle;
    private String mSnippet;
    private MarkerContent mContent;

    public MarkerContent getmContent() {
        return mContent;
    }

    public void setmContent(MarkerContent mContent) {
        this.mContent = mContent;
    }

    public MarkerItem(double lat, double lng) {
        mPosition = new LatLng(lat, lng);
        mTitle = null;
        mSnippet = null;
    }

    public MarkerItem(double lat, double lng, String title) {
        mPosition = new LatLng(lat, lng);
        mTitle = title;
    }

    public MarkerItem(double lat, double lng, String title, String snippet, MarkerContent content) {
        mPosition = new LatLng(lat, lng);
        mTitle = title;
        mSnippet = snippet;
        mContent = content;
    }

    @Override
    public LatLng getPosition() {
        return mPosition;
    }

    @Override
    public String getTitle() {
        return mTitle;
    }

    @Override
    public String getSnippet() {
        return mSnippet;
    }

}