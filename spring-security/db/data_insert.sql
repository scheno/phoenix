insert into t_user(id,username,password,fullname,mobile) values ('1', 'zhangsan','','张三',null);

insert into t_role(id,role_name,description,create_time,update_time,status) values ('1','管理员',NULL,NULL,NULL,'');

insert into t_permission(id,code,description,url) values ('1','p1','测试资源 1','/r/r1'),('2','p3','测试资源2','/r/r2');

insert into t_role_permission(role_id,permission_id) values ('1','1'),('1','2');