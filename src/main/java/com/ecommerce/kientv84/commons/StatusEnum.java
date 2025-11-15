package com.ecommerce.kientv84.commons;

import lombok.AllArgsConstructor;
import lombok.Getter;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusEnum {
    ACTIVE("active"),       // người dùng đang hoạt động
    INACTIVE("inactive"),   // người dùng bị khóa hoặc không hoạt động
    PENDING("pending"),     // người dùng chưa xác thực, chờ phê duyệt
    DELETED("deleted");     // người dùng bị xóa (soft delete)

    private final String status;
}

