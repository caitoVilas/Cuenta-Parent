package com.caito.customer.api.models.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * @author claudio.vilas
 * date 09/2023
 */

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ErrorResponse implements Serializable {
    private Integer code;
    private String status;
    private LocalDateTime timestamp;
    private String message;
    private String path;
}
