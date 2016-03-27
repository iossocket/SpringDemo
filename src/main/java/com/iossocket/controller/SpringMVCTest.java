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

@SessionAttributes(value = {"user"}, types = {String.class})
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

    @RequestMapping("/testSessionAttributes")
    public String testSessionAttributes(Map<String, Object> map) {
        User user = new User(1, "Tom", "123", "Tom@gmail.com", 15);
        map.put("user", user);
        map.put("school", "xjtu");
        return "time";
    }

    /**
     * @ModelAttribute 标记的方法,会在每个目标方法执行之前被SpringMVC调用
     */
    @ModelAttribute
    public void getUser(@RequestParam(value = "id", required = false) Integer id, Map<String, Object> map) {
        if (id != null) {
            User user = new User(1, "zxl", "123", "zxl@1.com", 12);
            System.out.println("Get model from db: " + user);

            map.put("user", user);
        }
    }

    /**
     * 运行流程
     * 1. 执行@ModelAttributes 注解修饰的方法: 从数据库中取出对象, 把对象放入Map中, Key为user
     * 2. SpringMVC 从Map中取出User对象, 并把表单的请求参数赋给该User 对象的对应属性.
     * 3. SpringMVC 将上一步修改过的user对象传入如下方法.
     *
     * Attention: 在@ModelAttribute修饰的方法中, 放入到Map时, Key值需要和Value的类同名(但首字母小写)
     */
    @RequestMapping("/testModelAttributes")
    public String testModelAttributes(@ModelAttribute("user") User user) {
        System.out.println("update: " + user);
        return SUCCESS;
    }
}
