# Запуск Приложения

## Требования

- Java 17
- Docker

## Запуск

1. Скачайте репозиторий

2. Перейдите в директорию проекта:

    ```bash
    cd <название папки проекта>
    ```

3. Перейдите в файл docker-compose.yml:

   Введите свои данные для доступа к базе данных

4 Проект мультимодульный, нужно собрать модули отдельно:

   С рут папки перейти в папку entity (cd entity) и выполнить (mvn install);
   после этого перейти обратно в корневую (cd ..) и выполнить тоже самое с другим модулем;
   Дождаться пока пройдут все тесты и соберется jar и war образ;

5. Соберите и запустите проект с помощью Docker Compose:

    ```bash
    docker-compose up --build
    ```

   Это создаст контейнеры для Spring Boot и MySQL, соберет приложение и запустит его.

6. После успешного запуска вы сможете обращаться к приложению Spring Boot по адресу:

    ```http
    http://localhost:8080
    ```
7 Для теста можно использовать  http://localhost:8080/swagger-ui или postman 
Аунтификация через Bearer {token}

## Остановка

Чтобы остановить приложение и контейнеры Docker, выполните:

```bash
docker-compose down
