package nioserver.selectables

import nioserver.Response
import nioserver.selectables.abstr.Writable
import java.nio.ByteBuffer
import java.nio.channels.SocketChannel
import java.util.function.BiConsumer
import java.util.function.Consumer

class ChannelResponder : Writable() {

    private var middleware = BiConsumer<String, Response>{req, res -> Unit}

    override fun write(channel: SocketChannel, req: String) {
        val res = Response()
        middleware.accept(req, res)
        respond(channel, res)
    }

    fun add(middleware: BiConsumer<String, Response>) {
        this.middleware = this.middleware.andThen { req, res ->
            if (!res.end) middleware.accept(req, res)
        }
    }

    private fun respond(channel: SocketChannel, res: Response) {
        if(res.msg.isEmpty()) return

        val buffer = ByteBuffer.wrap(res.msg.toByteArray())
        buffer.flip()
        channel.write(buffer)
        buffer.clear()
    }
}