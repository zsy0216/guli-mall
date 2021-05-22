package com.zsy.sso.client.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: zhangshuaiyin
 * @date: 2021/5/22 20:28
 */
@Controller
public class HelloController {
    /**
     * 无需登录就可访问
     *
     * @return
     */
    @ResponseBody
    @GetMapping(value = "/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping(value = "/employees")
    public String employees(Model model, HttpSession session,
                            @RequestParam(value = "token", required = false) String token) {

        if (!StringUtils.isEmpty(token)) {
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> forEntity = restTemplate.getForEntity("http://localhost:8080/userinfo?token=" + token, String.class);
            String body = forEntity.getBody();

            session.setAttribute("loginUser", body);
        }
        Object loginUser = session.getAttribute("loginUser");

        if (loginUser == null) {
            return "redirect:" + "http://localhost:8080/login.html" + "?redirect_url=http://localhost:8081/employees";
        } else {
            List<String> emps = new ArrayList<>();

            emps.add("张三");
            emps.add("李四");

            model.addAttribute("emps", emps);
            return "employees";
        }
    }

}
