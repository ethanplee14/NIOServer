package nioserver.io

import java.nio.ByteBuffer
import java.nio.channels.SocketChannel

class ChannelWriter: (SocketChannel, String) -> Unit {

    override fun invoke(channel: SocketChannel, msg: String) {
        if(msg.isEmpty()) return

        val buffer = ByteBuffer.wrap(msg.toByteArray())
        channel.write(buffer)
    }
}