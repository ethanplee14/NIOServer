package nioserver

import lib.Runner
import nioserver.selectables.abstr.Selectable
import java.nio.channels.SelectionKey
import java.nio.channels.Selector

class ServerEngine(private val sel: Selector) : Runner() {

    private val selectables = ArrayList<Selectable>()

    fun add(vararg sel: Selectable) = selectables.addAll(sel)

    override fun exec() {
        sel.selectNow()
        val keys = sel.selectedKeys()
        val iterator = keys.iterator()

        while (iterator.hasNext()) {
            val key = iterator.next()
            iterator.remove()
            runSelectables(key)
        }
    }

    private fun runSelectables(key: SelectionKey) {
        selectables.forEach {
            if (it.condition(key))
                it.accept(key)
        }
    }
}