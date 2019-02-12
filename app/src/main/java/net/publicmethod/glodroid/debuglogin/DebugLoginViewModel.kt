package net.publicmethod.glodroid.debuglogin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import net.publicmethod.glodroid.GloRepository
import net.publicmethod.glodroid.PersonalAuthenticationToken
import net.publicmethod.glodroid.UserCache
import net.publicmethod.glodroid.viewmodels.StateViewModel

class DebugLoginViewModel(
    private val userCache: UserCache,
    private val gloRepository: GloRepository
) : ViewModel(),
    StateViewModel<DebugLoginViewState, DebugLoginCommand> {

    private val _state: MutableLiveData<DebugLoginViewState> =
        MutableLiveData<DebugLoginViewState>().apply {
            value = DebugLoginViewState(consumable = DebugLoginConsumable.Empty)
        }

    override val state: LiveData<DebugLoginViewState> = _state

    override fun send(command: DebugLoginCommand) {
        when (command) {
            is ValidateTokenCommand -> {
                PersonalAuthenticationToken(command.tokenInput).run {
                    _state.value?.run {
                        _state.value = copy(
                            isLoginButtonEnabled = isValid
                        )
                    }
                }
            }
            AttemptLogin -> {
                userCache.personalAuthenticationToken?.run {
                    if (isValid)
                       gloRepository.getUserFor(this)?.run {
                           _state.value?.run {
                               _state.value = copy(
                                   consumable = NavigateToBoardsList()
                               )
                           }
                       }
                }
            }
        }
    }
}
