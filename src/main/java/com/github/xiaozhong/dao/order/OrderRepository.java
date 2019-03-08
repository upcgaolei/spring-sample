package com.github.xiaozhong.dao.order;

import com.github.xiaozhong.entity.order.Order;

/**
 * @author zhougaolei
 * @date At 2019/3/8
 */
public interface OrderRepository {

    int insert(Order record);

}
