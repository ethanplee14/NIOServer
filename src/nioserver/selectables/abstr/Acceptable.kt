package nioserver.selectables.abstr

import java.nio.channels.SelectionKey
import java.nio.channels.SocketChannel

abstract class Acceptable: Selectable {

    abstract fun acceptClient(key: SelectionKey): SocketChannel?

    override fun condition(key: SelectionKey): Boolean = key.isAcceptable

    override fun accept(key: SelectionKey) {
        key.channel().let {
            val client = acceptClient(key)
            client?.register(key.selector(), SelectionKey.OP_READ)
        }
    }
}