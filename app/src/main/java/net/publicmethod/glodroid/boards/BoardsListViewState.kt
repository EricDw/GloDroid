package net.publicmethod.glodroid.boards

import net.publicmethod.glodroid.viewmodels.ViewState

data class BoardsListViewState(
    val authCode: String = "",
    val consumable: BoardsListConsumable = BoardsListConsumable.Empty,
    val showLoading: Boolean = false
): ViewState
