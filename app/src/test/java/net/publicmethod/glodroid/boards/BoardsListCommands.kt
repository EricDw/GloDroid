package net.publicmethod.glodroid.boards

import net.publicmethod.glodroid.ViewCommand

sealed class BoardsListCommands : ViewCommand

data class HandleAuthenticationCode(
    val authenticationCode: String = ""
) : BoardsListCommands()