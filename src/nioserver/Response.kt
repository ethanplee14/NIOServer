package nioserver

open class Response {

    var msg = ""
    var end = false
        private set

    fun send(msg: String) {
        this.msg = msg
        end()
    }

    fun end() { end = true }

    fun available() = msg.isNotEmpty()

    override fun toString(): String = "Res: msg[$msg] ended[$end]"
}