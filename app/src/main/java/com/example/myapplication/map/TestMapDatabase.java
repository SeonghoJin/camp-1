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
                .setDescription("****TIP****" +
                        "\n Tip1: 2층에는 학교 외부보다 20% 저렴한 투썸플레이스가 있다!" +
                        "\n Tip2: 투썸플레이스를 통해 건물에 들어오는 경우 2층으로 들어오게 되므로 헷갈리지 않도록 유의해야한다! (편집자는 많이 헷갈렸습니다..)" +
                        "\n Tip3: 동문 쪽으로 나가면 있는 버스 정류소에서 604번 버스를 타면 갤러리아에 한 번에 갈 수 있다!" +
                        "\n\n 김병호·김삼열 IT 융합빌딩(영어: Kim Beang-Ho Kim Sam-Youl ITC Building, 건물번호: N1)은 KAIST 본원 북동쪽 끝에 위치한 건물로, 전산학부, 전기및전자공학부가 함께 사용한다.")
                .setResourceId(R.drawable.n1)

                .setInteriorImagesResourceIds(new int[]{
                        R.drawable.n1_interior_1,
                        R.drawable.n1_interior_2,
                        R.drawable.n1_interior_3
                })
                .build(),
            new MarkerVO.Builder()
                .setLatLng(new LatLng(36.37335982813255, 127.36057673629902))
                .setTitle("E13-1")
                .setSnippets("KAIST 장영신 학생회관")
                .setDescription("****TIP****" +
                        "\n Tip1: 시험기간에는 1층 공부할 수 있는 공간 자리가 없으므로 일찍 오도록 하자!" +
                        "\n Tip2: 신학관 옥상에 테이블과 의자가 있어, 배달 음식을 먹기 좋다!" +
                        "\n Tip3: 2층에는 태울관과 연결되는 통로가 있다!" +
                        "\n Tip4: 신학관 3층 화장실에는 샤워실이 있다!" +
                        "\n Tip5: 신학 2층으로 나오면 옆에 공간이 있는데 종종 버스킹을 한다!" +
                        "\n\nKAIST 중앙에 있는 학생회관이다. 줄여서 보통 '신학관'이라고 부른다. 1층에는 공부할 수 있는 공간이 있다. 뿐만 아니라 피아노 연습, 노래 연습, 춤 연습 등을 할 수 있는 다양한 공간들이 존재한다." +
                        "\n 2층에는 롯데리아가 있다. 많은 학생들이 사용하며 KAIST 롯데리아가 전국 매장 매출 1위라고한다. 또한, 음료를 마시며 책을 읽거나 공부를 할 수 있는 '책다방'이 있는데 현재에는 코로나로 인하여 폐쇠된 상태이다. " +
                        "\n 3층에는 여러 상설위원회의 단실이 존재하며, 세미나실이 있다." )
                .setId(1)
                .setResourceId(R.drawable.sinhak)
                .setInteriorImagesResourceIds(new int[]{
                        R.drawable.sinhak1,
                        R.drawable.sinhak2
                })
                .build(),
            new MarkerVO.Builder()
                    .setLatLng(new LatLng(36.3739782648305, 127.35981698341466))
                    .setTitle("카이마루")
                    .setDescription("음식점")
                    .setResourceId(R.drawable.kaimaru)
                    .setInteriorImagesResourceIds(new int[]{
                            R.drawable.kaimaru00,
                            R.drawable.kaimaru11,
                            R.drawable.kaimaru22
                    })
                    .setDescription("****TIP****" +
                            "\nTip1: 식사 시간보다 조금 빨리 가면 쾌적하게 먹을 수 있다!" +
                            "\n Tip2: 월요일은 '쥬스킹 데이'라고 하여 지정된 음료를 30&할인된 가격에 즐길 수 있다!" +
                            "\n Tip3: 쥬스킹 과일컵은 맛있다!" +
                            "\n\n 편집자1 최애 메뉴: 오늘도 든든! 된장찌개와 계란밥, 솥밥한상(사라진 것 같네요 ...)" +
                            "\n 편집자2 최애 메뉴: 다 맛있더라고요....."+
                            "\n\n 학부생들의 주요 식사 공간이다. 북측 기숙사 가운데에 위치하여, 북측 기숙사를 사용한다면 주로 카이마루를 이용하게 된다. 중앙에 대표 학식 카페테리아가 존재하여 배식을 받아 식사를 할 수 있다." +
                            "카페테리아를 중심으로 양 옆에는 다양한 음식점들이 입점해있다. 왼쪽에는 '오늘도 든든', '휴김밥', '리틀 하노이'가 있고 왼쪽에는 '웰차이', '캠토'가 있다. 쥬스 매장 '쥬스킹'도 입점하여 있으며 저렴한 가격에 음료를 즐길 수 있다. ")
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
