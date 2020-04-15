package com.learn.spring.rest.securiity.hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@Validated
public class HelloWorldController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HelloWorldController.class);

    @GetMapping(value="/hello", produces = "application/json")
    public String hello() {
        LOGGER.info("accessing hello API");
        return "{\"message\": \"hello world\"}";
    }

    @RequestMapping(value="/logmeout", method = RequestMethod.POST, produces = "application/json")
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("accessing logmeout API");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "{\"message\": \"you have been logged out successfully\"}";
    }
}
