package nioserver.builders

import java.net.InetSocketAddress
import java.nio.channels.SelectionKey
import java.nio.channels.Selector
import java.nio.channels.ServerSocketChannel
import java.util.function.Supplier

class NIOServerChannel(private val port: Int,
                       private val sel: Selector): Supplier<ServerSocketChannel> {

    override fun get(): ServerSocketChannel {
        val serverChannel = ServerSocketChannel.open()
        serverChannel.configureBlocking(false)
        serverChannel.bind(InetSocketAddress(port))
        serverChannel.register(sel, SelectionKey.OP_ACCEPT)
        return serverChannel
    }
}