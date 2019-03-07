package com.github.xiaozhong.dto;

import com.github.xiaozhong.entity.User;
import com.github.xiaozhong.entity.UserAddress;

/**
 * @author zhougaolei
 * @date At 2019/3/7
 */
public class UserRichInfo {

    private String userName;

    private String userPhone;

    private String userAddress;

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
        user.setUserName(this.userName);
        user.setUserPhone(this.userPhone);
        return user;
    }

    public UserAddress buildUserAddress() {
        UserAddress userAddress = new UserAddress();
        userAddress.setAddressDesc(this.userAddress);
        return userAddress;
    }

    @Override
    public String toString() {
        return "UserRichInfo{" +
                "userName='" + userName + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", userAddress='" + userAddress + '\'' +
                '}';
    }
}
