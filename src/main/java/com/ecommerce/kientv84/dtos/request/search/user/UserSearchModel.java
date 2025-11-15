package com.ecommerce.kientv84.dtos.request.search.user;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserSearchModel {
    private String q;       // search text: userName, userEmail
    private String status;  // ACTIVE, INACTIVE, PENDING, DELETED
    private Long roleId;    // filter theo role

    // hashKey phục vụ caching (cùng option sẽ tạo key duy nhất)
    public String hashKey() {
        return (q + "-" + status + "-" + roleId).replace("null", "");
    }
}
