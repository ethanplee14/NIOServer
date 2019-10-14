package nioserver.selectables

import nioserver.Response
import nioserver.selectables.abstr.Readable
import java.nio.ByteBuffer
import java.nio.channels.SocketChannel
import java.nio.charset.StandardCharsets
import java.util.*
import java.util.function.BiConsumer

class ChannelReader : Readable() {

    var process = BiConsumer<String, Response> {req, res -> Unit}
    val KB = 1048
    private val nullChar = "\u0000"

    override fun read(channel: SocketChannel): String {
        val req = readChannel(channel)
        if (req.isEmpty()) return ""

        val res = Response()
        process.accept(req, res)
        return res.msg
    }

    private fun readChannel(channel: SocketChannel): String {
        val buffer = ByteBuffer.allocate(KB)
        channel.read(buffer)
        buffer.clear()
        return StandardCharsets.UTF_8.decode(buffer).toString().replace(nullChar, "")
    }
}