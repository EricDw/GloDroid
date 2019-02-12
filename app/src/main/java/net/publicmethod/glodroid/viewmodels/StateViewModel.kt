package net.publicmethod.glodroid.viewmodels

import androidx.lifecycle.LiveData
import net.publicmethod.glodroid.ViewCommand

interface StateViewModel<T: ViewState, C: ViewCommand> {
    val state: LiveData<T>
    fun send(command: C)
}