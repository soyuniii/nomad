package com.web2.restaurant;

import com.web2.global.SessionService;
import com.web2.restaurant.dto.LocationRequest;
import com.web2.restaurant.dto.RestaurantDTO;
import com.web2.restaurant.dto.RestaurantDetailsDTO;
import com.web2.user.User;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/*@CrossOrigin(origins = "*") // 모든 도메인 허용*/
@RestController
@RequiredArgsConstructor
@RequestMapping("/restaurants")
public class RestaurantController {

    private final RestaurantService restaurantService;
    private final SessionService sessionService;

    //React native에서 JSON으로 사용자 위치 데이터(위도, 경도, 반경)를 전송해서 RequestBody로 받아 사용
    //반경 내의 음식점 리스트 다시 프론트로 반환 -> 네이버 지도 뷰에서 마커로 띄움.
    // 메인 화면에서 5km 이내 음식점 간단 조회
    @PostMapping("/search/location")
    public List<RestaurantDTO> searchRestaurants(@RequestBody LocationRequest request,
                                                 HttpSession session,
                                                 @CookieValue(value = "SESSION_ID", required = false) String sessionId) {

        if (sessionId == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "No Session ID");
        }

        sessionService.validateSession(sessionId, session);
        sessionService.validateCsrfToken(session);
        User user = sessionService.validateUser(session);

        double latitude = request.getLatitude();
        double longitude = request.getLongitude();
        double radius = request.getRadius();

        String userNationality = user.getNationality();

        return restaurantService.findRestaurantNearLocation(latitude, longitude, radius, userNationality);
    }

    /*@PostMapping("/search/location")
    public ResponseEntity<String> searchLocation(@RequestBody LocationRequest request,
                                                 HttpSession session,
                                                 @CookieValue(value = "SESSION_ID", required = false) String sessionId) {
        Logger logger = LoggerFactory.getLogger(getClass());

        if (sessionId == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "No Session ID");
        }

        sessionService.validateSession(sessionId, session);
        sessionService.validateCsrfToken(session);
        User user = sessionService.validateUser(session);

        if (request.getLatitude() == null || request.getLongitude() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Latitude or Longitude is missing");
        } else if (request.getRadius() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Radius is missing");
        }

        logger.info("Received Location Request: {}", request);

        double latitude = request.getLatitude();
        double longitude = request.getLongitude();
        double radius = request.getRadius();
        String userNationality = user.getNationality();

        // 주변 음식점 데이터 가져오기
        List<MarkerDTO> markers = restaurantService.findRestaurantNearLocation(latitude, longitude, radius, userNationality);

        // HTML에 표시할 마커 생성
        StringBuilder markersScript = new StringBuilder();
        for (MarkerDTO marker : markers) {
            markersScript.append(String.format("""
                    new naver.maps.Marker({
                        position: new naver.maps.LatLng(%f, %f),
                        map: map,
                        title: "%s"
                    });
                    """, marker.latitude(), marker.longitude(), marker.name()));
        }

        String htmlContent = String.format("""
                
                <!DOCTYPE html>
                <html lang="ko">
                <head>
                    <meta charset="UTF-8">
                    <meta http-equiv="X-UA-Compatible" content="IE=edge">
                    <meta name="viewport" content="width=device-width, user-scalable=no">
                    <title>위치 기반 레스토랑 표시</title>
                    <script type="text/javascript" src="https://oapi.map.naver.com/openapi/v3/maps.js?ncpClientId=ihsg50uuii"></script>
                </head>
                <body>
                    <div id="map" style="width:100%%;height:400px;"></div>
                    <script>
                            // Naver Maps API 인증 실패 시 처리 함수
                            window.naver.maps.authFailure = function () {
                                console.error("Naver Maps API 인증 실패. 클라이언트 ID가 잘못되었거나, API 사용이 허가되지 않았습니다.");
                                alert("지도 로드를 실패했습니다. 인증 문제를 확인하세요.");
                            };
                
                        // HTTPS 환경 확인
                        console.log("Current protocol:", window.location.protocol);
                        if (window.location.protocol !== "https:") {
                            console.warn("HTTPS 환경이 아닙니다. Naver Maps API는 HTTPS를 필요로 할 수 있습니다.");
                        }
                
                        // Naver Maps API Key 확인
                        console.log("Naver Maps API Client ID:", "ihsg50uuii");
                
                        var map = new naver.maps.Map('map', {
                            center: new naver.maps.LatLng(%f, %f),
                            zoom: 15
                        });
                
                        new naver.maps.Marker({
                            position: new naver.maps.LatLng(%f, %f),
                            map: map,
                            title: "Current Location",
                            icon: {
                                content: '<div style="background-color: red; width: 20px; height: 20px; border-radius: 50%%; border: 2px solid white;"></div>',
                                anchor: new naver.maps.Point(10, 10)
                            }
                        });
                
                        // Add markers for restaurants
                        %s
                    </script>
                </body>
                </html>
                """, latitude, longitude, latitude, longitude, markersScript.toString());

        return ResponseEntity.ok()
                .header("Content-Type", "text/html")
                .body(htmlContent);
    }*/

    @GetMapping("/search")
    public ResponseEntity<List<RestaurantDetailsDTO>> searchByHashtag(@RequestParam String keyword) {
        List<RestaurantDetailsDTO> restaurantDTOS = restaurantService.searchRestaurant(keyword);
        return ResponseEntity.ok(restaurantDTOS);
    }

    // 음식점 세부정보 조회(리뷰는 개수만)
    @GetMapping("/{id}")
    public RestaurantDetailsDTO getRestaurantDetails(@PathVariable("id") Long id) {
        return restaurantService.getRestaurantDetails(id);
    }
}
