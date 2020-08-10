package com.fchan.layui.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.format.NumberFormat;
import lombok.Data;
import java.io.Serializable;

@Data
public class EasyExcelTest implements Serializable {
    private static final long serialVersionUID = 8362987561243233425L;

    //@ExcelProperty(value ="类型",index = 0)     //文档中建议只用 index 或者只用 value 不要混用
    @ExcelProperty(index = 0)
    private String type;//开支类型 信用卡等

    @ExcelProperty(index =1)
    private String sum;

    @ExcelProperty(index =2)
    private String name;//开支来源  如：**银行信用卡

    /**
     * 这里用string 去接日期才能格式化。我想接收年月日格式
     */
    @ExcelProperty(index =3)
    @DateTimeFormat("yyyy年MM月dd日HH时mm分ss秒")     //代表从excel中读取到时间格式的单元格内容的时候格式化成 yyyy年MM月dd日HH时mm分ss秒
    private String date;


    @ExcelProperty(index =4)
    private Integer status;

    @ExcelProperty(index =5)
    @NumberFormat("#.##%")          //将接收的数字在读取转成这个 entity的status属性的值的时候转成百分比格式
    private String descr;

}
