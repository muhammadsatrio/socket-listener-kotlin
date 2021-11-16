package com.satrio.socketlistener.hardware.socket.threading

import com.satrio.socketlistener.hardware.socket.handler.ClientSocketHandler

interface AsyncRunner {
    fun closeAll()
    fun onClosed(clientSocketHandler: ClientSocketHandler)
    fun execute(clientSocketHandler: ClientSocketHandler)
}