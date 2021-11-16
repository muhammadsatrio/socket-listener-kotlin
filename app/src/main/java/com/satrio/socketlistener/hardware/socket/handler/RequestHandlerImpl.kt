package com.satrio.socketlistener.hardware.socket.handler

import com.satrio.socketlistener.hardware.socket.router.MessageRouter

class RequestHandlerImpl(
    private val router: MessageRouter
) :
    RequestHandler {
    override fun execute(requestMessage: String): String {
        return try {
            router.processRequestMessage(requestMessage)
        } catch (e: Exception) {
            e.printStackTrace()
            DEFAULT_ERROR_RESPONSE
        }
    }

    companion object {
        private const val DEFAULT_ERROR_RESPONSE = "error"
    }
}
