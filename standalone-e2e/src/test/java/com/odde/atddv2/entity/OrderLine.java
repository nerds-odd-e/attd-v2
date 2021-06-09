package com.odde.atddv2.entity;


import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Accessors(chain = true)
@Table(name = "order_lines")
public class OrderLine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String itemName;
    private BigDecimal price;
    private int quantity;

    @ManyToOne
    private Order order;
}
