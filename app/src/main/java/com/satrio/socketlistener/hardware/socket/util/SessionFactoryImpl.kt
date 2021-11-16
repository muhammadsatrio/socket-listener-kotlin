package com.satrio.socketlistener.hardware.socket.util

import com.satrio.socketlistener.hardware.socket.handler.RequestHandlerImpl
import com.satrio.socketlistener.hardware.socket.handler.Session
import com.satrio.socketlistener.hardware.socket.handler.SessionImpl
import com.satrio.socketlistener.hardware.socket.router.MessageRouter

class SessionFactoryImpl(
    private val router: MessageRouter
) :
    SessionFactory {
    override fun create(): Session {
        return SessionImpl(
            RequestHandlerImpl(
                router
            )
        )
    }
}
