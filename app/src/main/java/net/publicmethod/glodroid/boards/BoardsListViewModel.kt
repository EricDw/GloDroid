package net.publicmethod.glodroid.boards

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import net.publicmethod.glodroid.UserCache
import net.publicmethod.glodroid.viewmodels.StateViewModel

class BoardsListViewModel(
    private val userCache: UserCache
) : ViewModel(),
    StateViewModel<BoardsListViewState, BoardsListCommands> {

    private val _state: MutableLiveData<BoardsListViewState> =
        MutableLiveData<BoardsListViewState>().apply {
            value = BoardsListViewState(showLoading = true)
        }

    override val state: LiveData<BoardsListViewState> = _state

    override fun send(command: BoardsListCommands) {
        when (command) {
            is Initialize -> processInitializeCommand(command)

            is HandleAuthenticationCode ->
                processHandleAuthenticationCodeCommand(command)
        }
    }

    private fun processInitializeCommand(command: Initialize) {
        if (userCache.isUserLoggedIn) {
            _state.value?.run {
                if (command.buildType == "debug")
                    _state.value = copy(
                        consumable = BoardsListConsumable.Empty,
                        showLoading = true
                    )
            }
        } else {
            _state.value?.run {
                if (command.buildType == "debug")
                    _state.value = copy(
                        consumable = NavigateToDebugLogin()
                    )
            }
        }
    }

    private fun processHandleAuthenticationCodeCommand(command: HandleAuthenticationCode) {
        if (command.authenticationCode.isNotBlank())
            _state.value?.run {
                _state.value = this.copy(
                    consumable = NavigateToLogin()
                )
            }
    }
}
