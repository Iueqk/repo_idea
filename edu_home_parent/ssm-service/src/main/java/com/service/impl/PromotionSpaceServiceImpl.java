package com.service.impl;

import com.mapper.PromotionSpaceMapper;
import com.pojo.PromotionSpace;
import com.service.PromotionSpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class PromotionSpaceServiceImpl implements PromotionSpaceService {

    @Autowired
    private PromotionSpaceMapper promotionSpaceMapper;


    @Override
    public List<PromotionSpace> findAllPromotionSpace() {

        List<PromotionSpace> promotionSpaceList = promotionSpaceMapper.findAllPromotionSpace();

        return promotionSpaceList;
    }

    @Override
    public void savePromotionSpace(PromotionSpace promotionSpace) {

        Date date = new Date();

        promotionSpace.setSpaceKey(UUID.randomUUID().toString());
        promotionSpace.setCreateTime(date);
        promotionSpace.setUpdateTime(date);
        promotionSpace.setIsDel(0);

        promotionSpaceMapper.savePromotionSpace(promotionSpace);
    }

    @Override
    public void updatePromotionSpace(PromotionSpace promotionSpace) {
        promotionSpace.setUpdateTime(new Date());

        promotionSpaceMapper.updatePromotionSpace(promotionSpace);
    }

    @Override
    public PromotionSpace findPromotionSpaceById(Integer id) {
        PromotionSpace promotionSpace = promotionSpaceMapper.findPromotionSpaceById(id);

        return promotionSpace;
    }
}
