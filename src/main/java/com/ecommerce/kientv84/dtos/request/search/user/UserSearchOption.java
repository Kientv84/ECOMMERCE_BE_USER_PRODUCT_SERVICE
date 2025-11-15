package com.ecommerce.kientv84.dtos.request.search.user;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserSearchOption {
    private Integer page = 0;
    private Integer size = 10;
    private String sort = "createdDate,desc"; // format: field,asc|desc

    public String hashKey() {
        return page + "-" + size + "-" + sort;
    }
}