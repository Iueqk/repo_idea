package com.mapper;

import com.pojo.CourseLesson;

import java.util.List;

public interface CourseLessonMapper {
    List<CourseLesson> findLessonByCourseId(Integer courseId);
}
