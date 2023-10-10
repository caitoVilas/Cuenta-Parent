package com.caito.customer.infrastructure.services.contracts;

import com.caito.customer.api.models.requests.CustomerRequest;
import com.caito.customer.api.models.responses.CustomerResponse;
import com.caito.customer.util.enums.SortType;
import org.springframework.data.domain.Page;

/**
 * @author claudio.vilas
 * date 09/2023
 */

public interface CustomerService {
    CustomerResponse create(CustomerRequest request);
    CustomerResponse update(Long id, CustomerRequest request);
    Page<CustomerResponse> getAll(int page, int size, SortType sortType);
    CustomerResponse getOne(Long id);
    void delete(Long id);
}
