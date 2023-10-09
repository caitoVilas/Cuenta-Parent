package com.caito.customer.api.models.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

/**
 * @author claudio.vilas
 * date 09/2023
 */

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CustomerResponse implements Serializable {
    private Long id;
    private String name;
    private String surname;
    private String dni;
    private Set<HomeResponse> home;
    private String email;
    private String telephone;
    private Set<AccountResponse> account;
    private LocalDate createAt;
    private LocalDate updateAt;
}
