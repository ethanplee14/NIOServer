import nioserver.selectables.ChannelAcceptor
import nioserver.selectables.ChannelReader
import nioserver.NIOServerChannel
import nioserver.Response
import nioserver.ServerEngine
import nioserver.selectables.ChannelResponder
import java.nio.channels.Selector
import java.util.function.Consumer

fun main() {
    val sel = Selector.open()
    val serverChannel = NIOServerChannel(1337, sel).get()
    val engine = ServerEngine(sel)
    val responder = ChannelResponder()

    engine.add(ChannelAcceptor(serverChannel))
    engine.add(ChannelReader())
    engine.add(responder)
    responder.add(Consumer { println(it) })

    println("Starting server")
    engine.run()
}