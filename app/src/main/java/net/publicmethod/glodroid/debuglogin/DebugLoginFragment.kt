package net.publicmethod.glodroid.debuglogin

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_main.*

import net.publicmethod.glodroid.R

class DebugLoginFragment : Fragment() {

    private lateinit var viewModel: DebugLoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.debug_login_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        activity?.fab?.run {

            setImageResource(R.drawable.ic_key_white_24dp)

            setOnClickListener {
                // Send login command
            }
        }


        viewModel = ViewModelProviders.of(this).get(DebugLoginViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
