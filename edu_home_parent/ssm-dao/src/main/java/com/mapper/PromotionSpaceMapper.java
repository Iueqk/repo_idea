package com.mapper;

import com.pojo.PromotionAd;
import com.pojo.PromotionAdVo;
import com.pojo.PromotionSpace;

import java.util.List;

public interface PromotionSpaceMapper {

    List<PromotionSpace> findAllPromotionSpace();

    void savePromotionSpace(PromotionSpace promotionSpace);

    void updatePromotionSpace(PromotionSpace promotionSpace);

    PromotionSpace findPromotionSpaceById(Integer id);

}
