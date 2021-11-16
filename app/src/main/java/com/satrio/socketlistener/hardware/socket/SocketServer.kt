package com.satrio.socketlistener.hardware.socket

import android.annotation.SuppressLint
import android.util.Log
import com.satrio.socketlistener.hardware.socket.handler.ClientExecutor
import com.gojek.offline.payment.sdk.common.service.socket.server.runnable.ServiceRunnable
import com.satrio.socketlistener.hardware.socket.util.RunnableFactory
import com.satrio.socketlistener.hardware.socket.util.SocketFactory
import java.net.ServerSocket

class SocketServer(
    private val serverSocketFactory: SocketFactory,
    private val runnableFactory: RunnableFactory,
    private val defaultClientExecutor: ClientExecutor
) {
    private var thread: Thread? = null
    private var serverSocket: ServerSocket? = null
    private var runnable: ServiceRunnable? = null

    fun startServer() {
        if (checkServerIsActive()) {
            return
        }

        serverSocket = serverSocketFactory.create()
        runnable = runnableFactory.create()

        runnable!!.init(
            serverSocket!!,
            DEFAULT_PORT,
            DEFAULT_TIMEOUT_IN_MILLI_SECOND,
            defaultClientExecutor
        )
        thread = Thread(runnable!!)
        thread?.isDaemon = true
        thread?.name = " Plain Listener"
        thread?.start()
    }

    @SuppressLint("LogNotTimber")
    fun stopServer() {
        try {
            serverSocket?.close()
            serverSocket = null
            runnable = null
            defaultClientExecutor.stopAll()
            thread?.join()
            thread = null
        } catch (e: Exception) {
            Log.e(this::class.java.name, "Could not stop all connections")
        }
    }

    fun checkServerIsActive(): Boolean {
        return thread != null && !(serverSocket?.isClosed ?: true)
    }

    companion object {
        private const val DEFAULT_PORT = 8880
        private const val DEFAULT_TIMEOUT_IN_MILLI_SECOND = 5000
    }
}
