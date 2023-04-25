package com.ms.bpp.common.enums;

import java.util.Objects;

public enum OrderStatus {

    ACTIVE("ACTIVE"),
    COMPLETE("COMPLETE"),
    CANCELLED("CANCELLED"),
    PENDING_APPROVAL("PENDING APPROVAL"),
    ACCEPTED("ACCEPTED"),
    REJECTED("REJECTED");

    private final String value;

    OrderStatus(String value) {
        this.value = value;
    }

    public String value() {
        return this.value;
    }

    public static OrderStatus getStatus(String value) {
        for (OrderStatus str : OrderStatus.values()) {
            if (Objects.equals(str.value, value))
                return str;
        }
        return null;
    }
}