package com.example.CouponSystem2.entities;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Table(name = "coupons")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @ManyToOne
    private Company company;
    private Category category;
    private String title;
    private String description;
    private Date startDate;
    private Date endDate;
    private Integer amount;
    private BigDecimal price;
    private String image;
}
