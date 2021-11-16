package com.satrio.socketlistener.hardware.socket.handler

import java.io.InputStream
import java.io.OutputStream

interface Session {
    fun execute(inputStream: InputStream, outputStream: OutputStream)
}
