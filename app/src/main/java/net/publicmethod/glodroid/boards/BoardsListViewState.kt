package net.publicmethod.glodroid.boards

import net.publicmethod.glodroid.utils.Consumable

data class BoardsListViewState(
    val authCode: String = "",
    val consumable: Consumable = Empty()
)
