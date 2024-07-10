package com.jasper.usercenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jasper.usercenter.Exception.BusinessException;
import com.jasper.usercenter.common.ErrorCode;
import com.jasper.usercenter.model.domain.User;
import com.jasper.usercenter.service.UserService;
import com.jasper.usercenter.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.jasper.usercenter.constant.UserConstant.USER_LOGIN_STATUS;

/**
* @author Jie Liu
* @description Database operation service for the table 【user】
* @createDate 2024-05-25 12:00:04
*/
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

    @Resource
    private UserMapper userMapper;



    private static final String SALT = "jasper";




    @Override
    public long userRegister(String userAccount, String userPassword, String checkPassword, String userCode) {
        // Verify
        if (StringUtils.isAnyBlank(userAccount, userPassword, checkPassword, userCode)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "Parameter cannot be empty");
        }

        if (userAccount.length() < 4) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "The username must be at least 4 characters long");
        }

        if (userPassword.length() < 8 || checkPassword.length() < 8) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "The password must be at least 8 characters long");
        }

        if (userCode.length() > 5 || userCode.length() < 2) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "The user code must be between 2 and 5 characters long");
        }

        // Special characters are not allowed
        String validPattern = "^[a-zA-Z][a-zA-Z0-9_]{2,19}$";
        Matcher matcher = Pattern.compile(validPattern).matcher(userAccount);
        if (!matcher.find()) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "The username must start with a letter and contain only letters, numbers, and underscores");
        }

        if (!userPassword.equals(checkPassword)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "The two passwords do not match");
        }

        // Repeated user account is not allowed
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userAccount", userAccount);
        long count = userMapper.selectCount(queryWrapper);
        if (count > 0) {
            return -1;
        }

        // Repeated user code is not allowed
        queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userCode", userCode);
        count = userMapper.selectCount(queryWrapper);
        if (count > 0) {
            return -1;
        }

        // 2. Encrypt password
        String encryptedPassword = DigestUtils.md5DigestAsHex((SALT + userPassword).getBytes());

        // 3. insert data
        User user = new User();
        user.setUserAccount(userAccount);
        user.setUserPassword(encryptedPassword);
        boolean saveResult = this.save(user);
        if (!saveResult) {
            return -1;
        }

        return user.getId();
    }

    @Override
    public User userLogin(String userAccount, String userPassword, HttpServletRequest request) {
        // Verify
        if (StringUtils.isAnyBlank(userAccount, userPassword)) {
            return null;
        }

        if (userAccount.length() < 4) {
            return null;
        }

        if (userPassword.length() < 8) {
            return null;
        }

        // Special characters are not allowed
        String validPattern = "^[a-zA-Z][a-zA-Z0-9_]{2,19}$";
        Matcher matcher = Pattern.compile(validPattern).matcher(userAccount);
        if (!matcher.find()) {
            return null;
        }

        // 2. Encrypt password
        String encryptedPassword = DigestUtils.md5DigestAsHex((SALT + userPassword).getBytes());
        // Search if the user exists in the database
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userAccount", userAccount);
        queryWrapper.eq("userPassword", encryptedPassword);
        User user = userMapper.selectOne(queryWrapper);
        if (user == null) {
            log.info("user login failed, userAccount={}, userPassword={}", userAccount, userPassword);
            return null;
        }

        // 3. Prevent data leak
        User safeUser = getSafetyUser(user);

        // 4. record user's login status
        request.getSession().setAttribute(USER_LOGIN_STATUS, safeUser);

        return safeUser;
    }


    @Override
    public User getSafetyUser(User originalUser) {
        if (originalUser == null) {
            return null;
        }
        User safeUser = new User();
        safeUser.setId(originalUser.getId());
        safeUser.setUsername(originalUser.getUsername());
        safeUser.setUserAccount(originalUser.getUserAccount());
        safeUser.setAvatarUrl(originalUser.getAvatarUrl());
        safeUser.setGender(originalUser.getGender());
        safeUser.setPhone(originalUser.getPhone());
        safeUser.setEmail(originalUser.getEmail());
        safeUser.setUserRole(originalUser.getUserRole());
        safeUser.setUserStatus(originalUser.getUserStatus());
        safeUser.setCreateTime(originalUser.getCreateTime());
        safeUser.setUserCode(originalUser.getUserCode());
        return safeUser;
    }

    @Override
    public int userLogout(HttpServletRequest request) {
        request.getSession().removeAttribute(USER_LOGIN_STATUS);
        return 1;
    }
}



