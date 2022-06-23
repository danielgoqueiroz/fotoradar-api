# FotoRadar
## Sobre
FotoRadar é um sistema para gerenciamento de uso de imagens e acompanhamento de cobrnaça pelo uso. 
Também, foi minha primeira ideia de projeto de desenvolvimento e que me inspirou a querer aprender a programar, quando ainda era reporter fotográfico.
Ele já passou por alguns reconstruções e modificações, que acompanham a minha própria evolução como programador.

## Funcionalidades

### Imagem
[x]-Cadastrar
[x]-Visualizar várias
[x]-Editar
[x]-Remover

### Notificação
[x]-Cadastrar informando imagem e link do uso 
    [x]-Cadastra empresa, caso não tenha sido cadastrada ainda
[x]-Visualizar várias
[x]-Editar
    [x]-Adicionar imagem
[x]-Remover

### Pagamento
[x]-Adicionar
[x]-Visualizar extrato: valores e total
[ ]-Remover pagamento
[ ]-Editar pagamento

### Empresa
[x]-Cadastrar (Via cadastro de notificação)
[x]-Visualizar várias
[x]-Editar
[x]-Remover


#GetStart em DEV

Com docker (https://www.docker.com/)(Windows)
Rodar
```
docker run --name mysql-fotoradar -e MYSQL_ROOT_PASSWORD=pass -p 3306:3306 -d docker.io/library/mariadb:10.3
Ver nome de imagem com:
docker ps
```

Abrir cmd(Windows) e rodar o comando abaixo para configurar o banco local: 
```
docker exec -it 'docker-name'  mysql -uroot -p;
CREATE USER 'root'@'172.0.0.1' IDENTIFIED BY 'root';
CREATE USER 'test'@'172.17.0.1' IDENTIFIED BY 'password';
GRANT ALL PRIVILEGES ON *.* TO 'test'@'172.17.0.1' WITH GRANT OPTION;
flush privileges;
exit;
```
Criar o banco de dados
```
create database danie648_fotoradar_dev;
```

test
127.0.0.1
password

