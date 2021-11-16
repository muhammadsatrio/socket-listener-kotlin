package com.satrio.socketlistener.hardware.socket.handler

import com.satrio.socketlistener.hardware.socket.threading.AsyncRunner
import com.satrio.socketlistener.hardware.socket.util.ClientSocketHandlerFactory
import com.satrio.socketlistener.hardware.socket.util.SessionFactory
import java.net.Socket

class ClientExecutorImpl(
    private val asyncRunner: AsyncRunner,
    private val clientSocketHandlerFactory: ClientSocketHandlerFactory,
    private val SessionFactory: SessionFactory
) : ClientExecutor {
    override fun onClientRequest(socket: Socket) {
        asyncRunner.execute(createClientHandler(socket))
    }

    override fun stopAll() {
        asyncRunner.closeAll()
    }

    private fun createClientHandler(socket: Socket): ClientSocketHandler {
        return clientSocketHandlerFactory
            .create(socket, asyncRunner, SessionFactory)
    }
}
