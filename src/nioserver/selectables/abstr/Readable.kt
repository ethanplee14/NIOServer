package nioserver.selectables.abstr

import java.nio.channels.SelectionKey
import java.nio.channels.SocketChannel

abstract class Readable() : Selectable {

    abstract fun read(channel: SocketChannel): String

    override fun condition(key: SelectionKey): Boolean = key.isReadable

    override fun accept(key: SelectionKey) {
        val channel = key.channel()
        if (channel is SocketChannel) {
            val req = read(channel)

            if (req.isNotEmpty())
                channel.register(key.selector(), SelectionKey.OP_WRITE, req)
        }
    }
}