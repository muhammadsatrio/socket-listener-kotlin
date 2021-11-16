package com.satrio.socketlistener.hardware.socket.util

import java.net.ServerSocket

class SocketFactoryImpl : SocketFactory {
    override fun create(): ServerSocket {
        return ServerSocket().apply {
            reuseAddress = true
        }
    }
}
