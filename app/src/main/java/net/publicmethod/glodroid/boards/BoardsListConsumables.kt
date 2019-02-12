package net.publicmethod.glodroid.boards

import net.publicmethod.glodroid.utils.Consumable

sealed class BoardsListConsumable : Consumable() {

    object Empty : BoardsListConsumable() {
        init {
            consume()
        }

        override fun isSameType(other: Any): Boolean =
            other is Empty
    }
}

class NavigateToDebugLogin : BoardsListConsumable() {
    override fun isSameType(other: Any): Boolean =
        other is NavigateToDebugLogin
}

class NavigateToLogin : BoardsListConsumable() {
    override fun isSameType(other: Any): Boolean =
        other is NavigateToLogin
}
