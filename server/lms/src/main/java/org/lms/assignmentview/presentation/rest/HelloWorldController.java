package org.lms.assignmentview.presentation.rest;

import org.lms.assignmentview.presentation.rest.dto.HelloDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @GetMapping("/hello-world")
    public HelloDto test() {
        return new HelloDto("Hello world!");
    }
}
