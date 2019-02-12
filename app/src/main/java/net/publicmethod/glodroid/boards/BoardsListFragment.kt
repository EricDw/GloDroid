package net.publicmethod.glodroid.boards

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.boards_list_fragment.*
import net.publicmethod.glodroid.R
import net.publicmethod.glodroid.viewmodels.StateViewModel

class BoardsListFragment : Fragment() {

    private val viewModel: StateViewModel<BoardsListViewState, BoardsListCommands> by lazy {
        ViewModelProviders.of(this)
            .get(BoardsListViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.boards_list_fragment,
            container,
            false
        )
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.state.observe(this, Observer(this::render))

//        viewModel.send(HandleAuthenticationCode(authCode))

    }

    private fun render(state: BoardsListViewState?) {
        state?.run {
            when (consumable) {
                is NavigateToLogin ->
                    if (!consumable.consumed)
                        consumable.consume().also {
                            view?.findNavController()
                                ?.navigate(R.id.action_boardsListFragment_to_loginFragment)
                        }
            }
        }
    }
}


