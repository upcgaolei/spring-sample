package com.github.xiaozhong.manager;

import com.github.xiaozhong.dao.UserAddressRepository;
import com.github.xiaozhong.dao.UserRepository;
import com.github.xiaozhong.dto.UserRichInfo;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author zhougaolei
 * @date At 2019/3/7
 */
@Component
public class UserManager {

    @Resource
    private UserRepository userRepository;

    @Resource
    private UserAddressRepository userAddressRepository;

    @Transactional(rollbackFor = Exception.class, value = "transactionManager")
    public boolean createUserInfo(UserRichInfo userRichInfo) {
//        int userId = userRepository.insert(userRichInfo.buildUser());
//        UserAddress userAddress = userRichInfo.buildUserAddress();
//        userAddress.setUserId((long) userId);
//        userAddressRepository.insert(userAddress);
        return true;
    }

}