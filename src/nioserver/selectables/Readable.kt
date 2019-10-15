package nioserver.selectables

import java.nio.channels.SelectionKey
import java.nio.channels.SocketChannel

class Readable(private val read: (SocketChannel) -> String) : Selectable {

    override fun condition(key: SelectionKey): Boolean = key.isReadable

    override fun accept(key: SelectionKey) {
        val channel = key.channel()
        if (channel is SocketChannel) {
            val res = read(channel)

            if (res.isNotEmpty())
                channel.register(key.selector(), SelectionKey.OP_WRITE, res)
        }
    }
}