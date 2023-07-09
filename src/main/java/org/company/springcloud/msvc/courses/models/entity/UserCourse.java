package org.company.springcloud.msvc.courses.models.entity;

import javax.persistence.*;

@Entity
@Table(name = "user_courses")
public class UserCourse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", unique = true)
    private Long userId;



    public UserCourse(){
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if (!(obj instanceof UserCourse))
            return false;
        UserCourse o = (UserCourse) obj;
        return this.userId != null && this.userId.equals(o.userId);
    }

}
