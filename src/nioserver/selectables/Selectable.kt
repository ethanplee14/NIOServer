package nioserver.selectables

import java.nio.channels.SelectionKey
import java.util.function.Consumer

interface Selectable : Consumer<SelectionKey>{
    fun condition(key: SelectionKey):Boolean
}