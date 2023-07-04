package org.company.springcloud.msvc.courses.service;

import org.company.springcloud.msvc.courses.entity.Course;

import java.util.List;
import java.util.Optional;

public interface CourseService {

    List<Course> listAllCourses();

    Optional<Course> findCourseById(Long id);

    Course saveCourse(Course course);

    void deleteCourse(Course course);

}
