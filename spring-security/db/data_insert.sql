use user_db;

INSERT INTO user_db.t_permission (id, code, description, url) VALUES ('1', 'p1', '订单查询', '/order');
INSERT INTO user_db.t_permission (id, code, description, url) VALUES ('2', 'p2', '薪资查询', '/salary');
INSERT INTO user_db.t_permission (id, code, description, url) VALUES ('3', 'p3', '货物查询', '/goods');
INSERT INTO user_db.t_permission (id, code, description, url) VALUES ('4', 'p4', '报表查询', '/report');

INSERT INTO user_db.t_role (id, role_name, description, create_time, update_time, status) VALUES ('1', 'admin', null, null, null, '1');
INSERT INTO user_db.t_role (id, role_name, description, create_time, update_time, status) VALUES ('2', 'employee', null, null, null, '1');

INSERT INTO user_db.t_user (id, username, password, fullname, mobile) VALUES (1, 'zhangsan', '$2a$10$Pc9jRtRE6vO0k42JfH78IO/UchL3W6.3YUGOx8tXMJO/aP5fUR0X2', '张三', null);
INSERT INTO user_db.t_user (id, username, password, fullname, mobile) VALUES (2, 'lisi', '$2a$10$Pc9jRtRE6vO0k42JfH78IO/UchL3W6.3YUGOx8tXMJO/aP5fUR0X2', '李四', null);

INSERT INTO user_db.t_user_role (user_id, role_id, create_time, creator) VALUES ('1', '1', null, null);
INSERT INTO user_db.t_user_role (user_id, role_id, create_time, creator) VALUES ('2', '2', null, null);

INSERT INTO user_db.t_role_permission (role_id, permission_id) VALUES ('1', '2');
INSERT INTO user_db.t_role_permission (role_id, permission_id) VALUES ('1', '3');
INSERT INTO user_db.t_role_permission (role_id, permission_id) VALUES ('1', '4');
INSERT INTO user_db.t_role_permission (role_id, permission_id) VALUES ('2', '1');
INSERT INTO user_db.t_role_permission (role_id, permission_id) VALUES ('2', '4');

INSERT INTO oauth_client_details
VALUES ('c1', 'res1', '$2a$10$NlBC84MVb7F95EXYTXwLneXgCca6/GipyWR5NHm8K0203bSQMLpvm', 'ROLE_ADMIN,ROLE_USER,ROLE_API',
        'client_credentials,password,authorization_code,implicit,refresh_token', 'http://www.baidu.com', NULL, 7200,
        259200, NULL, '2019‐09‐09 16:04:28', 0, 0, 'false');
INSERT INTO oauth_client_details
VALUES ('c2', 'res2', '$2a$10$NlBC84MVb7F95EXYTXwLneXgCca6/GipyWR5NHm8K0203bSQMLpvm', 'ROLE_API',
        'client_credentials,password,authorization_code,implicit,refresh_token', 'http://www.baidu.com', NULL, 31536000,
        2592000, NULL, '2019‐09‐09 21:48:51', 0, 0, 'false');