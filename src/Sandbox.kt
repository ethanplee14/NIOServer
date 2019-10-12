import java.io.BufferedReader
import java.io.PrintWriter
import java.net.Socket

fun main() {
    val socket = Socket("localhost", 8000)
    val out = PrintWriter(socket.getOutputStream())
    out.print("A different send...")
    out.flush()


    val reader = BufferedReader(socket.getInputStream().bufferedReader())
    println("Reading")
    println(reader.readLine())
    println("Finished")
    socket.close()
}
