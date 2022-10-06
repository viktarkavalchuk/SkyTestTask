@echo off 
set uname=root
set pass=root

echo\
echo ---------------------- Preparing MySQL ----------------------
echo\
echo Creating DataBase "skytecgames"..
mysql -u%uname% -p%pass% -e "CREATE DATABASE IF NOT EXISTS `skytecgames`"
echo Deploying content to the DataBase..
mysql -u%uname% -p%pass% skytecgames < Script.sql
mysqlshow -u%uname% -p%pass% skytecgames

echo Creating DataBase "skytecgamestest"..
mysql -u%uname% -p%pass% -e "CREATE DATABASE IF NOT EXISTS `skytecgamestest`"
echo Deploying content to the DataBase..
mysql -u%uname% -p%pass% skytecgamestest < Script_TEST_BASE.sql
mysqlshow -u%uname% -p%pass% skytecgamestest



