package com.dilshad.social_media_rest_api.helloworld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @GetMapping(path = "hello-world")
    public String sayHello() {
        return "Hello World!";
    }

    @GetMapping(path = "hello-world-bean")
    public HelloWorldBean sayHelloBean() {
        return new HelloWorldBean("Hello World!");
    }

    @GetMapping("hello-world/path-variable/{name}")
    public HelloWorldBean sayHellowPathvariable(@PathVariable String name) {
        return new HelloWorldBean(String.format("Hello World %s", name));
    }
}
