package com.satrio.socketlistener.hardware.socket.handler

import java.net.Socket

interface ClientExecutor {
    fun onClientRequest(socket: Socket)
    fun stopAll()
}
