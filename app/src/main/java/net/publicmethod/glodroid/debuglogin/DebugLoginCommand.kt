package net.publicmethod.glodroid.debuglogin

import net.publicmethod.glodroid.ViewCommand

sealed class DebugLoginCommand : ViewCommand

data class ValidateToken(
    val tokenInput: String
): DebugLoginCommand()
