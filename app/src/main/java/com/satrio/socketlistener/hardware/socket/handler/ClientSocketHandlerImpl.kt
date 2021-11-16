package com.satrio.socketlistener.hardware.socket.handler

import android.annotation.SuppressLint
import android.util.Log
import com.satrio.socketlistener.hardware.socket.threading.AsyncRunner
import com.satrio.socketlistener.hardware.socket.util.SessionFactory
import java.io.InputStream
import java.io.OutputStream
import java.net.Socket

class ClientSocketHandlerImpl(
    private val socket: Socket,
    private val asyncRunner: AsyncRunner,
    private val SessionFactory: SessionFactory
) : ClientSocketHandler {
    private var outputStream: OutputStream? = null
    private var inputStream: InputStream? = null

    @SuppressLint("LogNotTimber")
    override fun run() {
        try {
            outputStream = socket.getOutputStream()
            inputStream = socket.getInputStream()
            val session = getSession()
            while (!socket.isClosed) {
                session.execute(inputStream!!, outputStream!!)
            }
        } catch (e: Exception) {
            Log.e(this::class.java.name, e::class.java.name)
        } finally {
            close()
        }
    }

    override fun close() {
        outputStream?.close()
        inputStream?.close()
        socket.close()
        asyncRunner.onClosed(this)
    }

    private fun getSession(): Session {
        return SessionFactory.create()
    }
}
