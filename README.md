# Учебный проект школы Бела
## Инструменты:Spring boot,Liquibase,mapstruct,junit,postgres,
# Для запуска нужно:
1.docker run --name postgres -p 5432:5432 -e POSTGRES_USER=sa -e POSTGRES_PASSWORD=sa -e POSTGRES_DB=postgres -d postgres:13.3
2. Запустить RabbitMQ, это можно сделать в Docker выполнив команду:
docker run -it --rm --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3.10-management или установить на ПК;

3. Запустить Kafka , это можно сделать создав Docker-compose.yml, со следующим содержанием:
https://gist.github.com/sgoshaa/bdc504ee3acdc3682d0837b482633411
