package nioserver.selectables

import nioserver.selectables.abstr.Readable
import java.lang.StringBuilder
import java.nio.ByteBuffer
import java.nio.channels.SocketChannel
import java.nio.charset.StandardCharsets
import java.util.*

class ChannelReader : Readable() {

    val KB = 1048
    private val nullChar = "\u0000"

    override fun read(channel: SocketChannel): String {
        val buffer = ByteBuffer.allocate(KB)
        channel.read(buffer)
        buffer.clear()
        return StandardCharsets.UTF_8.decode(buffer).toString().replace(nullChar, "")
    }
}