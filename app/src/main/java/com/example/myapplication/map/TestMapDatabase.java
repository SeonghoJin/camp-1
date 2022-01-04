package com.example.myapplication.map;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.myapplication.R;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

public class TestMapDatabase implements MapDatabase{

    private ArrayList<MarkerVO> markerVOs = new ArrayList<MarkerVO>(Arrays.asList(
            new MarkerVO.Builder()
                .setLatLng(new LatLng(36.373946873823975, 127.36564915252725))
                .setTitle("N1")
                .setSnippets("KAIST 김병호ㆍ김삼열 IT융합 빌딩")
                .setId(0)
                .setDescription("김병호·김삼열 IT 융합빌딩(영어: Kim Beang-Ho Kim Sam-Youl ITC Building, 건물번호: N1)은 KAIST 본원 북동쪽 끝에 위치한 건물로, 전산학부, 전기및전자공학부가 함께 사용한다.")
                .setResourceId(R.drawable.n1)
                .build(),
            new MarkerVO.Builder()
                .setLatLng(new LatLng(36.37387634865722, 127.35840965396052))
                .setTitle("N14")
                .setSnippets("KAIST 사랑관")
                .setDescription("남자 기숙사")
                .setId(1)
                .setResourceId(R.drawable.saragan)
                .build(),
            new MarkerVO.Builder()
                .setLatLng(new LatLng(36.374231511390335, 127.35993973853955))
                .setTitle("풀빛 마루")
                .setDescription("음식점")
                .setResourceId(R.drawable.poolbitmaru)
                .setId(2)
                .build(),
            new MarkerVO.Builder()
                    .setLatLng(new LatLng(36.3739782648305, 127.35981698341466))
                    .setTitle("카이마루")
                    .setDescription("음식점")
                    .setResourceId(R.drawable.poolbitmaru)
                    .setInteriorImagesResourceIds(new int[]{R.drawable.n1, R.drawable.poolbitmaru, R.drawable.saragan})
                    .setId(5)
                    .build()
            )
    );

    @Override
    public ArrayList<MarkerVO> getMarkers() {
        return markerVOs;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public Optional<MarkerVO> findMakersById(int id) {
        return markerVOs.stream()
                .filter((markerVO -> markerVO.id == id))
                .findFirst();
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public Optional<MarkerVO> findMakersByTitle(String title) {
        return markerVOs.stream()
                .filter((markerVO -> markerVO.title.equals(title)))
                .findFirst();
    }


}
