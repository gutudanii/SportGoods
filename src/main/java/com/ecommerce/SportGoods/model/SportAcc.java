package com.ecommerce.SportGoods.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "sportAcc")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SportAcc {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long sportId;

    private String type;
    private String orderName;
    private String orderNo;
    private String orderDetail;
    private double orderPrice;
    private int opdPercentage;
    private double opdPrice;
    @Lob
    private byte[] image;
    private String name;
    private String imgLink;
}
