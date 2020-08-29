package com.fchan.layui.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fchan.layui.aspect.CheckUser;
import com.fchan.layui.aspect.CurrentUserHolder;
import com.fchan.layui.entity.AopTestEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SpringAopTestService implements SpringAopTestInterface{

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public String insert(AopTestEntity aopTestEntity) throws JsonProcessingException {
        String result = objectMapper.writeValueAsString(aopTestEntity);
        log.info("插入一条数据:{}",result);
        if(!"hello".equals(CurrentUserHolder.get())){
            throw new RuntimeException("用户不对");
        }
        return result;
    }

    @CheckUser
    public void delete(AopTestEntity aopTestEntity) throws JsonProcessingException {
        String result = objectMapper.writeValueAsString(aopTestEntity);
        log.info("删除一条数据:{}",result);
        throw new RuntimeException("测试");
    }


    @Override
    public void print() {
        System.out.println("实现了SpringAopTestInterface接口的print方法");
    }
}
