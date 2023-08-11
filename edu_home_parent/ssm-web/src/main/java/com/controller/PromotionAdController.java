package com.controller;

import com.github.pagehelper.PageInfo;
import com.pojo.PromotionAd;
import com.pojo.PromotionAdVo;
import com.pojo.ResponseResult;
import com.service.PromotionAdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/promotionAd")
public class PromotionAdController {

    @Autowired
    private PromotionAdService promotionAdService;

    @RequestMapping("/findAllPromotionAdByPage")
    public ResponseResult findAllPromotionAdByPage(Integer currentPage, Integer pageSize) {

        PromotionAdVo promotionAdVo = new PromotionAdVo();
        promotionAdVo.setCurrentPage(currentPage);
        promotionAdVo.setPageSize(pageSize);
        PageInfo pageInfo = promotionAdService.findAllPromotionAdByPage(promotionAdVo);

        return new ResponseResult(true, 200, "分页成功", pageInfo);
    }

    @RequestMapping("/promotionAdUpload")
    public ResponseResult promotionAdUpload(MultipartFile file, HttpServletRequest request) throws IOException {
        String originalFilename = file.getOriginalFilename();
        String suffix = originalFilename.substring(originalFilename.lastIndexOf('.'));
        String newFileName = System.currentTimeMillis() + suffix;

        String realPath = request.getServletContext().getRealPath("/");
        String baseUrl = realPath.substring(0, realPath.indexOf("ssm_web"));
        String uploadPath = baseUrl + "upload\\";

        File filePath = new File(uploadPath, newFileName);
        if (!filePath.getParentFile().exists()) {
            filePath.getParentFile().mkdirs();
            System.out.println("哈哈哈哈哈");
        }
        file.transferTo(filePath);
        Map<String, String> map = new HashMap<>();
        map.put("fileName", newFileName);
        map.put("filePath", "http://localhost:8080/upload/" + newFileName);

        System.out.println(baseUrl);
        System.out.println(uploadPath);
        System.out.println(filePath + "");
        return new ResponseResult(true, 200, "图片上传成功", map);
    }

    @RequestMapping("/saveOrUpdatePromotionAd")
    public ResponseResult saveOrUpdatePromotionAd(@RequestBody PromotionAd promotionAd) {
        if (promotionAd.getId() == null) {
            promotionAdService.savePromotionAd(promotionAd);
            return new ResponseResult(true, 200, "添加广告成功", promotionAd);
        } else {
            promotionAdService.updatePromotionAd(promotionAd);
            return new ResponseResult(true, 200, "更新广告成功", promotionAd);
        }
    }

    @RequestMapping("/findPromotionAdById")
    public ResponseResult findPromotionAdById(@RequestParam("id") Integer id) {
        PromotionAd promotionAd = promotionAdService.findPromotionAdById(id);
        return new ResponseResult(true, 200, "查找成功", promotionAd);
    }

    @RequestMapping("/updatePromotionAdStatus")
    public ResponseResult updatePromotionAdStatus(@RequestParam("id") Integer id, @RequestParam("status") Integer status) {
        promotionAdService.updatePromotionAdStatus(id, status);
        Map<String, Integer> map = new HashMap<>();
        map.put("status", status);
        return new ResponseResult(true, 200, "修改广告信息状态成功", map);
    }
}
