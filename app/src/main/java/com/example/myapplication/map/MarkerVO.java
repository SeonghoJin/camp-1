package com.example.myapplication.map;

import com.google.android.gms.maps.model.LatLng;

public class MarkerVO {
    LatLng latLng;
    String title;
    String snippets;

    public MarkerVO(){

    }

    public static class Builder{
        MarkerVO marker;

        public Builder(){
            marker = new MarkerVO();
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

        public MarkerVO build(){
            return this.marker;
        }

    }

}
