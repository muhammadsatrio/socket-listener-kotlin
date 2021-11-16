package com.satrio.socketlistener.hardware.socket.handler

interface RequestHandler {
    fun execute(requestMessage: String): String
}

