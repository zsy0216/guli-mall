// package com.zsy.auth.utils;
//
// import org.springframework.beans.factory.InitializingBean;
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.stereotype.Component;
//
// /**
//  * @author: zhangshuaiyin
//  * @createTime: 2020-08-10 14:08
//  **/
// @Component
// public class ConstantWxUtils implements InitializingBean {
//
//     @Value("${wx.open.app_id}")
//     private String appId;
//
//     @Value("${wx.open.app_secret}")
//     private String appSecret;
//
//     @Value("${wx.open.redirect_url}")
//     private String redirectUrl;
//
//     public static String WX_OPEN_APP_ID;
//     public static String WX_OPEN_APP_SECRET;
//     public static String WX_OPEN_REDIRECT_URL;
//
//     @Override
//     public void afterPropertiesSet() throws Exception {
//         WX_OPEN_APP_ID = appId;
//         WX_OPEN_APP_SECRET = appSecret;
//         WX_OPEN_REDIRECT_URL = redirectUrl;
//     }
// }
