package com.odde.atddv2.jfactory.factory;

import com.github.leeonky.jfactory.Spec;
import com.github.leeonky.jfactory.Trait;
import com.odde.atddv2.entity.Order;

import static com.odde.atddv2.entity.Order.OrderStatus.delivering;
import static com.odde.atddv2.entity.Order.OrderStatus.toBeDelivered;

public class OrderFactories {

    public static class 订单 extends Spec<Order> {

        @Override
        public void main() {
            property("lines").reverseAssociation("order");
        }

        @Trait
        public void 已发货的() {
            property("status").value(delivering);
        }

        @Trait
        public void 未发货的() {
            property("status").value(toBeDelivered);
            property("deliveredAt").value(null);
        }
    }

}
