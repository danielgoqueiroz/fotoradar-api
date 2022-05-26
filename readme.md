
TODO
[x]-Cadastrar imagem
[x]-Cadastrar 
Rest
https://spring.io/guides/tutorials/rest/

Security
https://www.youtube.com/watch?v=VVn9OG9nfH0

#Get start DEV

Abrir app Docker (Windows)

Rodar docker:
docker run --name mysql-fotoradar -e MYSQL_ROOT_PASSWORD=pass -p 3306:3306 -d docker.io/library/mariadb:10.3
Ver nome de imagem com:
docker ps

Executar na imagem:
docker exec -it 'docker-name'  mysql -uroot -p;
CREATE USER 'root'@'172.0.0.1' IDENTIFIED BY 'root';
CREATE USER 'test'@'172.17.0.1' IDENTIFIED BY 'password';
GRANT ALL PRIVILEGES ON *.* TO 'test'@'172.17.0.1' WITH GRANT OPTION;
flush privileges;
exit;

create database danie648_fotoradar_dev;