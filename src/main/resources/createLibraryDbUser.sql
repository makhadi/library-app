CREATE USER 'demouser'@'localhost' IDENTIFIED BY 'fremont';



GRANT ALL PRIVILEGES ON library.* TO 'demouser'@'localhost' WITH GRANT OPTION;



SHOW GRANTS FOR 'demouser'@'localhost';