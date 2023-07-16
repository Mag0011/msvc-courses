package org.company.springcloud.msvc.courses.models.entity;
import org.company.springcloud.msvc.courses.models.User;

import javax.persistence.*;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "courses")
public class Course {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotBlank(message = "Name cannot be either blank nor null")
    private String name;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "course_id")
    private List<UserCourse> userCourses;

    @Transient
    private List<User> users;

    public Course(){
        userCourses = new ArrayList<>();
        users = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<UserCourse> getUserCourses() {
        return userCourses;
    }

    public void setUserCourses(List<UserCourse> userCourses) {
        this.userCourses = userCourses;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public void addUserCourse(UserCourse course){
        userCourses.add(course);
    }

    public void removeUserCourse(UserCourse course){
        userCourses.add(course);
    }

}
