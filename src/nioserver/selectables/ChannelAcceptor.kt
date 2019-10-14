package nioserver.selectables

import nioserver.selectables.abstr.Acceptable
import java.nio.channels.SelectionKey
import java.nio.channels.ServerSocketChannel
import java.nio.channels.SocketChannel

class ChannelAcceptor(private val serverChannel: ServerSocketChannel,
                      private val clients: MutableList<SocketChannel>? = null) : Acceptable() {

    override fun acceptClient(key: SelectionKey): SocketChannel? {
        val client = serverChannel.accept()
        client?.configureBlocking(false)
        client?.let {
            println("Client connected: " + it.remoteAddress)
            clients?.add(it)
            println(clients?.size)
        }
        return client
    }
}