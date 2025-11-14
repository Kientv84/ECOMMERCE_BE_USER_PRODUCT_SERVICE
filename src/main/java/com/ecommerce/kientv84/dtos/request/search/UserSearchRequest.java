package com.ecommerce.kientv84.dtos.request.search;

import lombok.*;

import java.util.UUID;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserSearchRequest {
    private Integer page = 0;
    private Integer size = 10;
    private String sort = "createdDate,desc"; // format: field,asc|desc
    private String q; // search text: name, email
    private String status;
    private Long roleId;
}

