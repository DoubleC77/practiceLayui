package com.fchan.layui.springLife;

import com.fasterxml.jackson.annotation.JsonFilter;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
@JsonFilter("beanName")
public class GetSpringCreateBeanName implements BeanNameAware, InitializingBean {

    private String beanName;

    @Override
    public void setBeanName(String name) {
        this.beanName = name;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("afterPropertiesSet");
    }
}
