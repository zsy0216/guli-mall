package com.zsy.sso.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * @author: zhangshuaiyin
 * @date: 2021/5/22 20:36
 */
@Controller
public class LoginController {

    @Autowired
    StringRedisTemplate redisTemplate;

    @ResponseBody
    @GetMapping("/userinfo")
    public String userinfo(@RequestParam(value = "token") String token) {
        return redisTemplate.opsForValue().get(token);

    }

    @GetMapping("/login.html")
    public String loginPage(@RequestParam("redirect_url") String url, Model model,
                            @CookieValue(value = "sso_token", required = false) String sso_token) {
        if (!StringUtils.isEmpty(sso_token)) {
            return "redirect:" + url + "?token=" + sso_token;
        }
        model.addAttribute("url", url);
        return "login";
    }

    @PostMapping(value = "/doLogin")
    public String doLogin(@RequestParam("username") String username,
                          @RequestParam("password") String password,
                          @RequestParam("redirect_url") String url,
                          HttpServletResponse response) {

        //登录成功跳转，跳回到登录页
        if (!StringUtils.isEmpty(username) && !StringUtils.isEmpty(password)) {

            String uuid = UUID.randomUUID().toString().replace("-", "");
            redisTemplate.opsForValue().set(uuid, username);
            Cookie ssoToken = new Cookie("sso_token", uuid);

            response.addCookie(ssoToken);
            return "redirect:" + url + "?token=" + uuid;
        }
        return "login";
    }
}
