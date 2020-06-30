CREATE TABLE t_user ( 
    id bigint(20) NOT NULL COMMENT '用户id', 
    username varchar(64) NOT NULL, 
    password varchar(64) NOT NULL, 
    fullname varchar(255) NOT NULL COMMENT '用户姓名', 
    mobile varchar(11) DEFAULT NULL COMMENT '手机号', 
    PRIMARY KEY (id) USING BTREE 
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE t_role (
    id varchar(32) NOT NULL,
    role_name varchar(255) DEFAULT NULL,
    description varchar(255) DEFAULT NULL,
    create_time datetime DEFAULT NULL,
    update_time datetime DEFAULT NULL,
    status char(1) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY unique_role_name (role_name)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE t_permission (
    id varchar(32) NOT NULL,
    code varchar(32) NOT NULL COMMENT '权限标识符',
    description varchar(64) DEFAULT NULL COMMENT '描述',
    url varchar(128) DEFAULT NULL COMMENT '请求地址',
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE t_role_permission (
    role_id varchar(32) NOT NULL,
    permission_id varchar(32) NOT NULL, PRIMARY KEY (role_id,permission_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;