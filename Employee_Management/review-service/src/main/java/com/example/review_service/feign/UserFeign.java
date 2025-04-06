package com.example.review_service.feign;

import com.example.review_service.model.Users;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@FeignClient(name = "USER-SERVICE")
public interface UserFeign {

    @GetMapping("/getUsersByIds")
    Map<Integer, Optional<Users>> getUsersByIds(@RequestParam Set<Integer> ids);
}
