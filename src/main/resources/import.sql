insert into permission_group (id,group_name) values (nextval('permission_group_sequence'),'admin');
insert into permission_group (id,group_name) values (nextval('permission_group_sequence'),'developer');
insert into permission_group (id,group_name) values (nextval('permission_group_sequence'),'tester');
insert into permission (id,permission_level,user_email,group_id) values (nextval('permission_sequence'),'VIEW','john@gmail.com',1);
insert into permission (id,permission_level,user_email,group_id) values (nextval('permission_sequence'),'EDIT','mary@gmail.com',1);
insert into permission (id,permission_level,user_email,group_id) values (nextval('permission_sequence'),'EDIT','rod@gmail.com',2);