package com.service;

import com.github.pagehelper.PageInfo;
import com.pojo.PromotionAd;
import com.pojo.PromotionAdVo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

public interface PromotionAdService {

    PageInfo findAllPromotionAdByPage(PromotionAdVo promotionAdVo);

    void savePromotionAd(PromotionAd promotionAd);

    void updatePromotionAd(PromotionAd promotionAd);

    PromotionAd findPromotionAdById(Integer id);

    void updatePromotionAdStatus(Integer id,Integer status);
}
