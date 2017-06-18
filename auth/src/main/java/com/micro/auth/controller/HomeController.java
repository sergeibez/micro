package com.micro.auth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for Home page
 *
 * @author Sergey Bezvershenko
 */
@RestController
public class HomeController {
    /**
     * Root path
     */
    @GetMapping("/")
    public String home() {
        return "{ \"service\" : \"Authorization service\" }";
    }
}
