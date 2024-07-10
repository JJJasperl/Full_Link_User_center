-- auto-generated definition
create table user
(
    id           bigint auto_increment comment 'user_id'
        primary key,
    userAccount  varchar(256)                       null comment 'user_login_account',
    username     varchar(256)                       null comment 'user_name',
    avatarUrl    varchar(256)                       null comment 'user_avatar',
    gender       tinyint                            null comment 'user_gender',
    userPassword varchar(512)                       not null comment 'user_password',
    phone        varchar(128)                       null comment 'user_phone_number',
    email        varchar(512)                       null comment 'user_email_address',
    userStatus   int      default 0                 not null comment 'user_status',
    createTime   datetime default CURRENT_TIMESTAMP null comment 'user_craete_time',
    updateTime   datetime default CURRENT_TIMESTAMP null comment 'user_update_time',
    isDelete     tinyint  default 0                 not null comment 'if_user_is_deleted',
    userRole     int      default 0                 null comment 'user role: 0: normal user, 1: admin',
    userCode     varchar(512)                       null
);

