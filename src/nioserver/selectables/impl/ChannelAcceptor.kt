package nioserver.selectables.impl

import nioserver.selectables.Acceptable
import java.nio.channels.SelectionKey
import java.nio.channels.ServerSocketChannel
import java.nio.channels.SocketChannel

class ChannelAcceptor(private val serverChannel: ServerSocketChannel,
                      private val clients: ArrayList<SocketChannel>? = null) : Acceptable() {


    override fun acceptClient(key: SelectionKey): SocketChannel? {
        val client = serverChannel.accept()
        client?.configureBlocking(false)
        client?.let {
            println("Client connected: " + it.remoteAddress)
            clients?.add(it)
        }
        return client
    }
}