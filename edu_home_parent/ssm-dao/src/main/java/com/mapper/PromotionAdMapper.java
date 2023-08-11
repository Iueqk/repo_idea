package com.mapper;

import com.pojo.PromotionAd;

import java.util.List;

public interface PromotionAdMapper {


    List<PromotionAd> findAllPromotionAdByPage();

    void savePromotionAd(PromotionAd promotionAd);

    void updatePromotionAd(PromotionAd promotionAd);

    PromotionAd findPromotionAdById(Integer id);

    void updatePromotionAdStatus(PromotionAd promotionAd);
}
