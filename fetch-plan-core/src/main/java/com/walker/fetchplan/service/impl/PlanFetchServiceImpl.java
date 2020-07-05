package com.walker.fetchplan.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.walker.fetchplan.dal.mapper.PlanDetailMapper;
import com.walker.fetchplan.dal.model.PlanDetail;
import com.walker.fetchplan.entity.PlanScriptsTO;
import com.walker.fetchplan.entity.PlanTO;
import com.walker.fetchplan.service.PlanFetchService;
import com.walker.fetchplan.util.DateUtil;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class PlanFetchServiceImpl implements PlanFetchService {

    private final PlanDetailMapper planDetailMapper;

    @Override
    public void multiplePlanUpload(List<PlanScriptsTO> plans) {
        for (PlanScriptsTO plan : plans) {
            if (planDetailMapper.selectByName(plan.getPlanName()) != null)
                planUpload(plan);
        }
    }

    @Override
    public void planUpload(PlanScriptsTO plan) {
        String biz = plan.getType();
        String realSubmitTime = plan.getRealSubmitTime();
        PlanDetail planDetail = new PlanDetail();

        String planName = plan.getPlanName();
        planDetail.setPlanName(planName);
        planDetail.setSubmitTime(DateUtil.getCurrentDate());
        planDetail.setRealSubmitTime(DateUtil.parseDate(realSubmitTime, "yyyy-MM-dd HH:mm:ss"));
        planDetail.setCreateTime(DateUtil.getCurrentDate());
        planDetail.setModifyTime(DateUtil.getCurrentDate());
        planDetail.setPlanStatus(0);
        planDetail.setType(1);
        planDetail.setCron(biz); //当作类型

        planDetail.setRemark(plan.getRemark());

        planDetailMapper.insertSelective(planDetail);

    }

    // TODO: 2. 将数据库中脚本记录形成文件，并上传
    public List<PlanDetail> formScript() {
        List<PlanDetail> submittableList = planDetailMapper.selectSubmittableList(DateUtil.getCurrentDate());

        return submittableList;
    }


    public PageInfo<PlanDetail> listPlans(int page, int size) {
        PageHelper.startPage(page, size);
        return PageInfo.of(planDetailMapper.selectList());

    }

}
