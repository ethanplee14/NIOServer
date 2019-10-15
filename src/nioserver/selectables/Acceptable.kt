package nioserver.selectables

import java.nio.channels.SelectionKey
import java.nio.channels.SocketChannel

class Acceptable(private val acceptor: (SelectionKey) -> SocketChannel): Selectable {

    override fun condition(key: SelectionKey): Boolean = key.isAcceptable

    override fun accept(key: SelectionKey) {
        key.channel().let {
            val client = acceptor(key)
            client.register(key.selector(), SelectionKey.OP_READ)
        }
    }
}