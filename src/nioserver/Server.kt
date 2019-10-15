package nioserver

import nioserver.builders.Middleware
import nioserver.builders.NIOServerChannel
import nioserver.selectables.ChannelAcceptor
import nioserver.selectables.ChannelReader
import nioserver.selectables.ChannelWriter
import java.nio.channels.Selector
import java.nio.channels.SocketChannel
import java.util.function.BiConsumer

class Server(private val port: Int): Runnable {

    private val _clients = mutableListOf<SocketChannel>()
    val clients get() = _clients.toMutableList()

    private val sel = Selector.open()
    private val serverChannel = NIOServerChannel(port, sel).get()
    private val engine = ServerEngine(sel, _clients)

    private val acceptor = ChannelAcceptor(serverChannel, _clients)
    private val reader = ChannelReader()
    private val writer = ChannelWriter()
    private val middleware = Middleware()

    override fun run() {
        buildEngine()
        println("Starting server on port: $port")
        engine.run()
    }

    fun use(func: (String, Response) -> Unit) = middleware.add(BiConsumer(func))

    private fun buildEngine() {
        reader.process = middleware.get()

        engine.add(acceptor)
        engine.add(reader)
        engine.add(writer)
    }
}