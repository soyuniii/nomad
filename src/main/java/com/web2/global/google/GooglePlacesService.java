package com.web2.global.google;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GooglePlacesService {

    //깃에 올릴 때 gitignore?
    @Value("${google.api.key}")
    private String GOOGLE_API_KEY;

    private static final String PLACES_SEARCH_URL = "https://maps.googleapis.com/maps/api/place/findplacefromtext/json";
    private static final String PLACE_PHOTO_URL = "https://maps.googleapis.com/maps/api/place/photo";

    // 장소 검색 및 photoReference 가져오기
    public String getPhotoReference(String restaurantName, String address) {
        String url = PLACES_SEARCH_URL + "?input=" + restaurantName + " " + address + " "
                + "&inputtype=textquery&fields=photos&key=" + GOOGLE_API_KEY;

        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(url, String.class);
        JSONObject jsonResponse = new JSONObject(response);

        if (jsonResponse.has("candidates") && jsonResponse.getJSONArray("candidates").length() > 0) {
            JSONObject candidate = jsonResponse.getJSONArray("candidates").getJSONObject(0);
            if (candidate.has("photos")) {
                return candidate.getJSONArray("photos").getJSONObject(0).getString("photo_reference");
            }
        }
        return null;
    }

    // photoReference로 이미지 URL 생성
    public String getPhotoUrl(String photoReference) {
        return PLACE_PHOTO_URL
                + "?maxwidth=400"
                + "&photoreference=" + photoReference
                + "&key=" + GOOGLE_API_KEY;
    }
}

