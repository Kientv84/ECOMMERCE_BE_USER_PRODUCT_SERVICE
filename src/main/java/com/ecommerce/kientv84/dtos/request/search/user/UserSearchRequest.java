package com.ecommerce.kientv84.dtos.request.search.user;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserSearchRequest {
    private UserSearchOption searchOption = new UserSearchOption();
    private UserSearchModel searchModel = new UserSearchModel();

    public String hashKey() {
        return "option:" + searchOption.hashKey() + "|filter:" + searchModel.hashKey();
    }
}
