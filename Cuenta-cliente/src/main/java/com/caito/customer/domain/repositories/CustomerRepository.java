package com.caito.customer.domain.repositories;

import com.caito.customer.domain.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author claudio.vilas
 * date 09/2023
 */

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    boolean existsByDni(String dni);
    boolean existsByEmail(String email);
}
