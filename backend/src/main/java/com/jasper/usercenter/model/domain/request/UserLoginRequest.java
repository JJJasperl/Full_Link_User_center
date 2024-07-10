package com.jasper.usercenter.model.domain.request;

import lombok.Data;

import java.io.Serializable;

/**
 * User Login request
 *
 * @author Jie Liu
 */
@Data
public class UserLoginRequest implements Serializable {
    private static final long serialVersionUID = 984863666521169856L;

    private String userAccount;

    private String userPassword;

}
