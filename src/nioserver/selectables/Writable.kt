package nioserver.selectables

import java.nio.channels.SelectionKey
import java.nio.channels.SocketChannel

class Writable(private val write: (SocketChannel, String) -> Unit) : Selectable {

    override fun condition(key: SelectionKey): Boolean = key.isWritable

    override fun accept(key: SelectionKey) {
        val attachment = key.attachment()
        val channel = key.channel()
        if(attachment is String && channel is SocketChannel)
            write(channel, attachment)
        key.channel().register(key.selector(), SelectionKey.OP_READ)
    }
}