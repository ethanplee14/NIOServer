import nioserver.NIOServerChannel
import nioserver.ServerEngine
import nioserver.selectables.ChannelAcceptor
import nioserver.selectables.ChannelReader
import nioserver.selectables.ChannelResponder
import java.nio.channels.Selector
import java.util.function.BiConsumer

fun main() {
    val sel = Selector.open()
    val serverChannel = NIOServerChannel(1337, sel).get()
    val engine = ServerEngine(sel)
    val responder = ChannelResponder()

    responder.add(BiConsumer {req, res ->
        println("Req: $req Res: $res")
    })

    engine.add(ChannelAcceptor(serverChannel))
    engine.add(ChannelReader())
    engine.add(responder)

    println("Starting server")
    engine.run()
}