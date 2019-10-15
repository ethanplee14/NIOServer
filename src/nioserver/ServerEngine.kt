package nioserver

import nioserver.lib.Runner
import nioserver.selectables.Selectable
import java.nio.channels.SelectionKey
import java.nio.channels.Selector
import java.nio.channels.SocketChannel

class ServerEngine(private val sel: Selector,
                   private val clients: MutableList<SocketChannel>) : Runner() {

    private val selectables = ArrayList<Selectable>()

    fun add(vararg sel: Selectable) = selectables.addAll(sel)

    override fun exec() {
        val iterator = keyIterator()

        while (iterator.hasNext()) {
            val key = iterator.next()
            iterator.remove()

            for (selectable in selectables)
                acceptKey(selectable, key)

            println(clients.size)
        }
    }

    private fun keyIterator(): MutableIterator<SelectionKey> {
        sel.selectNow()
        val keys = sel.selectedKeys()
        return keys.iterator()
    }

    private fun acceptKey(sel: Selectable, key: SelectionKey) {
        try{
            if(sel.condition(key))
                sel.accept(key)
        }catch (e: Exception) {
            println("Client disconnected")
            clients.remove(key.channel())
            key.channel().close()
        }
    }
}