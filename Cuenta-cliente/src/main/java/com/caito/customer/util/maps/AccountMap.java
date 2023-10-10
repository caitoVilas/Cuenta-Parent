package com.caito.customer.util.maps;

import com.caito.customer.api.models.requests.AccountRequest;
import com.caito.customer.api.models.responses.AccountResponse;
import com.caito.customer.domain.entities.Account;

/**
 * @author claudio.vilas
 * date 09/2023
 */

public class AccountMap {
    public static Account mapToEntity(AccountRequest request){
        return Account.builder()
                .entity(request.getEntity())
                .cbu(request.getCbu())
                .build();
    }

    public static AccountResponse mapToDto(Account entity){
        return AccountResponse.builder()
                .id(entity.getId())
                .entity(entity.getEntity())
                .cbu(entity.getCbu())
                .createAt(entity.getCreateAt())
                .updateAt(entity.getUpdateAt())
                .build();
    }
}
