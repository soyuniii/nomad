
## 📎 프로젝트 개요
이 프로젝트는 한국을 방문한 외국인 여행자들이 반경 5km 이내에서 자국 음식을 판매하는 음식점을 손쉽게 찾을 수 있도록 도와주는 서비스입니다. 사용자는 현재 위치를 기반으로 주변의 자국 음식점을 추천받고, 상세 정보를 확인할 수 있고, 음식점 이용 후, 리뷰를 작성할 수 있습니다. 

<br>


## 📎 기획 배경 
한국을 방문하는 외국인 관광객들이 증가함에 따라, 현지 음식에 익숙하지 않거나 자국의 음식이 그리운 여행자들이 많아졌습니다. 하지만 원하는 음식을 파는 식당을 찾기 어렵거나 정보가 부족한 경우가 많습니다. 이를 해결하기 위해 위치 기반 추천 시스템을 활용하여 가까운 자국 음식점을 지도에 표시하여 편리하게 음식점을 찾을 수 있도록 본 프로젝트를 기획하였습니다. 또한, 리뷰 기능을 제공하여 방문한 음식점에 대한 실제 평가를 공유함으로써, 신뢰할 수 있는 정보를 제공하고 보다 만족스러운 식사 경험을 도울 수 있도록 만들었습니다.


### 개발자 소개 



<br> 


## 🚀 주요 기능

### ⭐️ 회원가입 및 로그인(세션 기반 인증)
<img src="https://github.com/user-attachments/assets/1b83c78f-6993-4300-b635-cad113b7432f" height = 500>


<br>


### 📍 위치 기반 음식점 추천
- 사용자의 현재 위치를 기반으로 반경 5km 내 자국 음식점 추천
- 🗾 React Native Maps를 활용한 지도 표시


- #### 🇻🇳 베트남 음식점
<img src="https://github.com/user-attachments/assets/2e057893-93e5-4e55-ad32-87a7a64804ea" height = 500> 
<img src="https://github.com/user-attachments/assets/b37a9f7d-4709-4607-a8b5-8076af49101a" height = 500>


- #### 🇮🇹 이탈리아 음식점 
<img src="https://github.com/user-attachments/assets/ce4beab3-6811-4eff-ab62-0e66ea18c163" height = 500> 
<img src="https://github.com/user-attachments/assets/f5e3a90c-3555-49c0-973d-ef062477c8d8" height = 500>


<br>


### 📝 리뷰 작성 및 평점 시스템
- 방문한 음식점에 대한 리뷰 작성 및 별점 평가 기능

- 리뷰 작성
<img src="https://github.com/user-attachments/assets/aede1c44-8b3c-43f6-b3a1-26f19e1bcf5a" height = 500>
<img src="https://github.com/user-attachments/assets/6bbeadfa-b307-4d21-ad37-d31a313f45e4" height = 500>


- #### 프로필 -> 내 리뷰 조회 
<img src="https://github.com/user-attachments/assets/3be6cf4b-55e7-439d-bbfb-85c21b5135ef" height = 500>
<img src="https://github.com/user-attachments/assets/3f9b7f12-50d5-407a-8f0f-06ec29b89963" height = 500>



- #### 프로필 -> 리뷰 수정 및 삭제
<img src="https://github.com/user-attachments/assets/33c51711-b566-481b-8e1c-8a3b19d2c830" height = 500>
<img src="https://github.com/user-attachments/assets/c7a5680c-5e83-4163-91e7-b97c78987ac2" height = 500>


<br>



### 🔍 검색 기능 (해시태그 기반 필터링)
- 음식점의 리뷰 내 해시태그 분석을 통해 검색 결과 제공
<img src="https://github.com/user-attachments/assets/a7f45eef-6276-495b-8ebd-512bee98b495" height = 500>
<img src="https://github.com/user-attachments/assets/28d1b9d6-ebe7-4651-b10c-977cdfe8f6f5" height = 500>


<br>


### 👤 프로필 조회 
<img src="https://github.com/user-attachments/assets/c88d24b5-bb52-4b39-bf52-d1864e448064" height = 500>


<br>


#### 📷 음식점 사진 제공
- 🌎 Google Places API에서 음식점 사진을 가져와 표시
- ☁️ S3에 저장하여 이미지 캐싱 및 최적화


<br> 


## 📕 활용 데이터 
이 프로젝트에서는 한국문화정보원에서 제공하는 **전국 세계음식점 데이터(CSV)**를 활용하였습니다.


-> https://www.bigdata-culture.kr/bigdata/user/data_market/detail.do?id=43de70c0-0337-11ee-a67e-69239d37dfae


<br> 


## 📕 API 명세서
수정하고 추가하기

<br>


## 📌 고찰 
1️⃣ React Native의 기본 지도(react-native-maps)는 현재 위치를 자동으로 가져오지 않아, react-native-geolocation-service를 사용해 위치 정보를 받아와 연동했다. 그러나 GPS 신호가 약하거나 위치 권한이 제한된 경우 정확도가 떨어지는 문제가 있었다. 따라서 임시방편으로 직접 위치를 지정해서 연동했고, 추후에 사용자가 직접 위치를 설정할 수 있는 기능도 제공하여 보다 안정적인 검색이 가능하도록 개선할 것입니다.

2️⃣ 초기에는 일부 음식점에서 이미지 URL이 존재하지 않아 오류 발생하였고, Google Places API를 통해 찾은 photoUrl이 없을 경우, "대표 사진 없음"을 반환하는걸로 수정했습니다.

3️⃣ React Native에서 현재 위치를 가져오고 백엔드에 전송하는 과정에서 두 번 요청되는 문제가 발생했었는데, useEffect 내 의존성 배열 미설정으로 인해 중복 요청 발생하는게 원인이였고, useEffect에서 의존성 배열을 정확하게 설정하여 해결했습니다.


<br>


## 🛠 기술 스택
- Frontend
React Native
React Native Maps 
Axios (API 통신)


- Backend
Spring Boot
Spring Security (세션 기반 인증)
JPA & MySQL
Google Places API (음식점 정보 및 사진 제공)
Amazon S3 (이미지 저장)

<br>


## 📌 향후 업데이트 
- 다국어 지원(영어, 일본어, 베트남어 등)
- 리뷰에 사진 업로드 기능 추가
- 사용자가 직접 위치를 설정할 수 있는 기능 추가 

