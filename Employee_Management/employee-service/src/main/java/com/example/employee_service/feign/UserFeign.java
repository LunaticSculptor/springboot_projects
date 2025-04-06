package com.example.employee_service.feign;

import com.example.employee_service.model.Users;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@FeignClient(name = "USER-SERVICE")
public interface UserFeign {

    @GetMapping("/getUsersByIds")
    Map<Integer, Optional<Users>> getUsersByIds(@RequestParam Set<Integer> ids);
}
