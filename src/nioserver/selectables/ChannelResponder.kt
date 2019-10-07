package nioserver.selectables

import nioserver.Response
import nioserver.selectables.abstr.Writable
import java.util.function.Consumer

class ChannelResponder : Writable() {

    private val middleware = Consumer<Response> {}

    override fun write(req: String) {
        val res = Response()
        middleware.accept(res)
    }

    fun add(middleware: Consumer<Response>) {
        this.middleware.andThen {
            if (!it.end) middleware.accept(it)
        }
    }
}