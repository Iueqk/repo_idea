package com.service;

import com.github.pagehelper.PageInfo;
import com.pojo.Resource;
import com.pojo.ResourceCategory;
import com.pojo.ResourceVo;

import java.util.List;

public interface ResourceService {
    List<ResourceCategory> findAllResourceCategory();

    PageInfo findAllResource(ResourceVo resourceVo);

    void saveResource(Resource resource);

    void updateResource(Resource resource);

    void deleteResource(Integer id);
}
