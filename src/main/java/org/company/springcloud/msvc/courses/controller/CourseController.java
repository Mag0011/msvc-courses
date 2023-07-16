package org.company.springcloud.msvc.courses.controller;

import feign.FeignException;
import org.company.springcloud.msvc.courses.models.User;
import org.company.springcloud.msvc.courses.utils.RequestValidationService;
import org.company.springcloud.msvc.courses.models.entity.Course;
import org.company.springcloud.msvc.courses.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private RequestValidationService requestValidationService;

    @GetMapping("/getAllCourses")
    public List<Course> getAllCourses(){
        return courseService.listAllCourses();
    }

    @GetMapping("/getCourseById/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable(name = "id") Long id){
        Optional<Course> optionalCourse = courseService.findCourseById(id);
        return optionalCourse.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/getCourseCompleteDetail/{id}")
    public ResponseEntity<Course> getCourseByUsers(@PathVariable(name = "id") Long id){
        Optional<Course> optionalCourse = courseService.findCourseCompleteDetail(id);
        return optionalCourse.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/createCourse")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createCourse(@Valid @RequestBody Course Course, BindingResult validationResult){
        if(validationResult.hasErrors()){
            Map<String, String> mapErrors = requestValidationService.validateRequest(validationResult.getFieldErrors());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mapErrors);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(courseService.saveCourse(Course));
    }

    @PutMapping("/editCourse/{id}")
    public ResponseEntity<?> editCourse(@Valid @RequestBody Course courseRequest ,@PathVariable Long id, BindingResult validationResult){
        if(validationResult.hasErrors()){
            Map<String, String> mapErrors = requestValidationService.validateRequest(validationResult.getFieldErrors());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mapErrors);
        }

        Optional<Course> optionalCourseDb = courseService.findCourseById(id);
        if(optionalCourseDb.isPresent()){
            Course courseUpdated = optionalCourseDb.get();
            courseUpdated.setName(courseRequest.getName());
            return ResponseEntity.status(HttpStatus.CREATED).body(courseService.saveCourse(courseUpdated));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/deleteCourse/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id){
        Optional<Course> courseDB = courseService.findCourseById(id);
        if(courseDB.isPresent()) {
            courseService.deleteCourse(courseDB.get());
            return ResponseEntity.noContent().build();
        }
        return  ResponseEntity.notFound().build();
    }

    @PutMapping("assignUser/{course_id}")
    public ResponseEntity<?> assignUser(@RequestBody User user, @PathVariable Long course_id){
        //TODO: move to ExceptionHandler
        Optional<User> optUser;
        try{
            optUser = courseService.assignUser(user, course_id);
        }catch(FeignException ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("message","User with id" + user.getId() + "[ " + ex.getMessage() + " ]" ));
        }
        if(optUser.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(optUser.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("createUser/{course_id}")
    public ResponseEntity<?> createUser(@RequestBody User user, @PathVariable Long course_id){
        //TODO: move to ExceptionHandler
        Optional<User> optUser;
        try{
            optUser = courseService.createUser(user, course_id);
        }catch(FeignException ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("message","Not possible to create [ " + ex.getMessage() + " ]" ));
        }
        if(optUser.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(optUser.get());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("deleteUser/{course_id}")
    public ResponseEntity<?> deleteUser(@RequestBody User user, @PathVariable Long course_id){
        //TODO: move to ExceptionHandler
        Optional<User> optUser;
        try{
            optUser = courseService.unassignUser(user, course_id);
        }catch(FeignException ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("message","Not possible to create [ " + ex.getMessage() + " ]" ));
        }
        if(optUser.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(optUser.get());
        }
        return ResponseEntity.notFound().build();
    }

}
