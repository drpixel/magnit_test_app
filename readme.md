# magnit_test_app
Реализация тестового задания Магнит

## Тестовое задание

Реализовать два сервиса:

Первый сервис(серв1) должен предоставить api следующего функционала:

* Добавить маршрут; На вход мы передаем массив ид точек маршрута, на выход возвращаем ид маршрута. При добавлении маршрута нужно подсчитать время, которое на него потратится.

* Список маршрутов ( с массивом точек маршрута).

* Отдельный маршрут ( с массивом точек маршрута).

Второй сервис(Серв2) по точкам из маршрута возвращает время, которое нужно затратить на этот маршрут(-1 если не достижимо).

Функциональные требования:

* Предусмотреть, что каждый сервис может использовать свою базу данных.

* Формат api – JSON

* Серв1 могут использовать все, Серв2 только другие сервисы (организовать доступ по ролям).

### Сущности:

Маршрут:

```
id – int
is_ready – bool //посчитано ли время на маршрут
time – int // время на маршрут в минутах
```

Точки маршрута:
```
id – int
id_route – int //ид маршрута
```

Граф точек маршрутов: (в файле data.csv данные для этой сущности)
```
id_point_one – int // ид первой точки
id_point_second - int //ид второй точки
time – int // время на преодоления расстояния между двумя точками
```

## Используемые технологии
* Spring Boot (Каркас проекта)
* Spring JPA (Для работы с СУБД)
* Jersey (Для rs-сервисов)
* Guava (Для работы с графом)
* Oracle (СУБД)