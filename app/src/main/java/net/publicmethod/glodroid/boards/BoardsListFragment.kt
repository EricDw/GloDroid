package net.publicmethod.glodroid.boards

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import net.publicmethod.glodroid.R
import net.publicmethod.glodroid.viewmodels.StateViewModel

class BoardsListFragment : Fragment() {

    private val viewModel: StateViewModel<BoardsListViewState> by lazy {
        ViewModelProviders.of(this)
            .get(BoardsListViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.boards_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val authCode = arguments?.run {
            BoardsListFragmentArgs.fromBundle(this).code
        } ?: ""

    }

}
