package com.github.xiaozhong.component;

import com.alibaba.fastjson.JSONObject;
import com.github.xiaozhong.dto.UserRichInfo;
import com.github.xiaozhong.manager.OrderManager;
import com.github.xiaozhong.manager.UserManager;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author zhougaolei
 * @date At 2019/3/6
 */
@Component
public class MyComponent {

    private static final Log logger = LogFactory.getLog(MyComponent.class);

    @Resource
    private UserManager userManager;

    @Resource
    private OrderManager orderManager;

    public void dealMessage(String message, long i) {
        try {
            UserRichInfo userRichInfo = JSONObject.parseObject(message, UserRichInfo.class);
            userRichInfo.setUserId(i);
            userManager.createUserInfo(userRichInfo);

            orderManager.createOrderInfo();
        } catch (Exception e) {
            logger.error("dealMessage error", e);
        }
    }

}
