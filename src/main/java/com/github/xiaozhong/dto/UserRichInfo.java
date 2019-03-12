package com.github.xiaozhong.dto;

import com.github.xiaozhong.entity.user.User;
import com.github.xiaozhong.entity.user.UserAddress;

/**
 * @author zhougaolei
 * @date At 2019/3/7
 */
public class UserRichInfo {

    private long userId;

    private String userName;

    private String userPhone;

    private String userAddress;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public User buildUser() {
        User user = new User();
        user.setId(this.getUserId());
        user.setUserName(this.userName);
        user.setUserPhone(this.userPhone);
        return user;
    }

    public UserAddress buildUserAddress() {
        UserAddress userAddress = new UserAddress();
        userAddress.setUserId(this.getUserId());
        userAddress.setUserAddress(this.userAddress);
        return userAddress;
    }

    @Override
    public String toString() {
        return "UserRichInfo{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", userAddress='" + userAddress + '\'' +
                '}';
    }
}
