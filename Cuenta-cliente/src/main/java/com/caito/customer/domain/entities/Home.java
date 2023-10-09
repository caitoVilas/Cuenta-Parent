package com.caito.customer.domain.entities;

import jakarta.persistence.*;
import lombok.*;

/**
 * @author claudio.vilas
 * date 09/2023
 */

@Entity
@Table(name = "homes")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Home {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 40)
    private String street;
    @Column(nullable = false, length = 40)
    private String location;
    @Column(nullable = false, length = 30)
    private String province;
    @Column(nullable = false, length = 30)
    private String country;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
