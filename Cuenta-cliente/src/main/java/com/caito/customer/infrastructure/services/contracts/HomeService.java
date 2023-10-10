package com.caito.customer.infrastructure.services.contracts;

import com.caito.customer.api.models.requests.HomeRequest;
import com.caito.customer.api.models.responses.HomeResponse;
import org.springframework.data.domain.Page;

public interface HomeService {
    HomeResponse create(HomeRequest request);
    HomeResponse update(Long id, Long customerId, HomeRequest request);
    Page<HomeResponse> getAll (int page, int size);
    HomeResponse getOne(Long id);
    void delete(Long id);
}
