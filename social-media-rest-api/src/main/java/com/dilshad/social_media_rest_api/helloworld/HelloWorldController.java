package com.dilshad.social_media_rest_api.helloworld;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
public class HelloWorldController {

    @Autowired
    private MessageSource messageSource;

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

    @GetMapping(path = "hello-world-internationalization")
    public String sayHelloInternationalization() {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage("good.morning.message", null, "Default Message", locale);
    }
}
