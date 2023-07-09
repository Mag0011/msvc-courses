package org.company.springcloud.msvc.courses.service;

import org.company.springcloud.msvc.courses.models.User;
import org.company.springcloud.msvc.courses.models.entity.Course;

import java.util.List;
import java.util.Optional;

public interface CourseService {

    List<Course> listAllCourses();

    Optional<Course> findCourseById(Long id);

    Course saveCourse(Course course);

    void deleteCourse(Course course);

    Optional<User> assignUser(User user, Long courseId);

    Optional<User> createUser(User user, Long courseId);

    Optional<User> unnassignUser(User user, Long courseId);


}
