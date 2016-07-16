package com.zinbo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
@RestController vs @Controller
"The key difference between a traditional Spring MVC controller and the RESTful web service controller is the way the HTTP response body is created.
While the traditional MVC controller relies on the View technology,
the RESTful web service controller simply returns the object and the object data is written directly to the HTTP response as JSON/XML. "

"https://www.genuitec.com/spring-frameworkrestcontroller-vs-controller/"
 */
@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String hello(){
        return "hello";
    }
}
