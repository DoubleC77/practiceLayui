package com.fchan.layui;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fchan.layui.aspect.CurrentUserHolder;
import com.fchan.layui.entity.AopTestEntity;
import com.fchan.layui.represent.service.CglibDemoInterceptor;
import com.fchan.layui.represent.service.JdkProxySubject;
import com.fchan.layui.represent.service.Subject;
import com.fchan.layui.represent.service.impl.Proxy;
import com.fchan.layui.represent.service.impl.RealSubject;
import com.fchan.layui.service.SpringAopTestService;
import com.fchan.layui.springLife.SpringFactoryDemo;
import lombok.extern.slf4j.Slf4j;
import net.sf.cglib.proxy.Enhancer;
import net.sf.json.JSON;
import net.sf.json.JSONSerializer;
import net.sf.json.xml.XMLSerializer;
import org.json.JSONObject;
import org.json.XML;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

@SpringBootTest
@Slf4j
class LayuiApplicationTests {

    @Test
    void contextLoads() {
        int[] array = {123, 321};
        System.out.println(largestNumber(array));
    }

    public String largestNumber(int[] nums) {
        List<Integer> arrayList = Arrays.stream(nums).boxed().collect(Collectors.toList());
        List<Integer> list = new ArrayList<Integer>();
        int size = arrayList.size();
        for (int i = 0; i < size - 1; i++) {
            int index = getHaveMaxSingleNumElement(arrayList.toArray(new Integer[arrayList.size()]));
            list.add(arrayList.get(index));
            //去掉最大的那个数字在的数组元素,再执行一遍这个方法,直至最后一个元素
            arrayList.remove(arrayList.get(index));
        }
        //最后一个元素排最后
        list.addAll(arrayList);
        return String.join("", list.stream().map(e -> e.toString()).collect(Collectors.toList()));
    }

    //返回有最大单个数子的元素在传入的数组的下标
    int getHaveMaxSingleNumElement(Integer[] nums) {
        int num = -1;
        Map<Integer, Integer> recordMap = new HashMap<Integer, Integer>();
        Map<Integer, Integer> recordStrIndexMap = new HashMap<>();
        //求出拥有最大单个数字的整数
        for (int i = 0; i < nums.length; i++) {
            List<String> tempList = Arrays.asList(nums[i].toString().split(""));
            if (tempList.size() > 1) {
                for (String str : tempList) {
                    Integer n = Integer.parseInt(str);
                    if (n >= num) {
                        if (null != recordStrIndexMap.get(num) && tempList.indexOf(str) < recordStrIndexMap.get(num)) {
                            num = n;
                            //记录此时的最大单个数字和这个数字对应的原数组的元素下标
                            recordMap.put(num, i);
                            recordStrIndexMap.put(num, tempList.indexOf(str));
                        } else {
                            num = n;
                            //记录此时的最大单个数字和这个数字对应的原数组的元素下标
                            recordMap.put(num, i);
                            recordStrIndexMap.put(num, tempList.indexOf(str));
                        }
                    }
                }
            } else {
                if (Integer.parseInt(tempList.get(0)) > num) {
                    num = Integer.parseInt(tempList.get(0));
                    recordMap.put(num, i);
                }
            }
        }
        int max = recordMap.get(num);
        return max;
    }


    @Autowired
    public SpringAopTestService springAopTestService;

    @Test
    public void testInsertAop() throws JsonProcessingException {
        AopTestEntity aopTestEntity = new AopTestEntity();
        aopTestEntity.setId(1);
        aopTestEntity.setName("qqqq");

        CurrentUserHolder.set("hello");
        String result = null;
        try {
            result = springAopTestService.insert(aopTestEntity);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            log.error("aop的异常我吃了,{}", e.getMessage());
        }
        System.out.println(result);


    }

    @Test
    public void afterDelete() throws JsonProcessingException {
        AopTestEntity aopTestEntity = new AopTestEntity();
        aopTestEntity.setId(1);
        aopTestEntity.setName("Hello World");
        springAopTestService.delete(aopTestEntity);

    }


    @Test
    public void testPrint() {
        springAopTestService.print();
    }


    /**
     * 静态代理demo
     */
    @Test
    public void testRepresent() {
        Subject subject = new Proxy(new RealSubject());
        subject.print();
    }


    /**
     * JDK的动态代理
     * 需要通过接口来实现,即目标类需要实现一个业务接口
     * loader：一个ClassLoader对象，定义了由哪个ClassLoader对象来对生成的代理对象进行加载
     * <p>
     * interfaces：一个Interface对象的数组，表示的是我将要给我需要代理的对象提供一组什么接口
     * ，如果我提供了一组接口给它，那么这个代理对象就宣称实现了该接口(多态)，这样我就能调用这组接口中的方法了
     * <p>
     * h：一个InvocationHandler接口，表示代理实例的调用处理程序实现的接口。每个代理实例都具有一个关联的调用处理程序。
     * 对代理实例调用方法时，将对方法调用进行编码并将其指派到它的调用处理程序的 invoke 方法（传入InvocationHandler接口的子类）
     */
    @Test
    public void testJDKDynamicProxy() {
        //将生成的字节码保存下来
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        RealSubject realSubject = new RealSubject();
        realSubject.getClass().getInterfaces();
        Subject subject = (Subject) java.lang.reflect.Proxy.newProxyInstance(this.getClass().getClassLoader()
                , new Class[]{Subject.class}, new JdkProxySubject(new RealSubject()));
        subject.print();
    }


    @Test
    public void testCglibProxy() {

        Enhancer enhancer = new Enhancer();
        //设置父类
        enhancer.setSuperclass(RealSubject.class);
        //具体代理的实现了MethodInterceptor接口的子类
        enhancer.setCallback(new CglibDemoInterceptor());
        Subject subject = (Subject) enhancer.create();
        subject.print();
    }


    @Autowired
    SpringFactoryDemo springFactoryDemo;

    @Autowired
    ApplicationContext applicationContext;

    @Test
    public void testXmlToJson() {

        JSONObject json = new JSONObject();
        json.put("name", "1qqq");
        XMLSerializer serializer = new XMLSerializer();
        JSON jsonObject = JSONSerializer.toJSON(json.toString());
        String result = serializer.write(jsonObject);


    }


}
