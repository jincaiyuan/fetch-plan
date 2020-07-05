package com.walker.fetchplan.entity;

import lombok.Data;

import java.util.Date;

@Data
public class PlanScriptsTO {

    private Integer id;

    private String planName;

    private String realSubmitTime;

    private String remark;

    private String type;

    private String cron;

    private Integer planStatus;
}
