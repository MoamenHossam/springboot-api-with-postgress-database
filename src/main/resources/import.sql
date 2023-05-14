insert into permission_group (id,group_name) values (nextval('permission_group_sequence'),'admin');
insert into permission_group (id,group_name) values (nextval('permission_group_sequence'),'developer');
insert into permission_group (id,group_name) values (nextval('permission_group_sequence'),'tester');
insert into permission (id,permission_level,user_email,group_id) values (nextval('permission_sequence'),'VIEW','john@gmail.com',1);
insert into permission (id,permission_level,user_email,group_id) values (nextval('permission_sequence'),'EDIT','mary@gmail.com',1);
insert into permission (id,permission_level,user_email,group_id) values (nextval('permission_sequence'),'EDIT','rod@gmail.com',2);
insert into item (id,name, parent_id, permission_group_id, type) values (nextval('item_sequence'),'Admin-Space', null, 1, 'SPACE');
insert into item (id,name, parent_id, permission_group_id, type) values (nextval('item_sequence'),'Developer-Space', null, 2, 'SPACE');
insert into item (id,name, parent_id, permission_group_id, type) values (nextval('item_sequence'),'Admin-Folder-1', 1, 1, 'FOLDER');