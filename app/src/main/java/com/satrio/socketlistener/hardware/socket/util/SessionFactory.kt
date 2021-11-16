package com.satrio.socketlistener.hardware.socket.util

import com.satrio.socketlistener.hardware.socket.handler.Session

interface SessionFactory {
    fun create(): Session
}
