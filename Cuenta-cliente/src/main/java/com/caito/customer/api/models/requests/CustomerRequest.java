package com.caito.customer.api.models.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;

/**
 * @author claudio.vilas
 * date 09/2023
 */

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CustomerRequest implements Serializable {
    private String name;
    private String surname;
    private String dni;
    private Set<Long> homeId;
    private String email;
    private String telephone;
    private Set<Long> accontId;

}
