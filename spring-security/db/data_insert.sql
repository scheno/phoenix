use user_db;

insert into t_user(id,username,password,fullname,mobile) values ('1', 'zhangsan','$2a$10$Pc9jRtRE6vO0k42JfH78IO/UchL3W6.3YUGOx8tXMJO/aP5fUR0X2','张三',null);

insert into t_role(id,role_name,description,create_time,update_time,status) values ('1','管理员',NULL,NULL,NULL,'');

insert into t_user_role(user_id,role_id,create_time,creator) values ('1','1',NULL,NULL);

insert into t_permission(id,code,description,url) values ('1','p1','测试资源 1','/r/r1'),('2','p3','测试资源2','/r/r2');

insert into t_role_permission(role_id,permission_id) values ('1','1'),('1','2');

INSERT INTO oauth_client_details
VALUES ('c1', 'res1', '$2a$10$NlBC84MVb7F95EXYTXwLneXgCca6/GipyWR5NHm8K0203bSQMLpvm', 'ROLE_ADMIN,ROLE_USER,ROLE_API',
        'client_credentials,password,authorization_code,implicit,refresh_token', 'http://www.baidu.com', NULL, 7200,
        259200, NULL, '2019‐09‐09 16:04:28', 0, 0, 'false');
INSERT INTO oauth_client_details
VALUES ('c2', 'res2', '$2a$10$NlBC84MVb7F95EXYTXwLneXgCca6/GipyWR5NHm8K0203bSQMLpvm', 'ROLE_API',
        'client_credentials,password,authorization_code,implicit,refresh_token', 'http://www.baidu.com', NULL, 31536000,
        2592000, NULL, '2019‐09‐09 21:48:51', 0, 0, 'false');

INSERT INTO oauth_client_details
VALUES ('c1', 'res1', '$2a$10$NlBC84MVb7F95EXYTXwLneXgCca6/GipyWR5NHm8K0203bSQMLpvm', 'ROLE_ADMIN,ROLE_USER,ROLE_API',
        'client_credentials,password,authorization_code,implicit,refresh_token', 'http://www.baidu.com', NULL, 7200,
        259200, NULL, NULL, 0, 0, 'false');
INSERT INTO oauth_client_details
VALUES ('c2', 'res2', '$2a$10$NlBC84MVb7F95EXYTXwLneXgCca6/GipyWR5NHm8K0203bSQMLpvm', 'ROLE_API',
        'client_credentials,password,authorization_code,implicit,refresh_token', 'http://www.baidu.com', NULL, 31536000,
        2592000, NULL, NULL, 0, 0, 'false');