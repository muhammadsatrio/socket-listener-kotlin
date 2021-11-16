package com.satrio.socketlistener.hardware.socket.util

import com.satrio.socketlistener.hardware.socket.runnable.ServiceRunnable

interface RunnableFactory {
    fun create(): ServiceRunnable
}
