package com.caito.customer.api.models.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * @author claudio.vilas
 * date 09/2023
 */

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class AccountResponse implements Serializable {
    private long id;
    private String entity;
    private String cbu;
    private LocalDate createAt;
    private LocalDate updateAt;
}
