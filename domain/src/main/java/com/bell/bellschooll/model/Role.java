//package com.bell.bellschooll.model;
//
//import lombok.Getter;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//
//import java.util.Set;
//import java.util.stream.Collectors;
//
//@Getter
//public enum Role {
//    USER(Set.of(Permission.USER)),
//    ADMIN(Set.of(Permission.MODERATE, Permission.USER));
//
//    private final Set<Permission> permissions;
//
//    Role(Set<Permission> permissions) {
//        this.permissions = permissions;
//    }
//
//    public Set<SimpleGrantedAuthority> getAuthorities() {
//        return permissions.stream()
//                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
//                .collect(Collectors.toSet());
//    }
//}
