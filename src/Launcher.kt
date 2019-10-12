import nioserver.Server

fun main() {
    val server = Server(1337)
    server.use { req, res ->
        println("Req: $req Res: $res")
        res.send("Lmfao sending out this message")
    }
    server.run()
}