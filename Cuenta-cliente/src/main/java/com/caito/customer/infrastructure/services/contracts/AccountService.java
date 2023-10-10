package com.caito.customer.infrastructure.services.contracts;

import com.caito.customer.api.models.requests.AccountRequest;
import com.caito.customer.api.models.responses.AccountResponse;
import org.springframework.data.domain.Page;

/**
 * @author claudio.vilas
 * date 09/2023
 */

public interface AccountService {
    AccountResponse create(AccountRequest request);
    AccountResponse update(Long id, Long customerId, AccountRequest request);
    Page<AccountResponse> getAll(int page, int Size);
    AccountResponse getOne(Long id);
    void delete(Long id);
}
