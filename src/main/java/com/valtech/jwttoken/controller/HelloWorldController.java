package com.valtech.jwttoken.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/classlevel")
public class HelloWorldController {

  @RequestMapping(value = "/getNew", method = GET, produces = "application/json")
  public String getSomethingNew(@RequestParam String name) {
    return "hello" + name + "g, i am new";
  }
}
