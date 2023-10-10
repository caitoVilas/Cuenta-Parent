package com.caito.customer.util.maps;

import com.caito.customer.api.models.requests.CustomerRequest;
import com.caito.customer.api.models.responses.CustomerResponse;
import com.caito.customer.domain.entities.Customer;

import java.util.stream.Collectors;

/**
 * @author claudio.vilas
 * date 09/2023
 */


public class CustomerMap {
    public static Customer mapToEntity(CustomerRequest request){
        return Customer.builder()
                .name(request.getName())
                .surname(request.getSurname())
                .dni(request.getDni())
                .email(request.getEmail())
                .telephone(request.getTelephone())
                .build();
    }

    public static CustomerResponse mapToDto(Customer entity){
        return CustomerResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .surname(entity.getSurname())
                .dni(entity.getDni())
                .home(entity.getHome().stream().map(HomeMap::mapToDto).collect(Collectors.toSet()))
                .email(entity.getEmail())
                .telephone(entity.getTelephone())
                .account(entity.getAccounts().stream().map(AccountMap::mapToDto).collect(Collectors.toSet()))
                .createAt(entity.getCreateAt())
                .updateAt(entity.getUpdateAt())
                .build();
    }
}
