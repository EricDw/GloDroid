package net.publicmethod.glodroid.debuglogin

import net.publicmethod.glodroid.viewmodels.ViewState

data class DebugLoginViewState(
    val isLoginButtonEnabled: Boolean = false,
    val consumable: DebugLoginConsumable = DebugLoginConsumable.Empty
) : ViewState
