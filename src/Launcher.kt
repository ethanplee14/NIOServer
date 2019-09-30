import nioserver.selectables.impl.ChannelAcceptor
import nioserver.selectables.impl.ChannelReader
import nioserver.provider.NIOServerChannel
import nioserver.runner.ServerEngine
import java.nio.channels.Selector

fun main() {
    val sel = Selector.open()
    val serverChannel = NIOServerChannel(1337, sel).get()
    val engine = ServerEngine(sel)

    engine.add(ChannelAcceptor(serverChannel))
    engine.add(ChannelReader())
    println("Starting server")
    engine.run()
}