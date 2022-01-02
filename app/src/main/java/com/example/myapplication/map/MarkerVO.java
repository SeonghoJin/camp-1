package com.example.myapplication.map;

import com.example.myapplication.R;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MarkerVO {
    public LatLng latLng;
    public String title;
    public String snippets;
    public String description;
    public int imageResourceId;
    int id;

    public MarkerVO(){

    }

    public static class Builder{
        MarkerVO marker;

        public Builder(){
            marker = new MarkerVO();
            marker.snippets = "";
            marker.title = "";
            marker.latLng = new LatLng(0,0);
            marker.id = 0;
            marker.description = "";
            marker.imageResourceId = R.drawable.no_image;
        }

        public Builder setLatLng(LatLng latLng){
            this.marker.latLng = latLng;
            return this;
        }

        public Builder setTitle(String title){
            this.marker.title = title;
            return this;
        }

        public Builder setSnippets(String snippets){
            this.marker.snippets = snippets;
            return this;
        }

        public Builder setId(int id){
            this.marker.id = id;
            return this;
        }

        public Builder setDescription(String description){
            this.marker.description = description;
            return this;
        }

        public Builder setResourceId(int id){
            this.marker.imageResourceId = id;
            return this;
        }

        public MarkerVO build(){
            return this.marker;
        }

    }

    static MarkerOptions toMarkerOptions(MarkerVO markerVO){
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(markerVO.latLng);
        markerOptions.title(markerVO.title);
        markerOptions.snippet(markerVO.snippets);
        return markerOptions;
    }

}
