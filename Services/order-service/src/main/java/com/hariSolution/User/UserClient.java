package com.hariSolution.User;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
@FeignClient(name = "user-service", url = "${application.config.user-url}")
public interface UserClient {
    @GetMapping("/get-user/{user-id}")
    UserDetails getUserDetailsByUserId(@PathVariable(value = "user-id") Integer userId);


}
