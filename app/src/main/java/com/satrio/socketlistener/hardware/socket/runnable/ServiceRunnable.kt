package com.satrio.socketlistener.hardware.socket.runnable

import com.satrio.socketlistener.hardware.socket.handler.ClientExecutor
import java.net.ServerSocket

interface ServiceRunnable : Runnable {
    fun init(serverSocket: ServerSocket, port: Int, timeout: Int, clientExecutor: ClientExecutor)
}
