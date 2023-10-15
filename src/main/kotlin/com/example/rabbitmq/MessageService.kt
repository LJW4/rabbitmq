package com.example.rabbitmq

import org.slf4j.LoggerFactory
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class MessageService(
    @Value("\${rabbitmq.exchange-name}")
    val exchangeName: String,

    @Value("\${rabbitmq.routing-key}")
    val routingKey: String,

    private val rabbitTemplate: RabbitTemplate
) {
    private val log = LoggerFactory.getLogger(this.javaClass)!!

    fun sendMessage(messageDto: MessageDto) {
        rabbitTemplate.convertAndSend(exchangeName, routingKey, messageDto)
    }

    @RabbitListener(queues = ["\${rabbitmq.queue-name}"])
    fun receiveMessage(messageDto: MessageDto) {
        log.info("message title = {}", messageDto.title)
        log.info("message content = {}", messageDto.content)
    }
}