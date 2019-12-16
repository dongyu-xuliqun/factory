package com.cdf.factory.common.enums;

public enum StatusEnum {
    ENABLE(1, "启用"),
    DISABLE(0, "禁用");

    private final int value;
    private final String name;

    StatusEnum(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public int value() {
        return this.value;
    }

    public String getName() {
        return this.name;
    }
}
