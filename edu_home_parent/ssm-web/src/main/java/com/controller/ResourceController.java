package com.controller;

import com.github.pagehelper.PageInfo;
import com.pojo.Resource;
import com.pojo.ResourceCategory;
import com.pojo.ResourceVo;
import com.pojo.ResponseResult;
import com.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/resource")
public class ResourceController {
    @Autowired
    private ResourceService resourceService;

    @RequestMapping("/findAllResourceCategory")
    public ResponseResult findAllResourceCategory() {
        List<ResourceCategory> categories = resourceService.findAllResourceCategory();
        return new ResponseResult(true, 200, "响应成功", categories);
    }

    @RequestMapping("/findAllResource")
    public ResponseResult findAllResource(@RequestBody ResourceVo resourceVo) {
        PageInfo pageInfo = resourceService.findAllResource(resourceVo);
        return new ResponseResult(true, 200, "响应成功", pageInfo);
    }

    @RequestMapping("/saveOrUpdateResource")
    public ResponseResult saveOrUpdateResource(@RequestBody Resource resource) {
        if (resource.getId() == null) {
            resourceService.saveResource(resource);
        } else resourceService.updateResource(resource);
        return new ResponseResult(true, 200, "响应成功", resource);
    }

    @DeleteMapping("/deleteResource/{id}")
    // http://localhost:8080/ssm_web/resource/deleteResource/53
    public ResponseResult deleteResource(@PathVariable("id") Integer id) {
        resourceService.deleteResource(id);
        return new ResponseResult(true, 200, "响应成功", id);
    }
}

