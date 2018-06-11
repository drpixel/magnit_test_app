# Тестовое задание
Реализация тестового задания для Магнит ([Просмотр задания](test_task.md))

В качестве IDE для выполнения задания использован NetBeans 8

## Запуск
Для запуска проекта необходимо указать настройки для двух СУБД в файле application.properties, в файле pom.xml изменить зависимость Oracle Driver (ojdbc7.jar) на драйвер для СУБД, которые собираетесь использовать.
Произвести сборку проекта и запуск:

```
$ java -jar target/test_app-0.0.1-SNAPSHOT.jar
```

в режиме отладки:
```
$ java -Xdebug -Xrunjdwp:server=y,transport=dt_socket,address=8000,suspend=n \
       -jar target/test_app-0.0.1-SNAPSHOT.jar
```

При открытии в браузере [http://localhost:8080](http://localhost:8080) отобразится интерфейс визуального тестирования API [Swagger UI](https://swagger.io/)

По адресу [http://localhost:8080/api/swagger.json](http://localhost:8080/api/swagger.json) находится описание сервисов в формате JSON

По адресу [http://localhost:8080/api/application.wadl](http://localhost:8080/api/application.wadl) находится описание сервисов в формате WADL


## Настройки БД
В папке /src/main/resources находятся два файла: schema_objects.sql - схема БД (схема в БД создастся автоматически), import_graph.sql - точки для добавления в схему (надо выполнять самостоятельно).

Для изменения настроек создания схемы необходимо поменять параметр в файле application.properties:

```
spring.jpa.hibernate.ddl-auto=update
```


## Используемые технологии
* Spring Boot (Каркас проекта)
* Spring JPA (Для работы с СУБД)
* Jersey (Для rs-сервисов)
* Swagger2, Swagger-UI (Для документирования и тестирования API)
* Guava (Для работы с графом)
* Oracle (СУБД)