
## 📎 프로젝트 개요
이 프로젝트는 한국을 방문한 외국인 여행자들이 반경 5km 이내에서 자국 음식을 판매하는 음식점을 손쉽게 찾을 수 있도록 도와주는 서비스입니다. 사용자는 현재 위치를 기반으로 주변의 자국 음식점을 추천받고, 상세 정보를 확인할 수 있고, 음식점 이용 후, 리뷰를 작성할 수 있습니다. 

<br>


## 📎 기획 배경 
한국을 방문하는 외국인 관광객들이 증가함에 따라, 현지 음식에 익숙하지 않거나 자국의 음식이 그리운 여행자들이 많아졌습니다. 하지만 원하는 음식을 파는 식당을 찾기 어렵거나 정보가 부족한 경우가 많습니다. 이를 해결하기 위해 위치 기반 추천 시스템을 활용하여 가까운 자국 음식점을 지도에 표시하여 편리하게 음식점을 찾을 수 있도록 본 프로젝트를 기획하였습니다. 또한, 리뷰 기능을 제공하여 방문한 음식점에 대한 실제 평가를 공유함으로써, 신뢰할 수 있는 정보를 제공하고 보다 만족스러운 식사 경험을 도울 수 있도록 만들었습니다.


<br> 


## 🚀 주요 기능

### ⭐️ 회원가입 및 로그인(세션 기반 인증)
- JWT 방식과 비교하여 보안성과 편의성 고려
<img src="https://github.com/user-attachments/assets/1b83c78f-6993-4300-b635-cad113b7432f" height = 600>


<br>


### 📍 위치 기반 음식점 추천
- 사용자의 현재 위치를 기반으로 반경 5km 내 자국 음식점 추천
- 🗾 React Native Maps를 활용한 지도 표시


-- 🇻🇳 베트남 음식점
<img src="https://github.com/user-attachments/assets/2e057893-93e5-4e55-ad32-87a7a64804ea" height = 600> 
<img src="https://github.com/user-attachments/assets/b37a9f7d-4709-4607-a8b5-8076af49101a" height = 600>


 🇮🇹 이탈리아 음식점 
<img src="https://github.com/user-attachments/assets/ce4beab3-6811-4eff-ab62-0e66ea18c163" height = 600> 
<img src="https://github.com/user-attachments/assets/f5e3a90c-3555-49c0-973d-ef062477c8d8" height = 600>


<br>


### 📝 리뷰 작성 및 평점 시스템
방문한 음식점에 대한 리뷰 작성 및 별점 평가 기능

<br>


### 🔍 검색 기능 (해시태그 기반 필터링)
- 음식점의 리뷰 내 해시태그 분석을 통해 검색 결과 제공


<br>


📷 음식점 사진 제공
- 🌎 Google Places API에서 음식점 사진을 가져와 표시
- ☁️ S3에 저장하여 이미지 캐싱 및 최적화


<br> 


## 활용 데이터 
CSV~


## 📕 API 명세서



## 📕 ERD



## 📌 고찰 
- ✅ 음식점 대표 사진 설정 문제 해결
초기에는 일부 음식점에서 이미지 URL이 존재하지 않아 오류 발생하였고, Google Places API를 통해 찾은 photoUrl이 없을 경우, 기본 이미지를 제공하도록 수정

- ✅ 위치 정보 전달 방식 & 두 번 요청 문제 해결
React Native에서 현재 위치를 가져오고 백엔드에 전송하는 과정에서 두 번 요청되는 문제가 발생했었는데, useEffect 내 의존성 배열 미설정으로 인해 중복 요청 발생하는게 원인이였고, useEffect에서 의존성 배열을 정확하게 설정하여 해결했다.


🛠 기술 스택
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


서비스 실행??






