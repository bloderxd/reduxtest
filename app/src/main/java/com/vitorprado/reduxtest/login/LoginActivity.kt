package com.vitorprado.reduxtest.login

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.AutoCompleteTextView
import com.vitorprado.reduxtest.AppActions
import com.vitorprado.reduxtest.R
import com.vitorprado.reduxtest.events.EventListActivity
import com.vitorprado.reduxtest.redux.store

class LoginActivity : AppCompatActivity() {

    private val mEmailView by lazy { findViewById(R.id.email) as AutoCompleteTextView }
    private var subscription: () -> Any? = {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        findViewById(R.id.email_sign_in_button).setOnClickListener { store().dispatch(AppActions.Login(mEmailView.text.toString())) }
    }

    override fun onResume() {
        super.onResume()
        subscription = store().subscribe { if (it.user.isNotEmpty()) openEventsScreen() }
    }

    override fun onPause() {
        super.onPause()
        subscription()
    }

    private fun openEventsScreen() {
        startActivity(Intent(this, EventListActivity::class.java))
        finish()
    }
}

