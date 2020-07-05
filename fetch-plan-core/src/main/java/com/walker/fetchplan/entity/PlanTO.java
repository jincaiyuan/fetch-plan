package com.walker.fetchplan.entity;

import lombok.Data;

import java.io.File;

@Data
public class PlanTO {

    private File file;
    private String submitTime;
    private String biz;
}
