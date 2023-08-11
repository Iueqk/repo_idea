package com.service;


import com.github.pagehelper.PageInfo;
import com.pojo.PromotionAdVo;
import com.pojo.PromotionSpace;

import java.util.List;

public interface PromotionSpaceService {

    List<PromotionSpace> findAllPromotionSpace();

    void savePromotionSpace(PromotionSpace promotionSpace);

    void updatePromotionSpace(PromotionSpace promotionSpace);

    PromotionSpace findPromotionSpaceById(Integer id);

}
