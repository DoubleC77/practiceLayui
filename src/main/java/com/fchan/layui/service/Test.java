package com.fchan.layui.service;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;
import net.sf.json.JSON;
import net.sf.json.JSONSerializer;
import net.sf.json.xml.XMLSerializer;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.time.LocalDateTime;

@Data
@Component
public class Test implements Serializable {
    private static final long serialVersionUID = -6303796386508732965L;
    @JsonDeserialize(using = TestDesi.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public LocalDateTime time;

    public static void main(String[] args) {
        Object o = Proxy.newProxyInstance(Test.class.getClassLoader(), new Class[]{SpringAopTestInterface.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                return null;
            }
        });

        //Test是当前类

        InputStream in = Test.class.getResourceAsStream("test.xml");
        String xml;
        try {

            xml = org.apache.commons.io.IOUtils.toString(in);
            System.out.println(xml);
            net.sf.json.xml.XMLSerializer xmlSerializer = new XMLSerializer();
            net.sf.json.JSON json = xmlSerializer.read(xml);
            System.out.println(json);
            System.out.println(json.toString(0));
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
