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
                .setInteriorImagesResourceIds(new int[]{
                        R.drawable.n1_interior_1,
                        R.drawable.n1_interior_2,
                        R.drawable.n1_interior_3
                })
                .build(),
            new MarkerVO.Builder()
                .setLatLng(new LatLng(36.37387634865722, 127.35840965396052))
                .setTitle("N14")
                .setSnippets("KAIST 사랑관")
                .setDescription("남자 기숙사입니다. 수용인원인 408명, 면적은 7평 정도 입니다. 편의 시설로는 각층 휴게실, 세탁실이 존재합니다. 생활관 비는 101,000원/월/인이며 특징으로는 학사과정 신입생 배정, 카이 라운지 인접, 개별 에어콘, 온돌, 무인 택배 시스템이 설치되어 있습니다.")
                .setId(1)
                .setResourceId(R.drawable.saragan)
                .setInteriorImagesResourceIds(new int[]{
                        R.drawable.saranggwan_interior_1,
                        R.drawable.saranggwan_interior_3
                })
                .build(),
            new MarkerVO.Builder()
                    .setLatLng(new LatLng(36.3739782648305, 127.35981698341466))
                    .setTitle("카이마루")
                    .setDescription("음식점")
                    .setResourceId(R.drawable.kaimaru)
                    .setInteriorImagesResourceIds(new int[]{
                            R.drawable.kaimaru_interior_1
                    })
                    .setDescription("★운영시간★ 1. 중식: 11:30 ~ 13:30 2. 석식: 17:30 ~ 19:00 ★금액★ 1. 중식(자율배식: 4,500원/일품: 메뉴에 따라 상이 2. 석식(자율배식: 4,500원) ★알레르기 유발물질 안내★ 메뉴에 표시된 성분 코드를 확인해주세요. [1.난류 2.우유 3.메밀 4.땅콩 5.대두 6.밀 7.고등어 8.게 9.새우 10.돼지고기 11.복숭아 12.토마토 13.아황산류 14.호두 15.닭고기 16.쇠고기 17.오징어 18.조개류 (굴, 전복, 홍합 포함)19.잣] 인스타그램: gaon_kaist_n11")
                    .setId(2)
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
