package net.publicmethod.glodroid.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.Dispatchers
import net.publicmethod.glodroid.GloRepositoryImpl
import net.publicmethod.glodroid.GloServiceImpl
import net.publicmethod.glodroid.boards.BoardsListViewModel
import net.publicmethod.glodroid.cache.UserCacheImpl
import net.publicmethod.glodroid.debuglogin.DebugLoginViewModel
import net.publicmethod.glodroid.login.LoginViewModel
import net.publicmethod.glodroid.scopes.IOScope
import net.publicmethod.glodroid.scopes.UIScope
import kotlin.coroutines.CoroutineContext

class ViewModelFactoryImpl(
    private val context: Context
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        when (modelClass) {
            BoardsListViewModel::class.java -> BoardsListViewModel(
                UserCacheImpl(context)
            )
            LoginViewModel::class.java -> LoginViewModel()
            DebugLoginViewModel::class.java -> DebugLoginViewModel(
                UserCacheImpl(context),
                GloRepositoryImpl(GloServiceImpl()),
                object : IOScope {
                    override val coroutineContext: CoroutineContext =
                        Dispatchers.IO
                },
                object : UIScope {
                    override val coroutineContext: CoroutineContext =
                        Dispatchers.Main
                }

            )
            else -> throw IllegalArgumentException("Class $modelClass is assignable from ViewModel")
        } as T

}