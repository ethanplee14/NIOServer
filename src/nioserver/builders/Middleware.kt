package nioserver.builders

import nioserver.Response
import java.util.function.BiConsumer
import java.util.function.Supplier

class Middleware: Supplier<BiConsumer<String, Response>> {

    private var middleware = BiConsumer<String, Response>{ req, res -> Unit}

    override fun get(): BiConsumer<String, Response> = middleware

    fun add(middleware: BiConsumer<String, Response>) {
        this.middleware = this.middleware.andThen { req, res ->
            if (!res.end) middleware.accept(req, res)
        }
    }
}