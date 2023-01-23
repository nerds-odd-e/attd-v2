package com.odde.atddv2.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import com.odde.atddv2.api.Logistics;
import com.odde.atddv2.controller.DeliverOrder;
import com.odde.atddv2.controller.GetAllOrders;
import com.odde.atddv2.controller.GetOrder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static com.odde.atddv2.entity.Order.OrderStatus.delivering;
import static java.time.temporal.ChronoUnit.DAYS;

@Getter
@Setter
@Entity
@Accessors(chain = true)
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private long id;

    @JsonView(GetAllOrders.class)
    private String code, productName;

    @JsonView(GetOrder.class)
    private String recipientMobile, recipientAddress;

    @Column(nullable = false)
    @JsonView(GetOrder.class)
    private String recipientName;

    @JsonView(DeliverOrder.class)
    private String deliverNo;

    @Enumerated(EnumType.STRING)
    @JsonView(GetAllOrders.class)
    private OrderStatus status;

    @JsonView(GetAllOrders.class)
    private BigDecimal total;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("id")
    @JsonIgnore
    private List<OrderLine> lines = new ArrayList<>();
    @Transient
    @JsonInclude(value = JsonInclude.Include.NON_EMPTY)
    @JsonView(GetOrder.class)
    private OrderLogistics logistics;

    @JsonInclude(value = JsonInclude.Include.NON_EMPTY)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
    @JsonView(GetOrder.class)
    private Instant deliveredAt;

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

    public boolean isDone(Clock now) {
        return getStatus() == delivering && !getDeliveredAt().isAfter(now.instant().minus(15, DAYS));
    }

    public enum OrderStatus {
        toBeDelivered, delivering, done
    }

    @Getter
    @Setter
    @Accessors(chain = true)
    @JsonView(GetOrder.class)
    public static class OrderLogistics {
        private String deliverNo, companyCode, companyName, companyLogo, deliveryStatus, isSigned;
        private List<Logistics.Detail> details;
    }
}
