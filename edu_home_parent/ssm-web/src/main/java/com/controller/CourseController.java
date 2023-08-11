package com.controller;

import com.pojo.*;
import com.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;


@RestController     /*组合注解：@Controller @ResponseBody*/
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @RequestMapping("/findCourseByCondition")  /*解析json串*/
    public ResponseResult findCourseByCondition(@RequestBody CourseVo courseVo) {

        List<Course> courses = courseService.findCourseByCondition(courseVo);

        ResponseResult responseResult = new ResponseResult(true, 200, "查询成功", courses);

        return responseResult;
    }


    @RequestMapping("/courseUpload")
    public ResponseResult courseUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {

        //  1.判断是否为空
        if (file.isEmpty())
            throw new RuntimeException();

        // 2.获取部署路径
        // D:\program\apache-tomcat-8.5.91\webapps\ssm_web
        String realPath = request.getServletContext().getRealPath("/");
        // D:\program\apache-tomcat-8.5.91\webapps\
        String substring = realPath.substring(0, realPath.indexOf("ssm_web"));

        //  3.获取原始文件名
        //  flower.jpg
        String originalFilename = file.getOriginalFilename();

        //  4.拼接新文件名
        //  15687465.jpg
        String newFileName = System.currentTimeMillis() + originalFilename.substring(originalFilename.lastIndexOf("."));

        //  5.文件上传
        String uploadPath = substring + "upload\\";
        File filePath = new File(uploadPath, newFileName);
        //  如果目录不存在就创建目录
        if (!filePath.getParentFile().exists()) {
            filePath.getParentFile().mkdirs();
            System.out.println("创建好了目录->" + filePath);
        }
        //  图片真正的上传
        file.transferTo(filePath);

        //  6.将文件名和文件路径进行返回
        HashMap<String, String> map = new HashMap<>();
        map.put("fileName", newFileName);
        //  "http://localhost:8080/upload/1597112871741.JPG"
        map.put("filePath", "http://localhost:8080/upload/" + newFileName);

        return new ResponseResult(true, 200, "图片上传成功", map);
    }

    @RequestMapping("/saveOrUpdateCourse")
    public ResponseResult saveOrUpdateCourse(@RequestBody CourseVo courseVo) throws InvocationTargetException, IllegalAccessException {
        String MESSAGE = "";
        if (courseVo.getId() == null) {
            courseService.saveCourseOrTeacher(courseVo);
            MESSAGE = "添加成功";
        } else {
            courseService.updateCourseOrTeacher(courseVo);
            MESSAGE = "更新成功";
        }
        return new ResponseResult(true, 200, MESSAGE, null);
    }

    @RequestMapping("/findCourseById")
    public ResponseResult findCourseById(Integer id) {

        CourseVo courseVo = courseService.findCourseById(id);

        return new ResponseResult(true, 200, "查询成功", courseVo);
    }

    @RequestMapping("/updateCourseStatus")
    public ResponseResult updateCourseStatus(@RequestParam("id") Integer id, @RequestParam("status") Integer status) {

        courseService.updateCourseStatus(id, status);

        HashMap<String, Integer> map = new HashMap<>();

        map.put("status", status);

        return new ResponseResult(true, 200, "更新成功", map);
    }

    @RequestMapping("/findSectionAndLesson")
    public ResponseResult findSectionAndLesson(Integer courseId) {

        List<CourseSection> courseSectionList = courseService.findSectionAndLesson(courseId);

        return new ResponseResult(true, 200, "查询成功", courseSectionList);
    }

    @RequestMapping("/findCourseNameById")
    public ResponseResult findCourseNameById(Integer courseId) {

        Course course = courseService.findCourseNameById(courseId);

        HashMap<String, Object> map = new HashMap<>();
        map.put("id", courseId);
        map.put("courseName", course.getCourseName());

        return new ResponseResult(true, 200, "查询成功", map);
    }

    @RequestMapping("/saveOrUpdateSection")
    public ResponseResult saveOrUpdateSection(@RequestBody CourseSection courseSection) {

        String MESSAGE;

        if (courseSection.getId() == null) {
            courseService.saveSection(courseSection);
            MESSAGE = "保存成功";
        } else {
            courseService.updateSection(courseSection);
            MESSAGE = "更新成功";
        }
        return new ResponseResult(true, 200, MESSAGE, null);
    }

    @RequestMapping("/updateSectionStatus")
    public ResponseResult updateSectionStatus(@RequestParam("id") Integer id, @RequestParam("status") Integer status) {

        courseService.updateSectionStatus(id, status);

        HashMap<String, Integer> map = new HashMap<>();
        map.put("status", status);

        return new ResponseResult(true, 200, "更新成功", map);
    }

    @RequestMapping("/saveCourseLesson")
    public ResponseResult saveCourseLesson(@RequestBody CourseLesson courseLesson) {

        courseService.saveCourseLesson(courseLesson);

        return new ResponseResult(true, 200, "保存成功", courseLesson);
    }
}
