import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.ServerSocket
import java.net.Socket


fun main() {
    val server = ServerSocket(1337)

    var socket: Socket
    while (true) {
        socket = server.accept()
        println("Socket accepted from: ${socket.remoteSocketAddress}")
        var writer = PrintWriter(socket.getOutputStream(), true)
        println("Writing message")
        writer.print("Printing a message")
        writer.flush()
        socket.close()
        println("Closed")
    }
//    println("Reading in")
//    println("> " + reader.readLine())
//    val out = PrintWriter(socket.getOutputStream(), true)
//    out.write("Writing")
//    out.flush()
//    out.close()
//
//    server.close()
//    socket.close()
}