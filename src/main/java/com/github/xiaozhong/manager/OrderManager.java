package com.github.xiaozhong.manager;

import com.github.xiaozhong.dao.order.OrderRepository;
import com.github.xiaozhong.entity.order.Order;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

/**
 * @author zhougaolei
 * @date At 2019/3/8
 */
@Component
public class OrderManager {

    private static final Log logger = LogFactory.getLog(UserManager.class);

    @Resource
    private OrderRepository orderRepository;

    @Transactional(rollbackFor = Exception.class, value = "orderTransactionManager")
    public boolean createOrderInfo() {
        Order order = new Order();
        order.setPrice(100);
        order.setOrderDesc("desc");
        orderRepository.insert(order);
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
