package com.mapper;

import com.pojo.Resource;
import com.pojo.ResourceCategory;
import com.pojo.ResourceVo;

import java.util.List;

public interface ResourceMapper {

    List<ResourceCategory> findAllResourceCategory();

    List<Resource> findAllResource(ResourceVo resourceVo);

    void saveResource(Resource resource);

    void updateResource(Resource resource);

    void deleteResource(Integer id);

}
