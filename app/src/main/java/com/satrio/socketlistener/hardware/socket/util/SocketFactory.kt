package com.satrio.socketlistener.hardware.socket.util

import java.net.ServerSocket

interface SocketFactory {
    fun create(): ServerSocket
}
