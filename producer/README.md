# metrics

Producer Service:

Создан микросервис который при Post запросе ->
собираеть метрики работы приложения
и отправлять их в Kafka топик "metrics-app-topic".
UI работает на http://localhost:8090/ui/
 
Пример запроса:
 ```shell
curl -i -X POST http://localhost:8080/metrics
```

Запустить все через докер

```shell
docker-compose up
```