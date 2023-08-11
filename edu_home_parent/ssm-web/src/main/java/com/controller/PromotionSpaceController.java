package com.controller;

import com.pojo.PromotionSpace;
import com.pojo.ResponseResult;
import com.service.PromotionSpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/promotionSpace")
public class PromotionSpaceController {

    @Autowired
    private PromotionSpaceService promotionSpaceService;


    @RequestMapping("/findAllPromotionSpace")
    public ResponseResult findAllPromotionSpace() {

        List<PromotionSpace> promotionSpaceList = promotionSpaceService.findAllPromotionSpace();

        return new ResponseResult(true, 200, "查询成功", promotionSpaceList);
    }


    @RequestMapping("/saveOrUpdatePromotionSpace")
    public ResponseResult saveOrUpdatePromotionSpace(@RequestBody PromotionSpace promotionSpace) {
        if (promotionSpace.getId() == null) {
            promotionSpaceService.savePromotionSpace(promotionSpace);
            return new ResponseResult(true, 200, "添加成功", promotionSpace);
        } else {
            promotionSpaceService.updatePromotionSpace(promotionSpace);
            return new ResponseResult(true, 200, "更新成功", promotionSpace);
        }
    }

    @RequestMapping("/findPromotionSpaceById")
    public ResponseResult findPromotionSpaceById(Integer id) {

        PromotionSpace promotionSpace = promotionSpaceService.findPromotionSpaceById(id);

        return new ResponseResult(true, 200, "查找成功", promotionSpace);
    }
}
