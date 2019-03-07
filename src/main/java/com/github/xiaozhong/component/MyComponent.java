package com.github.xiaozhong.component;

import com.alibaba.fastjson.JSONObject;
import com.github.xiaozhong.dto.UserRichInfo;
import com.github.xiaozhong.manager.UserManager;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author zhougaolei
 * @date At 2019/3/6
 */
@Component
public class MyComponent {

    @Resource
    private UserManager userManager;

    public void dealMessage(String message) {
        UserRichInfo userRichInfo = JSONObject.parseObject(message, UserRichInfo.class);
        userManager.createUserInfo(userRichInfo);
    }

}
