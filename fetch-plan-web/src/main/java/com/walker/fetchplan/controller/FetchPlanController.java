package com.walker.fetchplan.controller;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.github.pagehelper.PageInfo;
import com.walker.fetchplan.dal.model.PlanDetail;
import com.walker.fetchplan.entity.PlanScriptsTO;
import com.walker.fetchplan.service.PlanFetchService;
import com.walker.fetchplan.web.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.CollectionUtils;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotBlank;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@RequestMapping("/plan")
public class FetchPlanController {

    @NacosValue("localFilePath")
    private String localFilePath;

    private final PlanFetchService planFetchService;


    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse fetchPlan(
            @RequestParam("plan_submit_time")String submitTimeValue,
            @RequestParam("type") @NotBlank String type,
            HttpServletRequest request
    ) throws IOException {

        CommonResponse commonResponse = new CommonResponse();

        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultiValueMap<String, MultipartFile> multiFileMap = multipartRequest.getMultiFileMap();

        List<MultipartFile> multipartFiles = new ArrayList<>();
        for (List<MultipartFile> files : multiFileMap.values()) {
            if (files != null) {
                multipartFiles.addAll(files);
            }
        }

        if (CollectionUtils.isEmpty(multipartFiles)) {
            commonResponse.setMsg("请上传文件");
            commonResponse.setCode(1111);
            return commonResponse;
        }


        List<PlanScriptsTO> plans = new ArrayList<>();
        for (MultipartFile multipartFile : multipartFiles) {
            PlanScriptsTO planScriptsTO = new PlanScriptsTO();
            planScriptsTO.setPlanName(multipartFile.getOriginalFilename());
            planScriptsTO.setPlanStatus(0);
            planScriptsTO.setRealSubmitTime(submitTimeValue);
            planScriptsTO.setType(type);
            planScriptsTO.setRemark(new String(multipartFile.getBytes(), StandardCharsets.UTF_8));

            plans.add(planScriptsTO);
        }

        commonResponse.setCode(0000);
        commonResponse.setMsg("上传成功");

        planFetchService.multiplePlanUpload(plans);
        return commonResponse;
    }

    @GetMapping("/list")
    public PageInfo<PlanDetail> listPlans(@RequestParam int pageNum, @RequestParam int pageSize) {
        return planFetchService.listPlans(pageNum, pageSize);
    }

    @GetMapping("/submit")
    public List<PlanDetail> listSubmittablePlans() {
        return planFetchService.formScript();
    }
}
