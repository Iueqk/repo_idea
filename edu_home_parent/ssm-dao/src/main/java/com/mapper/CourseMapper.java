package com.mapper;

import com.pojo.*;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface CourseMapper {

    /**
     * 按条件查询
     */
    List<Course> findCourseByCondition(CourseVo courseVo);

    /**
     * 保存课程信息 并返回Id值
     */
    void saveCourse(Course course);

    /**
     * 保存教师信息
     */
    void saveTeacher(Teacher teacher);


    CourseVo findCourseById(Integer id);

    void updateCourse(Course course);

    void updateTeacher(Teacher teacher);

    void updateCourseStatus(Course course);

    List<CourseSection> findSectionAndLesson(Integer courseId);

    List<CourseLesson> findLessonByCourseId(Integer courseId);

    Course findCourseNameById(Integer courseId);

    void saveSection(CourseSection courseSection);

    void updateSection(CourseSection courseSection);

    @Update("update course_section set status = #{status},update_time =#{updateTime} where id = #{id}")
    void updateSectionStatus(CourseSection courseSection);

    void saveCourseLesson(CourseLesson courseLesson);
}
