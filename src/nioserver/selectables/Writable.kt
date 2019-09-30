package nioserver.selectables

import java.nio.channels.SelectionKey

abstract class Writable : Selectable {

    abstract fun write(req: String)

    override fun condition(key: SelectionKey): Boolean = key.isWritable

    override fun accept(key: SelectionKey) {
        val attachment = key.attachment()
        if(attachment is String)
            write(attachment)
    }
}