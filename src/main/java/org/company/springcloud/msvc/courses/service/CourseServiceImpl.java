package org.company.springcloud.msvc.courses.service;

import org.company.springcloud.msvc.courses.clients.UserClientRest;
import org.company.springcloud.msvc.courses.models.User;
import org.company.springcloud.msvc.courses.models.entity.Course;
import org.company.springcloud.msvc.courses.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService{

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private UserClientRest clientRest;

    @Override
    @Transactional(readOnly = true)
    public List<Course> listAllCourses() {
        return (List<Course>) courseRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Course> findCourseById(Long id) {
        return courseRepository.findById(id);
    }

    @Override
    @Transactional
    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    @Transactional
    public void deleteCourse(Course course) {
        courseRepository.delete(course);
    }

    @Override
    public Optional<User> assignUser(User user, Long courseId) {
        return Optional.empty();
    }

    @Override
    public Optional<User> createUser(User user, Long courseId) {
        return Optional.empty();
    }

    @Override
    public Optional<User> unnassignUser(User user, Long courseId) {
        return Optional.empty();
    }

}
