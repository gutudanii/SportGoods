package com.ecommerce.SportGoods.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Shipping {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String recipientName;
    private String streetAddress;
    private String aptSuiteUnit;
    private String city;
    private String stateProvinceRegion;
    private String postalCode;
    private String country;
    private String phoneNumber;
    private String specialInstructions;
}
