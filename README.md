MadCamp (Week1)
======================

# 1. Introduction
## Project : Android app with 3 tabbed structure. 
* period: 2021.12.28 ~ 2022.01.04
* Contributors: [Seohyeon Kim](https://github.com/seohyeon0322), [Seongho Jin](https://github.com/SeonghoJin)

    
    
     

# 2. Project Description 
### Loading Activity
- Loading Activity 에서 모든 권한을 요청합니다. 
- 요청하는 권한들은 외부 저장소 작성, 카메라, 전화, 위치 정보 액세스 권한입니다.

### The Android app consists of 3 tabs. Each tab is 
## 2.1 Tab1: Phone (RecyclerView)
---
### Features
#### Feature 1. 연락처 추가
- 연락처 추가를 위해 이름을 적을 때 프로필 이미지가 변경되도록 구현했습니다.
- 연락처 추가를 할때 아무것도 입력되지 않을 경우 추가 버튼이 눌리지 않고, 회색 색깔이 되도록 구현했습니다. 
#### Feature 2. 연락처 삭제
#### Feature 3. 전화 
#### Feature 4. 레이아웃
- 전화와 연락처 삭제의 레이아웃은 swipelayout 라이브러리를 이용하여 구현했습니다.

---

전화 번호 정보를 저장하기 위해 "룸 데이터베이스"를, 정보를 효과적으로 보여주기 위해 리사이클러뷰를 사용했습니다.
우측 상단에 있는 추가 십자가 아이콘을 클릭하여 전화번호를 추가할 수 있습니다. 십자가를 클릭하면 밑에서 사용자 정보를 입력할 수 있는 
Dialog가 나타납니다. 썸네일은 사용자가 이름을 입력할 때 변경됩니다. 전화번호를 추가한 후 번호를 누르면 전화하거나 삭제할 수 있습니다.



## 2.2. Tab2: Gallery (GridView)
---
We used "Room Database" to save gallery informations (folders, and images). By introducing database, we added new activity for each folders. When the user click the folder, the gallery fragment sends folder informations to the activity, we could show the selected folder. 
* When we click gallery tab, it displays folders by gridview, and if there is no folder, it shows text message "앨범이 없습니다."
* By clicking "add icon", we can add folders, and we can take pictures in each folder. 
* There are two ways to add pictures (take pictures) in each folder. 
    * Long click a folder and select "앨범에 사진 추가"
    * Click a folder, and click add icon inside the folder.  

## 2.3. Tab3: Map 
---
We used "googlemap API" to show the map.
* When the user click the marker, blur fragment appears. The blur fragment contains the information 

# Design Reference

## loading image
<img src = "https://user-images.githubusercontent.com/87957569/148009627-03560bfd-4600-472e-880b-a1a6d9a6f5fe.png" width = "300" height = "300" >

from  instagram @kaist_hobby

# Libraries

- [com.google.android.material:material:1.4.0](https://github.com/material-components/material-components-android/releases)
  - bottom sheet dialog를 구현하기 위해 사용했습니다.
- [com.google.android.gms:play-services-maps:18.0.0](https://developers.google.com/android/guides/setup)
- [com.google.android.gms:play-services-location:18.0.0](https://developers.google.com/android/guides/setup)
  - 위 2개의 라이브러리는 지도 맵 구현을 위해 사용했습니다.
- [com.google.code.gson:gson:2.8.9](https://github.com/google/gson)
  - 배열로된 데이터를 저장하기 위해 사용했습니다.
- [androidx.room:room-runtime:2.4.0-alpha03](https://developer.android.com/jetpack/androidx/releases/room?hl=ko)
  - Room database를 적용하기 위해 사용했습니다. 
- [androidx.cardview:cardview:1.0.0](https://developer.android.com/jetpack/androidx/releases/cardview?hl=ko)
  - CardView를 구현하기 위해 사용했습니다.
- [com.chauthai.swipereveallayout:swipe-reveal-layout:1.4.1](https://github.com/chthai64/SwipeRevealLayout)
  - 탭 1에서 각 item을 스와이프 하는 UI를 구현하기 위해 사용했습니다.
- [com.eightbitlab:blurview:1.6.6](https://github.com/Dimezis/BlurView)
  - google map marker를 클릭했을 때 blur fragment를 띄우기 위해서 사용했습니다.
- [com.github.chrisbanes:PhotoView:2.1.4](https://github.com/Baseflow/PhotoView)
  - 사진의 크기 및 조절을 구현하기 위해 사용했습니다.
- [com.github.skydoves:expandablelayout:1.0.7](https://github.com/skydoves/ExpandableLayout)
  - 아코디언 UI를 구현하기 위해 사용했습니다. 

