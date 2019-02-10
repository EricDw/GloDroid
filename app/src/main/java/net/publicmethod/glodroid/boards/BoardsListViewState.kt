package net.publicmethod.glodroid.boards

import net.publicmethod.glodroid.utils.Consumable
import net.publicmethod.glodroid.viewmodels.ViewState

data class BoardsListViewState(
    val authCode: String = "",
    val consumable: Consumable = Empty()
): ViewState
