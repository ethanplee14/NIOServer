package nioserver.runner

import nioserver.selectables.Selectable
import java.nio.channels.SelectionKey
import java.nio.channels.Selector

class ServerEngine(private val sel: Selector) : Runner() {

    private val selectables = ArrayList<Selectable>()

    fun add(vararg sel: Selectable) = selectables.addAll(sel)

    override fun exec() {
        sel.selectNow()
        val keys = sel.selectedKeys()
        keys.forEach(this::runSelectables)
    }

    private fun runSelectables(key: SelectionKey) {
        selectables.forEach {
            if (it.condition(key))
                it.accept(key)
        }
    }
}