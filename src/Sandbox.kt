import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.Socket

fun main() {
    val socket = Socket("localhost", 1337)
    val out = PrintWriter(socket.getOutputStream(), true)
    val reader = BufferedReader(InputStreamReader(socket.getInputStream()))

    out.println("This is some different data...")

    println("Reading")

    var ready: Boolean
    do {
        print(reader.read().toChar())
        ready = reader.ready()
    }while (ready)
    println()
    println("Finished")

    while(true) {Thread.sleep(500)}
    out.close()
    reader.close()
    socket.close()
}
