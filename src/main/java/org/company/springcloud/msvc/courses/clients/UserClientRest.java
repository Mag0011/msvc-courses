package org.company.springcloud.msvc.courses.clients;

import org.company.springcloud.msvc.courses.models.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@FeignClient(value = "msvc-users", url = "localhost:8002")
public interface UserClientRest {

    @GetMapping("/{id}")
    public User getUserById(@PathVariable(name = "id") Long id);

    @PostMapping("/createUser")
    public User createUser( @RequestBody User user);

}
