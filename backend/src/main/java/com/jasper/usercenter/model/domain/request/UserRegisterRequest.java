package com.jasper.usercenter.model.domain.request;

import lombok.Data;

import java.io.Serializable;

/**
 * User register request
 *
 * @author Jie Liu
 */
@Data
public class UserRegisterRequest implements Serializable {
    private static final long serialVersionUID = 984863666521169856L;

    private String userAccount;

    private String userPassword;

    private String checkPassword;

    private String userCode;

}
