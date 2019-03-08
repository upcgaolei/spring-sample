package com.github.xiaozhong.extend;

import com.github.xiaozhong.component.LogComponent;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

/**
 * @author zhougaolei
 * @date At 2019/3/8
 */
public class ExtendDataSourceTransactionManager extends DataSourceTransactionManager {

    private static final Log logger = LogFactory.getLog(LogComponent.class);

    private void init() {
        logger.info(this.getClass().getName() + "- - - initializing bean");
    }

    public void destroy() {
        logger.info(this.getClass().getName() + "- - - destroying bean");
    }

}
