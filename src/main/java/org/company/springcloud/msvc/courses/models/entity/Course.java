package org.company.springcloud.msvc.courses.models.entity;
import org.company.springcloud.msvc.courses.models.User;

import javax.persistence.*;

import javax.validation.constraints.NotBlank;
import java.util.Collections;
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
        userCourses = Collections.emptyList();
        users = Collections.emptyList();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void addUserCourse(UserCourse course){
        userCourses.add(course);
    }

    public void removeUserCourse(UserCourse course){
        userCourses.add(course);
    }

}
