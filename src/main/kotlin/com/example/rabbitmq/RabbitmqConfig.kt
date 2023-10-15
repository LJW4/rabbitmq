package com.example.rabbitmq

import org.springframework.amqp.core.Binding
import org.springframework.amqp.core.BindingBuilder
import org.springframework.amqp.core.DirectExchange
import org.springframework.amqp.core.Queue
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.amqp.support.converter.MessageConverter
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RabbitmqConfig(
    @Value("\${spring.rabbitmq.host}")
    val host: String,

    @Value("\${spring.rabbitmq.port}")
    val port: Int,

    @Value("\${spring.rabbitmq.username}")
    val username: String,

    @Value("\${spring.rabbitmq.password}")
    val password: String,

    @Value("\${rabbitmq.queue-name}")
    val queueName: String,

    @Value("\${rabbitmq.exchange-name}")
    val exchangeName: String,

    @Value("\${rabbitmq.routing-key}")
    val routingKey: String
) {
    @Bean
    fun queue(): Queue {
        return Queue(queueName)
    }

    @Bean
    fun exchange(): DirectExchange {
        return DirectExchange(exchangeName)
    }

    @Bean
    fun binding(queue: Queue, exchange: DirectExchange): Binding {
        return BindingBuilder.bind(queue).to(exchange).with(routingKey)
    }

    @Bean
    fun rabbitTemplate(connectionFactory: ConnectionFactory, messageConverter: MessageConverter): RabbitTemplate {
        val rabbitTemplate = RabbitTemplate(connectionFactory)
        rabbitTemplate.messageConverter = Jackson2JsonMessageConverter()
        return rabbitTemplate
    }

    @Bean
    fun connectionFactory(): ConnectionFactory {
        val connectionFactory = CachingConnectionFactory()
        connectionFactory.setHost(host)
        connectionFactory.port = port
        connectionFactory.username = username
        connectionFactory.setPassword(password)
        return connectionFactory
    }

    @Bean
    fun messageConverter(): MessageConverter {
        return Jackson2JsonMessageConverter()
    }
}