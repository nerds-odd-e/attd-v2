package com.odde.atddv2.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.odde.atddv2.api.Logistics;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Accessors(chain = true)
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private long id;

    private String code, productName, recipientName, recipientMobile, recipientAddress;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String deliverNo;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private BigDecimal total;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderLine> lines = new ArrayList<>();
    @Transient
    @JsonInclude(value = JsonInclude.Include.NON_EMPTY)
    private OrderLogistics logistics;

    public Order populateLogistics(Logistics.Result logistics) {
        OrderLogistics orderLogistics = new OrderLogistics();
        orderLogistics.setDeliverNo(logistics.getNumber())
                .setCompanyCode(logistics.getType())
                .setCompanyName(logistics.getTypename())
                .setCompanyLogo(logistics.getLogo())
                .setDetails(logistics.getList())
                .setDeliveryStatus(logistics.getDeliverystatus() == 1 ? "在途中" : "")
                .setIsSigned(logistics.getIssign() == 0 ? "未签收" : "");
        setLogistics(orderLogistics);
        return this;
    }

    public enum OrderStatus {
        toBeDelivered, delivering
    }

    @Getter
    @Setter
    @Accessors(chain = true)
    public static class OrderLogistics {
        private String deliverNo, companyCode, companyName, companyLogo, deliveryStatus, isSigned;
        private List<Logistics.Detail> details;
    }
}
