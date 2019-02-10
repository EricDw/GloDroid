package net.publicmethod.glodroid.boards

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import net.publicmethod.glodroid.ViewCommand
import net.publicmethod.glodroid.viewmodels.StateViewModel

class BoardsListViewModel
    : ViewModel(),
    StateViewModel<BoardsListViewState> {

    private val _state = MutableLiveData<BoardsListViewState>().apply {
        value = BoardsListViewState()
    }

    override val state: LiveData<BoardsListViewState> = _state

    override fun send(command: ViewCommand) {
        when (command) {
            is HandleAuthenticationCode -> {
                if (command.authenticationCode.isNotEmpty())
                    _state.value?.run {
                        _state.value = this.copy(
                            consumable = NavigateToLogin()
                        )
                    }
            }
        }
    }
}
