package com.satrio.socketlistener.hardware.socket.threading

import com.satrio.socketlistener.hardware.socket.handler.ClientSocketHandler
import java.util.*

class DefaultAsyncRunner :
    AsyncRunner {
    private var requestCount: Int = 0
    private val running = Collections.synchronizedList(ArrayList<ClientSocketHandler>())

    override fun closeAll() {
        running.forEach {
            it.close()
        }
        running.clear()
    }

    override fun onClosed(clientSocketHandler: ClientSocketHandler) {
        running.remove(clientSocketHandler)
    }

    override fun execute(clientSocketHandler: ClientSocketHandler) {
        ++this.requestCount
        this.running.add(clientSocketHandler)
        createThread(clientSocketHandler).start()
    }

    private fun createThread(clientSocketHandler: ClientSocketHandler): Thread {
        val thread = Thread(clientSocketHandler)
        thread.isDaemon = true
        thread.name = " Server (#$requestCount)"
        return thread
    }
}