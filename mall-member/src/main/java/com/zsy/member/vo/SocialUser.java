package com.zsy.member.vo;

import lombok.Data;

/**
 * @Description: 社交用户信息
 * @author zsy
 * @email 594983498@qq.com
 * @createTime: 2021-04-24 11:04
 **/
@Data
public class SocialUser {

    private String access_token;

    private String remind_in;

    private long expires_in;

    private String uid;

    private String isRealName;

}
