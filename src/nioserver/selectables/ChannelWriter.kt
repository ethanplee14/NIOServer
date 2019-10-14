package nioserver.selectables

import nioserver.selectables.abstr.Writable
import java.nio.ByteBuffer
import java.nio.channels.SocketChannel

class ChannelWriter : Writable() {

    override fun write(channel: SocketChannel, msg: String) {
        if(msg.isEmpty()) return

        val buffer = ByteBuffer.wrap(msg.toByteArray())
        channel.write(buffer)
    }
}