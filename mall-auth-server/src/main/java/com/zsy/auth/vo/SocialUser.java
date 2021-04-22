package com.zsy.auth.vo;

import lombok.Data;

/**
 * @Description: 社交用户信息
 * @author: zhangshuaiyin
 * @createTime: 2021-04-22 19:07
 **/
@Data
public class SocialUser {

    private String accessToken;

    private String remindIn;

    private long expiresIn;

    private String uid;

    private String isRealName;

}
