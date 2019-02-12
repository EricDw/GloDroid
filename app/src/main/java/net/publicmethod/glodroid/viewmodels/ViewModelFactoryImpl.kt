package net.publicmethod.glodroid.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import net.publicmethod.glodroid.boards.BoardsListViewModel
import net.publicmethod.glodroid.cache.UserCacheImpl
import net.publicmethod.glodroid.login.LoginViewModel

class ViewModelFactoryImpl : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        when (modelClass) {
            BoardsListViewModel::class.java -> BoardsListViewModel(
                UserCacheImpl()
            )
            LoginViewModel::class.java -> LoginViewModel()
            else -> throw IllegalArgumentException("Class $modelClass is assignable from ViewModel")
        } as T

}