package com.satrio.socketlistener.hardware.socket.handler

import java.io.InputStream
import java.io.OutputStream
import java.net.SocketException

class SessionImpl(
    private val requestHandler: RequestHandler
) : Session {

    override fun execute(inputStream: InputStream, outputStream: OutputStream) {
        try {
            val requestBuffByte = ByteArray(BUFF_SIZE_INPUT_STREAM)
            val receivedSize = inputStream.read(
                requestBuffByte,
                0,
                BUFF_SIZE_INPUT_STREAM
            )
            if (receivedSize == INPUT_STREAM_READ_RETURN_IF_DATA_NOT_AVAILABLE) {
                throw SocketException("Session Shutdown")
            }

            sendResponse(
                outputStream,
                requestHandler.execute(
                    convertToHexString(requestBuffByte, receivedSize)
                )
            )
        } catch (e: Exception) {
            inputStream.close()
            outputStream.close()
            throw SocketException("Session Shutdown")
        }
    }

    private fun convertToHexString(requestBuffByte: ByteArray, requestSize: Int): String {
        return requestBuffByte.sliceArray(IntRange(0, requestSize - 1)).toHexString().toUpperCase()
    }

    private fun sendResponse(outputStream: OutputStream, responseHexString: String) {
        outputStream.write(responseHexString.hexString2Bytes())
        outputStream.flush()
    }

    private fun ByteArray.toHexString() = this.joinToString("") { "%02x".format(it) }

    private fun String.hexString2Bytes(): ByteArray {
        var data = this
        val result = ByteArray((data.length + 1) / 2)
        if (data.length and 0x1 == 1) {
            data = "0$data"
        }
        for (i in result.indices) {
            result[i] = (hex2byte(data[i * 2 + 1]) or (hex2byte(data[i * 2]) shl 4)).toByte()
        }
        return result
    }

    private fun hex2byte(hex: Char): Int {
        if (hex in 'a'..'f') {
            return (hex - 'a' + 10)
        }

        if (hex in 'A'..'F') {
            return (hex - 'A' + 10)
        }

        return if (hex in '0'..'9') {
            (hex - '0')
        } else {
            0
        }
    }

    companion object {
        private const val BUFF_SIZE_INPUT_STREAM = 8192
        private const val INPUT_STREAM_READ_RETURN_IF_DATA_NOT_AVAILABLE = -1
    }
}
