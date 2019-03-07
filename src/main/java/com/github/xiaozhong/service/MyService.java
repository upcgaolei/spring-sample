package com.github.xiaozhong.service;

import com.github.xiaozhong.component.MyComponent;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author zhougaolei
 * @date At 2019/3/6
 */
@Service
public class MyService {

    @Resource
    private MyComponent myComponent;

    public void sayHello() {
    }

}
