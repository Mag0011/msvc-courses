package org.company.springcloud.msvc.courses.service;

import org.company.springcloud.msvc.courses.clients.UserClientRest;
import org.company.springcloud.msvc.courses.models.User;
import org.company.springcloud.msvc.courses.models.entity.Course;
import org.company.springcloud.msvc.courses.models.entity.UserCourse;
import org.company.springcloud.msvc.courses.models.external.user.ListUsersByCourseRequest;
import org.company.springcloud.msvc.courses.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    @Transactional(readOnly = true)
    public Optional<Course> findCourseCompleteDetail(Long id) {
        Optional<Course> optCourse = courseRepository.findById(id);
        if (optCourse.isPresent()){
            Course course = optCourse.get();
            if(!course.getUserCourses().isEmpty()){
                List<Long> listIds = course.getUserCourses()
                        .stream()
                        .map(UserCourse::getUserId)
                        .collect(Collectors.toList());
                ListUsersByCourseRequest request = new ListUsersByCourseRequest();
                request.setListIds(listIds);
                List<User> userResponse = clientRest.getUsersByCourse(request);

                course.setUsers(userResponse);
                return Optional.of(course);
            }
        }
        return Optional.empty();
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
    @Transactional
    public Optional<User> assignUser(User user, Long courseId) {
        Optional<Course> optCourse = courseRepository.findById(courseId);
        if(optCourse.isPresent()){
            User optUserResponse = clientRest.getUserById(user.getId());
            Course course = optCourse.get();

            UserCourse userCourse = new UserCourse();
            userCourse.setUserId(optUserResponse.getId());

            course.addUserCourse(userCourse);
            courseRepository.save(course);
            return Optional.of(optUserResponse);
        }
        return Optional.empty();
    }

    @Override
    @Transactional
    public Optional<User> createUser(User user, Long courseId) {
        Optional<Course> optCourse = courseRepository.findById(courseId);
        if(optCourse.isPresent()){
            User optUserResponse = clientRest.createUser(user);
            Course course = optCourse.get();
            UserCourse userCourse = new UserCourse();
            userCourse.setUserId(optUserResponse.getId());
            course.addUserCourse(userCourse);
            courseRepository.save(course);
            return Optional.of(optUserResponse);
        }
        return Optional.empty();
    }

    @Override
    @Transactional
    public Optional<User> unassignUser(User user, Long courseId) {
        Optional<Course> optCourse = courseRepository.findById(courseId);
        if(optCourse.isPresent()){
            User optUserResponse = clientRest.getUserById(user.getId());
            Course course = optCourse.get();
            UserCourse userCourse = new UserCourse();
            userCourse.setUserId(optUserResponse.getId());
            course.removeUserCourse(userCourse);
            courseRepository.delete(course);
            return Optional.of(optUserResponse);
        }
        return Optional.empty();
    }

    @Override
    @Transactional
    public void deleteUserCourseById(Long id) {
        courseRepository.deleteUserCourseById(id);
    }

}
