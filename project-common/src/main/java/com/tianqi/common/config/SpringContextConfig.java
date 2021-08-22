package com.tianqi.common.config;

import com.tianqi.common.util.SpringUtil;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @Author: yuantianqi
 * @Date: 2021/4/11 17:40
 * @Description:
 */
@Component
public class SpringContextConfig implements ApplicationContextAware {
    @Override
    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {
        SpringUtil.setApplicationContext(applicationContext);
    }

}
