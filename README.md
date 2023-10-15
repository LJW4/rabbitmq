# rabbitmq
## 설치
rabbitmq.yml
```
version: '3'
services:
  rabbitmq:
    image: 'rabbitmq:3-management-alpine'
    container_name: rabbitmq-stream
    ports:
      - "5672:5672"
      - "15672:15672"
    environment:
      RABBITMQ_ERLANG_COOKIE: "RabbitMQ-My-Cookies"
      RABBITMQ_DEFAULT_USER: "admin"
      RABBITMQ_DEFAULT_PASS: "admin"
```
## 실행
docker compose -f rabbitmq.yml up -d
## config
queue = sample.queue
<br/>exchange = sample.exchange
<br/>routingKey = sample.key
## API
메시지 발행
```
POST http://localhost:8080/send/message
{
    "title": "rabbitmq title",
    "content": "rabbitmq content"
}
```
