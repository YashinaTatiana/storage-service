# Storage-service 

## Запуск приложения:

```
cd storage-service
docker-compose up
./gradlew bootRun --args=--app.input.link=type:path
```

- ```type``` - тип ссылки
- ```path``` - путь к файлу

Ссылка определяет источник, из которого загружаются данные в XML-формате. 

Тип ссылки ```type```:
- ```file``` (внешний файл) 
- ```classpath``` (файл в classpath) 
- ```url``` (URL)

Примеры:
- ```classpath:test.xml```
- ```file:input.xml```
- ```url:file:/input.xml```

### Пример XML файла (test.xml):

```
<?xml version="1.0" encoding="UTF-8"?>
<Storage>
 <Box id="1">
   <Item id="1"/>
   <Item color="red" id="2"/>
   <Box id="3">
       <Item id="3" color="red" />
       <Item id="4" color="black" />
   </Box>
   <Box id="6"/>
   <Item id="5"/>
 </Box>
 <Item id="6"/>
</Storage>
```



## REST сервис 

REST-сервис возвращает id предметов заданного цвета (Color), содержащиеся в ящике c заданным идентификатором (Box)


### Запрос:

```
POST /api/v1/items HTTP/1.1
Host: localhost
Accept: application/json
Content-Type:application/json

{"box":"1","color":"red"}
```

```
curl -i -X POST \
   -H "Content-Type:application/json" \
   -d \
'{"color":"red", "box":1 }' \
 'http://localhost:8080/api/v1/items'
```

### Ответ:
```
HTTP/1.1 200
Content-Type: application/json;charset=UTF-8
Transfer-Encoding: chunked

[2,3]
```

В случае невалидного запроса (```color``` или ```box``` = null)

```
HTTP/1.1 400
Content-Type: application/json
Transfer-Encoding: chunked

{
    "errors": [
        {
            "message": "must not be null",
            "field": "box"
        },
        {
            "message": "must not be null",
            "field": "color"
        }
    ]
}

```