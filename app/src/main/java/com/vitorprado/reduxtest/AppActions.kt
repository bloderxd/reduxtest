package com.vitorprado.reduxtest

import com.vitorprado.reduxtest.redux.Action

sealed class AppActions : Action {
    class Login(val user: String) : AppActions()
    class AddEvent(val event: String) : AppActions()
    class RemoveEvent(val event: String) : AppActions()
}