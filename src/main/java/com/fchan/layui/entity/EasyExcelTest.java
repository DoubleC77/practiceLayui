package com.fchan.layui.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.format.NumberFormat;
import com.fchan.layui.service.Test;
import lombok.Data;
import org.apache.commons.codec.binary.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Component
@Data
public class EasyExcelTest implements Serializable {

    public EasyExcelTest() {
    }

    public EasyExcelTest(String type, String sum, String name, String date, Integer status, String descr, String beanName) {
        this.type = type;
        this.sum = sum;
        this.name = name;
        this.date = date;
        this.status = status;
        this.descr = descr;
        this.beanName = beanName;
    }

    private static final long serialVersionUID = 8362987561243233425L;

    //@ExcelProperty(value ="类型",index = 0)     index代表第几列,从0开始
    @ExcelProperty(index = 0, value = "开支类型")
    private String type;//开支类型 信用卡等

    @ExcelProperty(index = 1, value = "总计")
    private String sum;

    @ExcelProperty(index = 2, value = "开支来源")
    private String name;//开支来源  如：**银行信用卡

    /**
     * 这里用string 去接日期才能格式化。我想接收年月日格式
     */
    @ExcelProperty(index = 3, value = "日期")
    @DateTimeFormat("yyyy年MM月dd日HH时mm分ss秒")     //代表从excel中读取到时间格式的单元格内容的时候格式化成 yyyy年MM月dd日HH时mm分ss秒
    private String date;


    @ExcelProperty(index = 4, value = "状态")
    private Integer status;

    @ExcelProperty(index = 5, value = "备注")
    @NumberFormat("#.##%")          //将接收的数字在读取转成这个 entity的status属性的值的时候转成百分比格式
    private String descr;

    @ExcelIgnore
    private String beanName;


}
