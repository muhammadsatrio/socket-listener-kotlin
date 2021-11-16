package com.satrio.socketlistener.hardware.socket.runnable

import com.satrio.socketlistener.hardware.socket.handler.ClientExecutor
import java.net.InetSocketAddress
import java.net.ServerSocket

class EcrRunnableImpl : ServiceRunnable {
    private lateinit var serverSocket: ServerSocket
    private lateinit var clientExecutor: ClientExecutor
    private var port: Int = 0
    private var timeout: Int = 0

    override fun run() {
        if (port == 0) {
            throw RuntimeException("port must be not 0")
        }
        serverSocket.bind(InetSocketAddress(port))

        do {
            try {
                val socket = serverSocket.accept()
                if (timeout > 0) {
                    socket.soTimeout = timeout
                }
                clientExecutor.onClientRequest(socket)
            } catch (e: Exception) {

            }
        } while (!serverSocket.isClosed)
    }

    override fun init(
        serverSocket: ServerSocket,
        port: Int,
        timeout: Int,
        clientExecutor: ClientExecutor
    ) {
        this.serverSocket = serverSocket
        this.port = port
        this.timeout = timeout
        this.clientExecutor = clientExecutor
    }
}
