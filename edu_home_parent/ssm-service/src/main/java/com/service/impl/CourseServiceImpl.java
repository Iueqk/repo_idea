package com.service.impl;

import com.mapper.CourseMapper;
import com.pojo.*;
import com.service.CourseService;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseMapper courseMapper;


    @Override
    public List<Course> findCourseByCondition(CourseVo courseVo) {

        List<Course> courses = courseMapper.findCourseByCondition(courseVo);

        return courses;
    }

    @Override
    public void saveCourseOrTeacher(CourseVo coursevo) throws InvocationTargetException, IllegalAccessException {

        //  封装Course类
        Course course = new Course();
        BeanUtils.copyProperties(course, coursevo);
        //  补全课程信息
        Date date = new Date();
        course.setCreateTime(date);
        course.setUpdateTime(date);
        //获取新插入数据的id
        int id = course.getId();

        //封装讲师信息
        Teacher teacher = new Teacher();
        BeanUtils.copyProperties(teacher, coursevo);

        //补全信息
        teacher.setIsDel(0);
        teacher.setCourseId(id);
        teacher.setCreateTime(date);
        teacher.setUpdateTime(date);

        //保存讲师信息
        courseMapper.saveTeacher(teacher);
    }

    @Override
    public CourseVo findCourseById(Integer id) {
        CourseVo courseVo = courseMapper.findCourseById(id);

        return courseVo;
    }

    @Override
    public void updateCourseOrTeacher(CourseVo courseVo) throws InvocationTargetException, IllegalAccessException {
        Date date = new Date();

        Course course = new Course();
        BeanUtils.copyProperties(course, courseVo);
        course.setUpdateTime(date);

        Teacher teacher = new Teacher();
        BeanUtils.copyProperties(teacher, courseVo);
        teacher.setUpdateTime(date);
        teacher.setCourseId(course.getId());

        courseMapper.updateCourse(course);
        courseMapper.updateTeacher(teacher);
    }

    @Override
    public void updateCourseStatus(Integer id, Integer status) {
        Course course = new Course();
        course.setId(id);
        course.setStatus(status);
        course.setUpdateTime(new Date());
        courseMapper.updateCourseStatus(course);
    }

    @Override
    public List<CourseSection> findSectionAndLesson(Integer courseId) {

        List<CourseSection> courseSectionList = courseMapper.findSectionAndLesson(courseId);
        System.out.println("------>"+courseSectionList);
        return courseSectionList;
    }

    @Override
    public Course findCourseNameById(Integer courseId) {

        Course course = courseMapper.findCourseNameById(courseId);

        return course;

    }

    @Override
    public void saveSection(CourseSection courseSection) {

        Date date = new Date();
        courseSection.setCreateTime(date);
        courseSection.setUpdateTime(date);

        courseMapper.saveSection(courseSection);
    }

    @Override
    public void updateSection(CourseSection courseSection) {

        courseSection.setUpdateTime(new Date());

        courseMapper.updateSection(courseSection);

    }

    @Override
    public void updateSectionStatus(Integer id,Integer status) {

        CourseSection courseSection = new CourseSection();

        courseSection.setId(id);
        courseSection.setStatus(status);
        courseSection.setCreateTime(new Date());
        courseSection.setUpdateTime(new Date());

        courseMapper.updateSectionStatus(courseSection);
    }

    @Override
    public void saveCourseLesson(CourseLesson courseLesson) {

        Date date = new Date();
        courseLesson.setCreateTime(date);
        courseLesson.setUpdateTime(date);

        courseMapper.saveCourseLesson(courseLesson);
    }
}
