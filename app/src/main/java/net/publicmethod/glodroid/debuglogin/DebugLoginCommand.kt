package net.publicmethod.glodroid.debuglogin

import net.publicmethod.glodroid.ViewCommand

sealed class DebugLoginCommand : ViewCommand

data class ValidateTokenCommand(
    val tokenInput: String
): DebugLoginCommand()

object AttemptLogin : DebugLoginCommand()