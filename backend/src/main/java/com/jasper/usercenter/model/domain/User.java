package com.jasper.usercenter.model.domain;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 *
 * @TableName user
 */
@TableName(value ="user")
@Data
public class User implements Serializable {
    /**
     * user_id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * user_login_account
     */
    private String userAccount;

    /**
     * user_name
     */
    private String username;

    /**
     * user_avatar
     */
    private String avatarUrl;

    /**
     * user_gender
     */
    private Integer gender;

    /**
     * user_password
     */
    private String userPassword;

    /**
     * user_phone_number
     */
    private String phone;

    /**
     * user_email_address
     */
    private String email;

    /**
     * user_status
     */
    private Integer userStatus;

    /**
     * user_craete_time
     */
    private Date createTime;

    /**
     * user_update_time
     */
    private Date updateTime;

    /**
     * if_user_is_deleted
     */
    @TableLogic
    private Integer isDelete;

    /**
     * user role: 0: normal user, 1: admin
     */
    private Integer userRole;

    /**
     * User's unique code
     */
    private String userCode;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}