use user_db;

CREATE TABLE t_user
(
    id       bigint(20)   NOT NULL COMMENT '用户id',
    username varchar(64)  NOT NULL,
    password varchar(64)  NOT NULL,
    fullname varchar(255) NOT NULL COMMENT '用户姓名',
    mobile   varchar(11) DEFAULT NULL COMMENT '手机号',
    PRIMARY KEY (id) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE t_role
(
    id          varchar(32) NOT NULL,
    role_name   varchar(255) DEFAULT NULL,
    description varchar(255) DEFAULT NULL,
    create_time datetime     DEFAULT NULL,
    update_time datetime     DEFAULT NULL,
    status      char(1)     NOT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY unique_role_name (role_name)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE t_user_role
(
    user_id     varchar(32) NOT NULL,
    role_id     varchar(32) NOT NULL,
    create_time datetime     DEFAULT NULL,
    creator     varchar(255) DEFAULT NULL,
    PRIMARY KEY (user_id, role_id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE t_permission
(
    id          varchar(32) NOT NULL,
    code        varchar(32) NOT NULL COMMENT '权限标识符',
    description varchar(64)  DEFAULT NULL COMMENT '描述',
    url         varchar(128) DEFAULT NULL COMMENT '请求地址',
    PRIMARY KEY (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE t_role_permission
(
    role_id       varchar(32) NOT NULL,
    permission_id varchar(32) NOT NULL,
    PRIMARY KEY (role_id, permission_id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE oauth_client_details
(
    client_id               varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '客户端标 识',
    resource_ids            varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL     DEFAULT NULL COMMENT '接入资源列表',
    client_secret           varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL     DEFAULT NULL COMMENT '客户端秘钥',
    scope                   varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL     DEFAULT NULL,
    authorized_grant_types  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL     DEFAULT NULL,
    web_server_redirect_uri varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL     DEFAULT NULL,
    authorities             varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL     DEFAULT NULL,
    access_token_validity   int(11)                                                 NULL     DEFAULT NULL,
    refresh_token_validity  int(11)                                                 NULL     DEFAULT NULL,
    additional_information  longtext CHARACTER SET utf8 COLLATE utf8_general_ci     NULL,
    create_time             timestamp(0)                                            NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
    archived                tinyint(4)                                              NULL     DEFAULT NULL,
    trusted                 tinyint(4)                                              NULL     DEFAULT NULL,
    autoapprove             varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL     DEFAULT NULL,
    PRIMARY KEY (client_id) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci COMMENT = '接入客户端信息'
  ROW_FORMAT = Dynamic;

CREATE TABLE oauth_code
(
    create_time    timestamp(0)                                            NOT NULL DEFAULT CURRENT_TIMESTAMP,
    code           varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL     DEFAULT NULL,
    authentication blob                                                    NULL,
    INDEX code_index (code) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = Compact;

create table oauth_access_token (
    token_id VARCHAR(255),
    token BLOB,
    authentication_id VARCHAR(255) PRIMARY KEY,
    user_name VARCHAR(255),
    client_id VARCHAR(255),
    authentication BLOB,
    refresh_token VARCHAR(255)
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = Compact;

create table oauth_refresh_token (
    token_id VARCHAR(255),
    token BLOB,
    authentication BLOB
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = Compact;