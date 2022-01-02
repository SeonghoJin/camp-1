package com.example.myapplication.map;

import java.util.ArrayList;
import java.util.Optional;

public interface MapDatabase {
    ArrayList<MarkerVO> getMarkers();
    Optional<MarkerVO> findMakersById(int id);
    Optional<MarkerVO> findMakersByTitle(String title);
}
