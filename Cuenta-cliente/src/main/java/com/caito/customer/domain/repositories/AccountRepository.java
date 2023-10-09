package com.caito.customer.domain.repositories;

import com.caito.customer.domain.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author claudio.vilas
 * date 09/2023
 */
@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    boolean existsByCbu(String cbu);
}
