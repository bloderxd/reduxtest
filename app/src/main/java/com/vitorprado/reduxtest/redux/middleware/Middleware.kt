package com.vitorprado.reduxtest.redux.middleware

import com.vitorprado.reduxtest.redux.Action

interface Middleware<State> {
    fun compose(reducer: (State, Action) -> State) : (State, Action) -> State
}