package com.ecommerce.kientv84.Commons.CKConstant;

public class CkResults {
    // static la sd ma ko can khoi tao object, final la chi duoc ga 1 lan
    public static final int SUCCESS = 1;
    public static final int ERROR = -1;
    public static final int DATA_EXIST = 2;
    public static final int DATA_USING = 4;

    private CkResults() {
        // Private constructor để không cho phép khởi tạo object từ class này
    }
}
