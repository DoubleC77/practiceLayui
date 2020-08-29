package com.fchan.layui.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class AopTestEntity implements Serializable {

    private static final long serialVersionUID = 7462803298342868222L;

    private long id;

    private String name;

    private int age;

    private String address;

}
