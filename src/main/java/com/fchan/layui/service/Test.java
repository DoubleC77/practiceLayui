package com.fchan.layui.service;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class Test implements Serializable {
    private static final long serialVersionUID = -6303796386508732965L;
    @JsonDeserialize(using = TestDesi.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public LocalDateTime time;
}
