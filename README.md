MadCamp (Week1)
======================

# 1. Introduction
## Project : Android app with 3 tabbed structure. 
* period: 2021.12.28 ~ 2022.01.04
* Contributors: [Seohyeon Kim](https://github.com/seohyeon0322), [Seongho Jin](https://github.com/SeonghoJin)

    
    
     

# 2. Project Description 
### The Android app consists of 3 tabs. Each tab is 
## 2.1 Tab1: Phone (RecyclerView)
---
We used "Room Database" to save Phone number informations, and RecyclerView to show informations effectively. 
* We can add phone number by clicking add icon in the upper right. Thumbnail changes as the user types the name. 
* After adding the phone number, we can call or delete the number by swiping the number. 


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
- [com.google.android.gms:play-services-maps:18.0.0](https://developers.google.com/android/guides/setup)
- [com.google.android.gms:play-services-location:18.0.0](https://developers.google.com/android/guides/setup)
- [com.google.code.gson:gson:2.8.9](https://github.com/google/gson)
- [androidx.room:room-runtime:2.4.0-alpha03](https://developer.android.com/jetpack/androidx/releases/room?hl=ko)
- [androidx.cardview:cardview:1.0.0](https://developer.android.com/jetpack/androidx/releases/cardview?hl=ko)
- [com.chauthai.swipereveallayout:swipe-reveal-layout:1.4.1](https://github.com/chthai64/SwipeRevealLayout)
- [com.eightbitlab:blurview:1.6.6](https://github.com/Dimezis/BlurView)
- [com.github.chrisbanes:PhotoView:2.1.4](https://github.com/Baseflow/PhotoView)
- [com.github.skydoves:expandablelayout:1.0.7](https://github.com/skydoves/ExpandableLayout)