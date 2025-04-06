//package com.example.review_service.security;
//
//import feign.RequestInterceptor;
//import feign.RequestTemplate;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Component;
//
//@Component
//public class FeignClientInterceptor implements RequestInterceptor {
//
//    @Override
//    public void apply(RequestTemplate requestTemplate) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//        if (authentication != null && authentication.getCredentials() instanceof String token) {
//            requestTemplate.header("Authorization", "Bearer " + token);
//        }
//    }
//}


package com.example.review_service.security;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class FeignClientInterceptor implements RequestInterceptor {

    private static final String API_KEY = "my-secure-internal-key";

    @Override
    public void apply(RequestTemplate requestTemplate) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getCredentials() instanceof String token) {
            requestTemplate.header("Authorization", "Bearer " + token);
        }

        // Add API Key for internal service authentication
        requestTemplate.header("X-API-KEY", API_KEY);
    }
}
