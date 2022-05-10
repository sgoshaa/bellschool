package com.bell.bellschooll.model;

import lombok.Getter;

@Getter
public enum Permission {
    USER("user:write"),
    MODERATE("user:moderate");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }
}
