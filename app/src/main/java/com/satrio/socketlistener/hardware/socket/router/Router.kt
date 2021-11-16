package com.satrio.socketlistener.hardware.socket.router

import com.satrio.socketlistener.model.MessageProcessor
import com.satrio.socketlistener.util.Const.Companion.ROUTER_NOT_FOUND_RESPONSE_CODE

class Router(
    private val map: Map<String, Class<Any>>
) : MessageRouter {
    override fun processRequestMessage(request: String): String {
        val code = getCode(request)
        val targetClass = map[code]

        targetClass?.let {
            val classToBuild =
                Class.forName(it.canonicalName)
            val constructor = classToBuild.getConstructor()
            val instance = constructor.newInstance() as MessageProcessor
            val arg = instance.parseMessage(request)
            val response = instance.processRequestMessage(arg)
            return instance.packResponse(response)
        }

        return ROUTER_NOT_FOUND_RESPONSE_CODE
    }

    private fun getCode(request: String): String {
        return request.substring(0, 2)
    }
}
