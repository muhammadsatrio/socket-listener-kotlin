package com.satrio.socketlistener.hardware.socket.util

import com.satrio.socketlistener.hardware.socket.handler.ClientSocketHandler
import com.satrio.socketlistener.hardware.socket.handler.ClientSocketHandlerImpl
import com.satrio.socketlistener.hardware.socket.threading.AsyncRunner
import java.net.Socket

class ClientSocketHandlerFactoryImpl :
    ClientSocketHandlerFactory {
    override fun create(
        socket: Socket,
        asyncRunner: AsyncRunner,
        SessionFactory: SessionFactory
    ): ClientSocketHandler {
        return ClientSocketHandlerImpl(
            socket,
            asyncRunner,
            SessionFactory
        )
    }
}
