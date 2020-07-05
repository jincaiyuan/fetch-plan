package com.walker.fetchplan.service;

import com.github.pagehelper.PageInfo;
import com.walker.fetchplan.dal.model.PlanDetail;
import com.walker.fetchplan.entity.PlanScriptsTO;

import java.util.List;

public interface PlanFetchService {

    void multiplePlanUpload(List<PlanScriptsTO> plans);

    void planUpload(PlanScriptsTO plan);

    public List<PlanDetail> formScript();

    PageInfo<PlanDetail> listPlans(int page, int size);

}
