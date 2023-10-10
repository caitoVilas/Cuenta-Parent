package com.caito.customer.util.maps;

import com.caito.customer.api.models.requests.HomeRequest;
import com.caito.customer.api.models.responses.HomeResponse;
import com.caito.customer.domain.entities.Home;

/**
 * @author claudio.vilas
 * date 09/2023
 */

public class HomeMap {

    public static Home mapToEntity(HomeRequest request){
        return Home.builder()
                .street(request.getStreet())
                .location(request.getLocation())
                .province(request.getProvince())
                .country(request.getCountry())
                .build();
    }

    public static HomeResponse mapToDto(Home entity){
        return HomeResponse.builder()
                .id(entity.getId())
                .street(entity.getStreet())
                .location(entity.getLocation())
                .province(entity.getProvince())
                .country(entity.getCountry())
                .build();
    }
}
