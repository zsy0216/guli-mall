package com.zsy.member.exception;

/**
 * @Description:
 * @author zsy
 * @email 594983498@qq.com
 * @createTime: 2020-06-27 16:04
 **/
public class UsernameException extends RuntimeException {
    public UsernameException() {
        super("存在相同的用户名");
    }
}
