package com.github.xiaozhong.entity;

/**
 * @author zhougaolei
 * @date At 2019/3/7
 */
public class UserAddress {

    private Long userId;

    private String userAddress;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    @Override
    public String toString() {
        return "UserAddress{" +
                "userId=" + userId +
                ", userAddress='" + userAddress + '\'' +
                '}';
    }
}
