# rabbitmq
## config
queue = sample.queue
<br/>exchange = sample.exchange
<br/>routingKey = sample.key
## process
메시지 발행
```
POST http://localhost:8080/send/message
{
    "title": "rabbitmq title",
    "content": "rabbitmq content"
}
```
