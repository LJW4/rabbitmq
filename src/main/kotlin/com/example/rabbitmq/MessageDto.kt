package com.example.rabbitmq

data class MessageDto(
    val title: String,
    val content: String
) {
    constructor() : this("", "")
}
