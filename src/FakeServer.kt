import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.ServerSocket


fun main() {
    val server = ServerSocket(8000)
    val socket = server.accept()
    val reader = BufferedReader(InputStreamReader(socket.getInputStream()))
    println("Reading in")
    println(reader.readLine())
    val out = PrintWriter(socket.getOutputStream(), true)
    out.write("Writing")
    out.flush()
    server.close()
    socket.close()
}