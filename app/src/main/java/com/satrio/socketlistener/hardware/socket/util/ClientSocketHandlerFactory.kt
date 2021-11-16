package com.satrio.socketlistener.hardware.socket.util

import com.satrio.socketlistener.hardware.socket.handler.ClientSocketHandler
import com.satrio.socketlistener.hardware.socket.threading.AsyncRunner
import java.net.Socket

interface ClientSocketHandlerFactory {
    fun create(
        socket: Socket,
        asyncRunner: AsyncRunner,
        SessionFactory: SessionFactory
    ): ClientSocketHandler
}
