create user 'server'@'%' identified by 'server';
GRANT ALL PRIVILEGES ON *.* TO 'server'@'%' WITH GRANT OPTION;

create user 'server_manager'@'%' identified by 'server_manager';
GRANT ALL PRIVILEGES ON *.* TO 'server_manager'@'%' WITH GRANT OPTION;

create user 'server_fabric'@'%' identified by 'server';
GRANT ALL PRIVILEGES ON *.* TO 'server_fabric'@'%' WITH GRANT OPTION;

FLUSH PRIVILEGES;