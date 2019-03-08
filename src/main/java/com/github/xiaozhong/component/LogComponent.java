package com.github.xiaozhong.component;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author zhougaolei
 * @date At 2019/3/8
 */
public class LogComponent {

    private static final Log logger = LogFactory.getLog(LogComponent.class);

    private void init() {
        logger.info(this.getClass().getName() + "- - - initializing bean");
    }

    public void destroy() {
        logger.info(this.getClass().getName() + "- - - destroying bean");
    }

}
