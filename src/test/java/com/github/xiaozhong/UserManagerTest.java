package com.github.xiaozhong;

import com.github.xiaozhong.dto.UserRichInfo;
import com.github.xiaozhong.manager.UserManager;
import org.junit.Before;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * @author zhougaolei
 * @date At 2019/3/8
 */
public class UserManagerTest extends AbstractTest {

    @Resource
    private UserManager userManager;

    private UserRichInfo userRichInfo;

    @Before
    public void init() {
        userRichInfo = new UserRichInfo();
        userRichInfo.setUserName("name");
        userRichInfo.setUserPhone("123456");
        userRichInfo.setUserAddress("china, shanghai");
    }

    @Test
    public void createUserInfoTest() {
        userManager.createUserInfo(userRichInfo);
    }

}
