package net.publicmethod.glodroid.boards

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import net.publicmethod.glodroid.R

class BoardsListFragment : Fragment() {

    companion object {
        fun newInstance() = BoardsListFragment()
    }

    private lateinit var viewModel: BoardsListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.boards_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(BoardsListViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
