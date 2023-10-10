package com.caito.customer.infrastructure.services.impl;

import com.caito.customer.api.exceptions.customs.BadRequestException;
import com.caito.customer.api.exceptions.customs.NotFoundExceptions;
import com.caito.customer.api.models.requests.CustomerRequest;
import com.caito.customer.api.models.responses.CustomerResponse;
import com.caito.customer.domain.entities.Account;
import com.caito.customer.domain.entities.Customer;
import com.caito.customer.domain.entities.Home;
import com.caito.customer.domain.repositories.AccountRepository;
import com.caito.customer.domain.repositories.CustomerRepository;
import com.caito.customer.domain.repositories.HomeRepository;
import com.caito.customer.infrastructure.services.contracts.CustomerService;
import com.caito.customer.util.constants.AccountConstans;
import com.caito.customer.util.constants.CustomerConstants;
import com.caito.customer.util.constants.HomeConstants;
import com.caito.customer.util.enums.SortType;
import com.caito.customer.util.maps.CustomerMap;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author claudio.vilas
 * date 09/2023
 */

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final AccountRepository accountRepository;
    private final HomeRepository homeRepository;

    @Override
    public CustomerResponse create(CustomerRequest request) {
        log.info("---> inicio servicio crear cliente");
        log.info("---> validando datos...");
        this.validateCustomer(request);
        log.info("---> generando domicilios...");
        Set<Home> homes = new HashSet<>();
        request.getHome().forEach(h -> {
            var home = Home.builder()
                    .street(h.getStreet())
                    .location(h.getLocation())
                    .province(h.getProvince())
                    .province(h.getProvince())
                    .country(h.getCountry())
                    .build();
            homes.add(home);
        });
        log.info("---> generando cuentas...");
        Set<Account> accounts = new HashSet<>();
        request.getAcconts().forEach(a -> {
            var account = Account.builder()
                    .entity(a.getEntity())
                    .cbu(a.getCbu())
                    .build();
            accounts.add(account);
        });
        log.info("---> creando cliente...");
        var customer = Customer.builder()
                .name(request.getName())
                .surname(request.getSurname())
                .dni(request.getDni())
                .email(request.getEmail())
                .telephone(request.getTelephone())
                .build();
        homes.forEach(h -> {
            h.setCustomer(customer);
        });
        accounts.forEach(a -> {
            a.setCustomer(customer);
        });
        var homesNew = homeRepository.saveAll(homes);
        var accountsNew = accountRepository.saveAll(accounts);
        Set<Home> hns = new HashSet<>();
        Set<Account> ans = new HashSet<>();
        hns = homesNew.stream().map(hn -> {
            return hn;
        }).collect(Collectors.toSet());
        ans = accountsNew.stream().map(an -> {
            return an;
        }).collect(Collectors.toSet());
        customer.setHome(hns);
        customer.setAccounts(ans);
        log.info("---> guardando cliente...");
        var newCustomer = customerRepository.save(customer);
        log.info("---> finalizado servicio crear cliente");
        return CustomerMap.mapToDto(newCustomer);
    }

    @Override
    public CustomerResponse update(Long id, CustomerRequest request) {
        return null;
    }

    @Override
    public Page<CustomerResponse> getAll(int page, int size, SortType sortType) {
        log.info("---> inicio servicio consultar clientes");
        PageRequest pr = null;
        switch (sortType){
            case NONE -> {
                pr = PageRequest.of(page, size);
                break;
            }
            case LOWER -> {
                pr = PageRequest.of(page, size, Sort.by("createAt").ascending());
                break;
            }
            case UPPER -> {
                pr =PageRequest.of(page,size, Sort.by("createAt").descending());
                break;
            }
        }
        log.info("---> buscando clientes...");
        log.info("---> finalizado servicio consultar clientes");
        return customerRepository.findAll(pr).map(CustomerMap::mapToDto);
    }

    @Override
    public CustomerResponse getOne(Long id) {
        log.info("---> inicio servicio buscar cliente por id");
        log.debug("---> buscando cliente id: {}", id);
        var customer = customerRepository.findById(id).orElseThrow(()-> {
            log.error("ERROR: ".concat(CustomerConstants.C_ID_NOT_FOUND));
            return new NotFoundExceptions(CustomerConstants.C_ID_NOT_FOUND.concat(id.toString()));
        });
        log.info("---> finalizado servicio buscar cliente por id");
        return CustomerMap.mapToDto(customer);
    }

    @Override
    public void delete(Long id) {

    }

    private void validateCustomer(CustomerRequest request){
        if (request.getName().isBlank()){
            log.error("ERROR: ".concat(CustomerConstants.C_NO_NAME));
            throw  new BadRequestException(CustomerConstants.C_NO_NAME);
        }
        if (request.getSurname().isBlank()){
            log.error("ERROR: ".concat(CustomerConstants.C_NO_SURNAME));
            throw  new BadRequestException(CustomerConstants.C_NO_SURNAME);
        }
        if (request.getDni().isBlank()){
            log.error("ERROR: ".concat(CustomerConstants.C_NO_DNI));
            throw new BadRequestException(CustomerConstants.C_NO_DNI);
        }
        if (customerRepository.existsByDni(request.getDni())){
            log.error("ERROR: ".concat(CustomerConstants.C_DNI_REGISTRED));
            throw new BadRequestException(CustomerConstants.C_DNI_REGISTRED);
        }
        if (request.getHome().isEmpty()){
            log.error("ERROR: ".concat(CustomerConstants.C_HOME_EMPTY));
            throw new BadRequestException(CustomerConstants.C_HOME_EMPTY);
        }
        request.getHome().forEach(home ->{
            if (home.getStreet().isBlank()){
                log.error("ERROR: ".concat(HomeConstants.H_NO_STREET));
                throw new BadRequestException(HomeConstants.H_NO_STREET);
            }
            if (home.getLocation().isBlank()){
                log.error("ERROR: ".concat(HomeConstants.H_NO_LOCATION));
                throw new BadRequestException(HomeConstants.H_NO_LOCATION);
            }

            if (home.getProvince().isBlank()){
                log.error("ERROR: ".concat(HomeConstants.H_NO_PROVINCE));
                throw new BadRequestException(HomeConstants.H_NO_PROVINCE);
            }
            if (home.getCountry().isBlank()){
                log.error("ERROR: ".concat(HomeConstants.H_NO_COUNTRY));
                throw new BadRequestException(HomeConstants.H_NO_PROVINCE);
            }
        });
        if (request.getEmail().isBlank()){
            log.error("ERROR: ".concat(CustomerConstants.C_EMAIL_EMPTY));
            throw new BadRequestException(CustomerConstants.C_EMAIL_EMPTY);
        }
        if (customerRepository.existsByEmail(request.getEmail())){
            log.error("ERROR: ".concat(CustomerConstants.C_EMAIL_REGISTRED));
            throw new BadRequestException(CustomerConstants.C_EMAIL_REGISTRED);
        }
        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher matcher = pattern.matcher(request.getEmail());
        if (matcher.find()) {
            log.info("---> mail valido");
        }else{
            log.error("ERROR: ".concat(CustomerConstants.C_EMAIL_NO_VALID));
            throw new BadRequestException(CustomerConstants.C_EMAIL_NO_VALID);
        }
       if (request.getAcconts().isEmpty()){
           log.error("ERROR: ".concat(CustomerConstants.C_ACCOUNT_EMPTY));
           throw new BadRequestException(CustomerConstants.C_ACCOUNT_EMPTY);
       }
       request.getAcconts().forEach(account -> {
           if (account.getEntity().isBlank()){
               log.error("ERROR: ".concat(AccountConstans.A_NO_ENTITY));
               throw new BadRequestException(AccountConstans.A_NO_ENTITY);
           }
           if (account.getCbu().isBlank()){
               log.error("ERROR: ".concat(AccountConstans.A_NO_CBU));
               throw new BadRequestException(AccountConstans.A_NO_CBU);
           }
           if (accountRepository.existsByCbu(account.getCbu())){
               log.error("ERROR: ".concat(AccountConstans.A_CBU_REGISTRED).concat(account.getCbu()));
               throw new BadRequestException(AccountConstans.A_CBU_REGISTRED.concat(account.getCbu()));
           }
       });
    }
}
