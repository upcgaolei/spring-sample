package com.github.xiaozhong.component;

import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * @author zhougaolei
 * @date At 2019/3/8
 */
public class InstantiationTracingBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        System.out.println("Bean...... '" + beanName + "' created : " + bean.toString());
        return bean;
    }
}
