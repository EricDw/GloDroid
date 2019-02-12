package net.publicmethod.glodroid.boards

import net.publicmethod.glodroid.BuildConfig
import net.publicmethod.glodroid.ViewCommand

sealed class BoardsListCommands : ViewCommand

data class HandleAuthenticationCode(
    val authenticationCode: String = ""
) : BoardsListCommands()

data class Initialize(
    val buildType: String = BuildConfig.BUILD_TYPE
) : BoardsListCommands()