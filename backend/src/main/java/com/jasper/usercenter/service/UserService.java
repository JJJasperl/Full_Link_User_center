package com.jasper.usercenter.service;

import com.jasper.usercenter.model.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;

/**
* @author Jie Liu
* @description Database operation service for the table 【user】
* @createDate 2024-05-25 12:00:04
*/
public interface UserService extends IService<User> {



    /**
     * Register the user
     *
     * @param userAccount   username
     * @param userPassword  password
     * @param checkPassword verification password
     * @param userCode  userCode verification code
     * @return user id
     */
    long userRegister(String userAccount, String userPassword, String checkPassword, String userCode);

    /**
     * @param userAccount  username
     * @param userPassword password
     * @param request
     * @return user
     */
    User userLogin(String userAccount, String userPassword, HttpServletRequest request);


    /**
     * Get the user information based on the user id
     * @param originalUser
     * @return
     */
    User getSafetyUser(User originalUser);


    /**
     * User logout
     * @param request
     */
    int userLogout(HttpServletRequest request);
}
