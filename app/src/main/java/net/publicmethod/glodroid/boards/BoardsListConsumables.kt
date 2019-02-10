package net.publicmethod.glodroid.boards

import net.publicmethod.glodroid.utils.Consumable

sealed class BoardsListConsumable: Consumable()

class Empty : BoardsListConsumable() {
    init {
        consume()
    }
}

class NavigateToLogin : BoardsListConsumable()
