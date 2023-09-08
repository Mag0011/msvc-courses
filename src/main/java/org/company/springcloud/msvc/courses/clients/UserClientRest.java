package org.company.springcloud.msvc.courses.clients;

import org.company.springcloud.msvc.courses.models.User;
import org.company.springcloud.msvc.courses.models.external.user.ListUsersByCourseRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "msvc-users", url = "user-service:8001")
public interface UserClientRest {

    @GetMapping("/getById/{id}")
    User getUserById(@PathVariable(name = "id") Long id);

    @PostMapping("/createUser")
    User createUser( @RequestBody User user);

    @PostMapping("/getUsersByCourse")// fixme: why only accepts PostMappings and not Get...
    List<User> getUsersByCourse(@RequestBody ListUsersByCourseRequest request);

}
