package com.github.xiaozhong.manager;

import com.github.xiaozhong.dao.user.UserAddressRepository;
import com.github.xiaozhong.dao.user.UserRepository;
import com.github.xiaozhong.dto.UserRichInfo;
import com.github.xiaozhong.entity.user.User;
import com.github.xiaozhong.entity.user.UserAddress;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

/**
 * @author zhougaolei
 * @date At 2019/3/7
 */
@Component
public class UserManager {

    private static final Log logger = LogFactory.getLog(UserManager.class);

    @Resource
    private UserRepository userRepository;

    @Resource
    private UserAddressRepository userAddressRepository;

    @Transactional(rollbackFor = Exception.class, value = "userTransactionManager")
    public boolean createUserInfo(UserRichInfo userRichInfo) {
        User user = userRichInfo.buildUser();
        userRepository.insert(user);
        UserAddress userAddress = userRichInfo.buildUserAddress();
        userAddress.setUserId(user.getId());
        userAddressRepository.insert(userAddress);
        return true;
    }

    @PostConstruct
    private void init() {
        logger.info(this.getClass().getName() + "- - - initializing bean");
    }

    @PreDestroy
    public void destroy() {
        logger.info(this.getClass().getName() + "- - - destroying bean");
    }

}