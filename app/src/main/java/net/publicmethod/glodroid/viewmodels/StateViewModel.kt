package net.publicmethod.glodroid.viewmodels

import androidx.lifecycle.LiveData
import net.publicmethod.glodroid.ViewCommand

interface StateViewModel<T: ViewState> {
    val state: LiveData<T>
    fun send(command: ViewCommand)
}