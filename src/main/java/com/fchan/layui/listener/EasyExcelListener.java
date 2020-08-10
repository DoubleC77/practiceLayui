package com.fchan.layui.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fchan.layui.entity.EasyExcelTest;
import lombok.extern.slf4j.Slf4j;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Slf4j
public class EasyExcelListener extends AnalysisEventListener<EasyExcelTest> {

    static final ObjectMapper JSON = new ObjectMapper();

    /**
     * 每隔5条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 5;

    //Collections.synchronizedList给List的add方法加 synchronized 锁
    List<EasyExcelTest> list = Collections.synchronizedList(new ArrayList<EasyExcelTest>());
/*

     * 假设这个是一个DAO，当然有业务逻辑这个也可以是一个service。当然如果不用存储这个对象没用。

    private DemoDAO demoDAO;
    public DemoDataListener() {
        // 这里是demo，所以随便new一个。实际使用如果到了spring,请使用下面的有参构造函数
        demoDAO = new DemoDAO();
    }

     * 如果使用了spring,请使用这个构造方法。每次创建Listener的时候需要把spring管理的类传进来
     *
     * @param demoDAO
     *
    public DemoDataListener(DemoDAO demoDAO) {
        this.demoDAO = demoDAO;
    }
*/

    /**
     * 加上存储数据库
     */
    private void saveData() {
        //demoDAO.save(list);
        log.info("存储数据库成功！");
    }


    /**
     * 解析每一条数据的时候都会进这个方法
     * @param data
     * @param context
     */
    @Override
    public void invoke(EasyExcelTest data, AnalysisContext context) {
        try {
            log.info("解析到一条数据:{}",JSON.writeValueAsString(data));
            if(list.size() >= BATCH_COUNT){
                //存储数据库
                saveData();

                //然后准备攒下一波数据
                list.clear();
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    /**
     * 解析完成的时候也要保存一遍数据库
     * @param context
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        if(list.size() > 0){
            //存数据库
            saveData();
        }
    }

    /**
     * 只会第一次读取到表头的时候进入这个方法
     * 这里读取到的是真实的传入的excel中的表头
     * @param headMap
     * @param context
     */
    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        try {
            log.info("读取到的表头:{}",JSON.writeValueAsString(headMap));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        super.invokeHeadMap(headMap, context);
    }
}
