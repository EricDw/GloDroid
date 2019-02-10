package net.publicmethod.glodroid.viewmodels

import net.publicmethod.glodroid.ViewCommand

interface StateViewModel {
    fun send(command: ViewCommand)
}