package com.iossocket.controller;

import com.iossocket.entities.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;


@RequestMapping("/springmvc")
@Controller
public class SpringMVCTest {


    private static final String SUCCESS = "success";

    // RequestMapping 可以修饰方法,也可修饰类
    // 此时如果需要方法该类中的方法,使用  /springmvc/testRequestMapping
    @RequestMapping("/testRequestMapping")
    public String testRequestMapping() {
        System.out.println("testRequestMapping");
        return SUCCESS;
    }

    @RequestMapping(value = "/testMethod", method = RequestMethod.POST)
    public String testMethod() {
        System.out.println("testMehod");
        return SUCCESS;
    }

    @RequestMapping(value = "testParamsAndHeader",
            params = {"username", "age!=10"},
            headers = {"username=zxl"}
    )
    public String testParamsAndHeader() {
        System.out.println("testParamsAndHeader");
        return SUCCESS;
    }

    @RequestMapping("/testAntPath/*/abc")
    public String tsetAntPath() {
        System.out.println("testAntPath");
        return SUCCESS;
    }


    @RequestMapping("/testPathVariable/{id}")
    public String testPathVariable(@PathVariable("id") Integer id) {
        System.out.println("testPathVariable" + id);
        return SUCCESS;
    }

    @RequestMapping("/testRequestParam")
    public String testRequestParam(@RequestParam("username") String username,
                                   @RequestParam(value = "password", required = false, defaultValue = "123") String password) {
        System.out.println(username + "+" + password);
        return SUCCESS;
    }

    @RequestMapping("/testRequestHeader")
    public String testRequestHeader(@RequestHeader(value = "user", required = true, defaultValue = "123") String user) {
        System.out.println("header user" + user);
        return SUCCESS;
    }

    @RequestMapping("/testCookieValue")
    public String testCookieValue(@CookieValue("JSESSIONID") String sessionId) {
        System.out.println("Cookie value: " + sessionId);
        return SUCCESS;
    }

    @RequestMapping("/testPojo")
    public String testPojo(User user) {
        System.out.println("testPojo: " + user);
        return SUCCESS;
    }

    @RequestMapping("/testServletAPI")
    public void testServletAPI(HttpServletRequest request, HttpServletResponse response,
                               Writer out) throws IOException {
        System.out.println("testServletAPI, request" + request + ", response" + response);
        out.write("hello springmvc");
    }

    @RequestMapping("/testModelAndView")
    public ModelAndView testModelAndView() {

        ModelAndView modelAndView = new ModelAndView("time");
        modelAndView.addObject("time", new Date());

        return modelAndView;
    }

    @RequestMapping("/testMap")
    public String testMap(Map<String, Object> map) {
//        Map<String, Object> map = new HashMap<String, Object>();
        map.put("names", Arrays.asList("Tom", "Jerry", "Mike"));
        return "time";
    }
}
