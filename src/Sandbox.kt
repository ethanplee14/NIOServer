import java.io.*
import java.net.Socket
import java.util.*

fun main() {
    val socket = Socket("localhost", 1337)

    val out = PrintWriter(socket.getOutputStream(), true)
    val reader = BufferedReader(InputStreamReader(socket.getInputStream()))

    Thread { read(reader)}.start()
    write(out)
}

private fun read(reader: Reader) {
    while(true) {
        val builder = StringBuilder()
        while (reader.ready()) {
            builder.append(reader.read().toChar())
        }
        if(builder.isNotEmpty())
            println(builder)
        builder.clear()
    }
}

private fun write(writer: Writer) {
    val scanner = Scanner(System.`in`)
    while (true) {
        val input = scanner.nextLine()
        writer.write(input)
        writer.flush()
    }
}
