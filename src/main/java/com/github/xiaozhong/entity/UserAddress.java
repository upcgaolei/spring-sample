package com.github.xiaozhong.entity;

/**
 * @author zhougaolei
 * @date At 2019/3/7
 */
public class UserAddress {

    private Long userId;

    private String addressDesc;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getAddressDesc() {
        return addressDesc;
    }

    public void setAddressDesc(String addressDesc) {
        this.addressDesc = addressDesc;
    }

    @Override
    public String toString() {
        return "UserAddress{" +
                "userId=" + userId +
                ", addressDesc='" + addressDesc + '\'' +
                '}';
    }
}
