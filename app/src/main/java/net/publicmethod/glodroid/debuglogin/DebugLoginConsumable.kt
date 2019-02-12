package net.publicmethod.glodroid.debuglogin

import net.publicmethod.glodroid.utils.Consumable

sealed class DebugLoginConsumable : Consumable() {

    object Empty : DebugLoginConsumable() {
        init {
            consume()
        }

        override fun isSameType(other: Any): Boolean =
            other is Empty
    }

}

class NavigateToBoardsList : DebugLoginConsumable() {
    override fun isSameType(other: Any): Boolean =
        other is NavigateToBoardsList
}