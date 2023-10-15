package com.example.rabbitmq

import org.slf4j.LoggerFactory
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class MessageController(
    private val messageService: MessageService
) {
    @PostMapping("/send/message")
    fun sendMessage(@RequestBody messageDto: MessageDto): ResponseEntity<String> {
        messageService.sendMessage(messageDto)
        return ResponseEntity.ok("success")
    }
}