package com.web2.restaurant.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LocationRequest {
    private Double latitude;
    private Double longitude;
    private Double radius;

    public LocationRequest(Double latitude, Double longitude, Double radius) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.radius = radius;
    }
}
