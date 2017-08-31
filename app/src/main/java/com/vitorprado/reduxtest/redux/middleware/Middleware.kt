package com.vitorprado.reduxtest.redux.middleware

import com.vitorprado.reduxtest.redux.Action

interface Middleware<State> {
    fun compose(reducer: (State, T : Action<State>) -> State) : (State, Action<State>) -> State
}