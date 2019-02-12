package net.publicmethod.glodroid.login

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.login_fragment.*
import net.publicmethod.glodroid.GLO_APP_CLIENT_ID
import net.publicmethod.glodroid.R
import net.publicmethod.glodroid.boards.BoardsListFragmentArgs

class LoginFragment : Fragment() {

    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.login_fragment, container, false)
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val authCode = arguments?.run {
            BoardsListFragmentArgs.fromBundle(this).code
        } ?: ""

        webView_login.settings.javaScriptEnabled = true

        val baseURl = "https://app.gitkraken.com/oauth/authorize"
        val responseType = "?response_type=code"
        val clientId = "?client_id=$GLO_APP_CLIENT_ID"
        val scope = "?scope=board:write+user:write"
        val fullURL = "$baseURl$responseType$clientId$scope"

        val client = WebViewClient()

        if (authCode.isBlank()) {
            webView_login.webViewClient = client
            webView_login.loadUrl(fullURL)
        }


//        textView.text = authCode

        viewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
