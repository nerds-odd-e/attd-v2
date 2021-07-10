package com.odde.atddv2.jfactory.factory;

import com.github.leeonky.jfactory.Spec;
import com.odde.atddv2.entity.Order;

public class OrderFactories {

    public static class 订单 extends Spec<Order> {

        @Override
        public void main() {
            property("lines").reverseAssociation("order");
        }
    }

}
