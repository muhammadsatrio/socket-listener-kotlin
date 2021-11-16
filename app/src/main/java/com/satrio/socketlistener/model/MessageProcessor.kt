package com.satrio.socketlistener.model

interface MessageProcessor {
    fun processRequestMessage(request: SocketRequest): SocketResponse
    fun parseMessage(requestMessage: String): SocketRequest
    fun packResponse(SocketResponse: SocketResponse): String
}
