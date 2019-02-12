package net.publicmethod.glodroid.debuglogin

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.debug_login_fragment.*

import net.publicmethod.glodroid.R
import net.publicmethod.glodroid.viewmodels.ViewModelFactoryImpl

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

        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactoryImpl()
        ).get(DebugLoginViewModel::class.java)

        viewModel.state.observe(this, Observer(this::renderDetailsFor))

        textInputEditText_personalAuthenticationToken.doAfterTextChanged {
            it?.toString()?.run {
                viewModel.send(ValidateTokenCommand(this))
            }
        }

    }

    private fun renderDetailsFor(aViewState: DebugLoginViewState?) {
        aViewState?.run {

            activity?.fab?.run {

                isEnabled = isLoginButtonEnabled

                setImageResource(
                    R.drawable.ic_lock_open_white_24dp.takeIf { isLoginButtonEnabled }
                        ?: R.drawable.ic_lock_white_24dp
                )

                setOnClickListener {
                    // Send login command

                }
            }
        }
    }

}
