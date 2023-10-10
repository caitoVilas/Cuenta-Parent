package com.caito.customer.api.controllers;

import com.caito.customer.api.models.requests.CustomerRequest;
import com.caito.customer.api.models.responses.CustomerResponse;
import com.caito.customer.infrastructure.services.contracts.CustomerService;
import com.caito.customer.util.enums.SortType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author claudio.vilas
 * date 09/2023
 */

@RestController
@RequestMapping("/api/v1/cuenta/customers")
@Slf4j
@RequiredArgsConstructor
@Tag(name = "CUENTA - CLIENTES")
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping
    @Operation(summary = "servicio para alta de clientes al sisteme",
            description = "servicio para alta de clientes al sisteme")
    @Parameter(name = "request", description = "datos del cliente")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "created"),
            @ApiResponse(responseCode = "400", description = "bad request"),
            @ApiResponse(responseCode = "500", description = "internal server error")
    })
    public ResponseEntity<CustomerResponse> create(@RequestBody CustomerRequest request){
        log.info("#### endpoint alta de clientes ####");
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(customerService.create(request));
    }

    @GetMapping("/{id}")
    @Operation(summary = "servicio para consulta de clientes por id",
            description = "servicio para consulta de clientes por id si existe")
    @Parameter(name = "id", description = "id del cliente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "404", description = "not found"),
            @ApiResponse(responseCode = "500", description = "internal server error")
    })
    public ResponseEntity<CustomerResponse> getOne(@PathVariable Long id){
        log.info("#### endpoint consultar cliente por id ####");
        return ResponseEntity.ok(customerService.getOne(id));
    }

    @GetMapping
    @Operation(summary = "servicio para consulta de clientes",
            description = "servicio para consulta de clientes ")
    @Parameters({
            @Parameter(name = "page", description = "pagina a mostrar"),
            @Parameter(name = "size", description = "numero de elementos a mostrar"),
            @Parameter(name = "sorType", description = "ordenamiento")
    })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "204", description = "no content"),
            @ApiResponse(responseCode = "500", description = "internal server error")
    })
    public ResponseEntity<Page<CustomerResponse>> getAll(@RequestParam int page,
                                                         @RequestParam int size,
                                                         @RequestParam SortType sortType){
        log.info("#### endpoint consultar clientes ####");
        if (page < 0) page = 0;
        if (size < 0) size = 5;
        Page<CustomerResponse> customers = customerService.getAll(page, size, sortType);
        if (customers.getContent().isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(customers);
    }
}
