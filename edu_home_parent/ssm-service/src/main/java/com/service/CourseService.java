package com.service;

import com.pojo.*;
import org.apache.ibatis.annotations.Param;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface CourseService {

    List<Course> findCourseByCondition(CourseVo courseVo);

    void saveCourseOrTeacher(CourseVo coursevo) throws InvocationTargetException, IllegalAccessException;

    CourseVo findCourseById(Integer id);

    void updateCourseOrTeacher(CourseVo courseVo) throws InvocationTargetException, IllegalAccessException;

    void updateCourseStatus(Integer id, Integer status);

    List<CourseSection> findSectionAndLesson(Integer courseId);

    Course findCourseNameById(Integer courseId);

    void saveSection(CourseSection courseSection);

    void updateSection(CourseSection courseSection);

    void updateSectionStatus(Integer id,Integer status);

    void saveCourseLesson(CourseLesson courseLesson);
}
