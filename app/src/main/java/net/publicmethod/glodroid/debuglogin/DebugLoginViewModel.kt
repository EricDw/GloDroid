package net.publicmethod.glodroid.debuglogin

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import net.publicmethod.glodroid.GloRepository
import net.publicmethod.glodroid.PersonalAuthenticationToken
import net.publicmethod.glodroid.UserCache
import net.publicmethod.glodroid.scopes.IOScope
import net.publicmethod.glodroid.scopes.UIScope
import net.publicmethod.glodroid.viewmodels.StateViewModel

class DebugLoginViewModel(
    private val userCache: UserCache,
    private val gloRepository: GloRepository,
    private val ioScope: IOScope,
    private val uiScope: UIScope
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
                    if (isValid) userCache.personalAuthenticationToken = this
                    _state.value?.run {
                        _state.value = copy(
                            isLoginButtonEnabled = isValid
                        )
                    }
                }
            }
            AttemptLogin -> {
                ioScope.launch(ioScope.coroutineContext) {
                    userCache.personalAuthenticationToken?.run {
                        if (isValid)
                            gloRepository.getUserFor(this)?.let { userDTO ->
                                uiScope.launch {
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
    }
}
