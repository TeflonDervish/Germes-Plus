drop database germesplus;
create  database germesplus;

create user 'server_manager'@'%' identified by 'server_manager';
GRANT ALL PRIVILEGES ON *.* TO 'server_manager'@'%' WITH GRANT OPTION;

create user 'server_fabric'@'%' identified by 'server_fabric';
GRANT ALL PRIVILEGES ON *.* TO 'server_fabric'@'%' WITH GRANT OPTION;