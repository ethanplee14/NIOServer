package nioserver.selectables

import nioserver.selectables.abstr.Readable
import java.nio.ByteBuffer
import java.nio.channels.SocketChannel

class ChannelReader : Readable() {

    val KB = 1048

    override fun read(channel: SocketChannel): String {
        val buffer = ByteBuffer.allocate(KB)
        channel.read(buffer)
        return String(buffer.array())
    }
}