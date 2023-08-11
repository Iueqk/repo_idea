package com.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mapper.ResourceMapper;
import com.pojo.Resource;
import com.pojo.ResourceCategory;
import com.pojo.ResourceVo;
import com.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ResourceMapper resourceMapper;

    @Override
    public List<ResourceCategory> findAllResourceCategory() {
        List<ResourceCategory> resourceCategories = resourceMapper.findAllResourceCategory();
        return resourceCategories;
    }

    @Override
    public PageInfo findAllResource(ResourceVo resourceVo) {
        PageHelper.startPage(resourceVo.getCurrentPage(), resourceVo.getPageSize());
        List<Resource> resources = resourceMapper.findAllResource(resourceVo);
        PageInfo<Resource> pageInfo = new PageInfo<>(resources);
        return pageInfo;
    }

    @Override
    public void saveResource(Resource resource) {
        Date date = new Date();
        resource.setCreatedTime(date);
        resource.setUpdatedTime(date);
        resource.setCreatedBy("iu");
        resource.setUpdatedBy("iu");
        resourceMapper.saveResource(resource);
    }

    @Override
    public void updateResource(Resource resource) {
        resource.setUpdatedTime(new Date());
        resourceMapper.updateResource(resource);
    }

    @Override
    public void deleteResource(Integer id) {
        resourceMapper.deleteResource(id);
    }
}
