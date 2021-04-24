// package com.zsy.auth.controller;
//
// import com.alibaba.fastjson.TypeReference;
// import com.zsy.common.utils.R;
// import com.zsy.common.vo.MemberResponseVo;
// import com.zsy.gulimall.auth.feign.MemberFeignService;
// import com.zsy.gulimall.auth.utils.ConstantWxUtils;
// import com.zsy.gulimall.auth.utils.HttpClientUtils;
// import lombok.extern.slf4j.Slf4j;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Controller;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
//
// import javax.servlet.http.HttpSession;
// import java.io.UnsupportedEncodingException;
// import java.net.URLEncoder;
//
// import static com.zsy.common.constant.AuthServerConstant.LOGIN_USER;
//
// /**
//  * @author: zhangshuaiyin
//  * @createTime: 2020-08-10 20:28
//  **/
//
// @Slf4j
// @Controller
// @RequestMapping(value = "/api/ucenter/wx")
// public class WxApiController {
//
//     @Autowired
//     private MemberFeignService memberFeignService;
//
//     /**
//      * 获取扫码人的信息，添加数据
//      * @return
//      */
//     @GetMapping(value = "/callback")
//     public String callback(String code,String state, HttpSession session) throws Exception {
//
//         try {
//             //得到授权临时票据code
//             System.out.println(code);
//             System.out.println(state);
//             //从redis中将state获取出来，和当前传入的state作比较
//             //如果一致则放行，如果不一致则抛出异常：非法访问
//
//             //向认证服务器发送请求换取access_token
//             //2、拿着code请求 微信固定的地址，得到两个 access_token 和 openid
//             String baseAccessTokenUrl = "https://api.weixin.qq.com/sns/oauth2/access_token" +
//                     "?appid=%s" +
//                     "&secret=%s" +
//                     "&code=%s" +
//                     "&grant_type=authorization_code";
//
//             //拼接三个参数：id 秘钥 和 code 值
//             String accessTokenUrl = String.format(
//                     baseAccessTokenUrl,
//                     ConstantWxUtils.WX_OPEN_APP_ID,
//                     ConstantWxUtils.WX_OPEN_APP_SECRET,
//                     code
//             );
//
//             String accessTokenInfo = HttpClientUtils.get(accessTokenUrl);
//             R r = memberFeignService.weixinLogin(accessTokenInfo);
//             if (r.getCode() == 0) {
//                 MemberResponseVo data = r.getData("data", new TypeReference<MemberResponseVo>() {});
//                 log.info("登录成功：用户信息：{}",data.toString());
//
//                 //1、第一次使用session，命令浏览器保存卡号，JSESSIONID这个cookie
//                 //以后浏览器访问哪个网站就会带上这个网站的cookie
//                 //TODO 1、默认发的令牌。当前域（解决子域session共享问题）
//                 //TODO 2、使用JSON的序列化方式来序列化对象到Redis中
//                 session.setAttribute(LOGIN_USER,data);
//
//                 //2、登录成功跳回首页
//                 return "redirect:http://gulimall.com";
//             } else {
//
//                 return "redirect:http://auth.gulimall.com/login.html";
//             }
//
//         } catch (Exception e) {
//             e.printStackTrace();
//         }
//         return "redirect:http://auth.gulimall.com/login.html";
//     }
//
//     /**
//      * 生成微信扫描二维码
//      * @return
//      * @throws UnsupportedEncodingException
//      */
//     @GetMapping(value = "/login")
//     public String getWxCode() throws UnsupportedEncodingException {
//
//         //微信开发平台授权baseUrl   %s相当于？表示占位符
//         String baseUrl = "https://open.weixin.qq.com/connect/qrconnect" +
//                 "?appid=%s" +
//                 "&redirect_uri=%s" +
//                 "&response_type=code" +
//                 "&scope=snsapi_login" +
//                 "&state=%s" +
//                 "#wechat_redirect";
//
//         //对redirect_url进行URLEncoder编码
//         String redirect_url = ConstantWxUtils.WX_OPEN_REDIRECT_URL;
//         redirect_url = URLEncoder.encode(redirect_url,"UTF-8");
//
//         // 防止csrf攻击（跨站请求伪造攻击）
//         //String state = UUID.randomUUID().toString().replaceAll("-", "");//一般情况下会使用一个随机数
//         String state = "hjl.mynatapp.cc";//为了让大家能够使用我搭建的外网的微信回调跳转服务器，这里填写你在ngrok的前置域名
//         System.out.println("state = " + state);
//         // 采用redis等进行缓存state 使用sessionId为key 30分钟后过期，可配置
//         //键： "wechar-open-state-" + httpServletRequest.getSession().getId()
//         //值： satte
//         //过期时间： 30分钟
//         //生成qrcodeUrl
//
//         //设置%s中的值
//         String url = String.format(
//                 baseUrl,
//                 ConstantWxUtils.WX_OPEN_APP_ID,
//                 redirect_url,
//                 "zsy"
//         );
//
//         //重定向到请求微信地址
//         return "redirect:" + url;
//     }
//
// }
