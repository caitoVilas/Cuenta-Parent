package com.caito.customer.infrastructure.services.impl;

import com.caito.customer.api.exceptions.customs.BadRequestException;
import com.caito.customer.api.models.requests.HomeRequest;
import com.caito.customer.api.models.responses.HomeResponse;
import com.caito.customer.domain.repositories.HomeRepository;
import com.caito.customer.infrastructure.services.contracts.HomeService;
import com.caito.customer.util.constants.HomeConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author claudio.vilas
 * date 09/2023
 */

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class HomeServiceImpl implements HomeService {
    private HomeRepository homeRepository;


    @Override
    public HomeResponse create(HomeRequest request) {
        log.info("---> inicio servicio crear domicilio");
        log.info("---> validando entradas...");
        this.validateHome(request);
        log.info("---> guardando domicilio...");

        return null;
    }

    @Override
    public HomeResponse update(Long id, Long customerId, HomeRequest request) {
        return null;
    }

    @Override
    public Page<HomeResponse> getAll(int page, int size) {
        return null;
    }

    @Override
    public HomeResponse getOne(Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    private void validateHome(HomeRequest request){
        if (request.getStreet().isBlank()){
            log.error("ERROR: ".concat(HomeConstants.H_NO_STREET));
            throw new BadRequestException(HomeConstants.H_NO_STREET);
        }
        if (request.getLocation().isBlank()){
            log.error("ERROR: ".concat(HomeConstants.H_NO_LOCATION));
            throw new BadRequestException(HomeConstants.H_NO_LOCATION);
        }

        if (request.getProvince().isBlank()){
            log.error("ERROR: ".concat(HomeConstants.H_NO_PROVINCE));
            throw new BadRequestException(HomeConstants.H_NO_PROVINCE);
        }
        if (request.getCountry().isBlank()){
            log.error("ERROR: ".concat(HomeConstants.H_NO_COUNTRY));
            throw new BadRequestException(HomeConstants.H_NO_PROVINCE);
        }
    }
}
