package com.example.CouponSystem2.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "companies")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;
    private String name;
    private String email;
    private String password;

    @OneToMany(mappedBy = "company", cascade = CascadeType.REMOVE)
    @ToString.Exclude
    @JsonIgnore
    private List<Coupon> coupons;
}
