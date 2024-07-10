package com.jasper.usercenter.service;

import com.jasper.usercenter.model.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * User Service Test
 * @author Jie Liu
 */
@SpringBootTest
class UserServiceTest {

    @Resource
    private UserService userService;

    @Test
    void testAddUser() {
        User user = new User();
        user.setId(0L);
        user.setUsername("jasper");
        user.setUserAccount("123");
        user.setAvatarUrl("");
        user.setGender(0);
        user.setUserPassword("password");
        user.setPhone("123");
        user.setEmail("456");

        boolean result = userService.save(user);
        System.out.println(user.getId());
        Assertions.assertTrue(result);
    }

    @Test
    void userRegister() {
        String userAccount = "jasper";
        String password = "";
        String checkPassword = "123456789";
        String userCode = "123";
        long result = userService.userRegister(userAccount, password, checkPassword, userCode);
        Assertions.assertEquals(-1, result);

        password = "123456789";
        userAccount = "ja";
        result = userService.userRegister(userAccount, password, checkPassword, userCode);
        Assertions.assertEquals(-1, result);


        password = "123456789";
        userAccount = "jas per";
        result = userService.userRegister(userAccount, password, checkPassword, userCode);
        Assertions.assertEquals(-1, result);

        userAccount = "jasper";
        checkPassword = "123456789";
        result = userService.userRegister(userAccount, password, checkPassword, userCode);
        Assertions.assertEquals(-1, result);

        result = userService.userRegister(userAccount, password, checkPassword, userCode);
        Assertions.assertEquals(-1, result);

        userAccount = "jasper";
        result = userService.userRegister(userAccount, password, checkPassword, userCode);
        Assertions.assertTrue(result < 0);
    }
}