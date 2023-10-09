package com.caito.customer.domain.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.util.Set;

/**
 * @author claudio.vilas
 * date 09/2023
 */

@Entity
@Table(name = "customers")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 25, nullable = false)
    private String name;
    @Column(length = 25, nullable = false)
    private String surname;
    @Column(length = 8, nullable = false)
    private String dni;
    @OneToMany(mappedBy = "customer")
    private Set<Home> home;
    @Column(length = 80, nullable = false)
    private String email;
    @Column(length = 30)
    private String telephone;
    @OneToMany(mappedBy = "customer")
    private Set<Account> accounts;
    @CreationTimestamp
    private LocalDate createAt;
    @UpdateTimestamp
    private LocalDate updateAt;
}
