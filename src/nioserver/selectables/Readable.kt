package nioserver.selectables

import java.nio.channels.SelectionKey
import java.nio.channels.SocketChannel

abstract class Readable() : Selectable {

    abstract fun read(channel: SocketChannel): String

    override fun condition(key: SelectionKey): Boolean = key.isReadable

    override fun accept(key: SelectionKey) {
        val channel = key.channel()
        if (channel is SocketChannel) {
            val req = read(channel)
            key.attach(req)
            key.channel()?.register(key.selector(), SelectionKey.OP_WRITE)
        }
    }
}