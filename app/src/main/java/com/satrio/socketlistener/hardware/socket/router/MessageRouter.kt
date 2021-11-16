package com.satrio.socketlistener.hardware.socket.router

interface MessageRouter {
    fun processRequestMessage(request: String): String
}
