package nioserver

open class Response {

    var res = ""
    var end = false
        private set

    fun send(msg: String) {
        res = msg
        end()
    }

    fun end() { end = true }

    override fun toString(): String = "Res: msg[$res] ended[$end]"
}