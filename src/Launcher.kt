import nioserver.Server

fun main() {
    val server = Server(1337)
    server.use { req, res ->
        println("Req: $req")
        println("Echoing out message")
        res.send(req)
    }
    server.run()
}

