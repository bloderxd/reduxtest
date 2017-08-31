package com.vitorprado.reduxtest.redux

interface Action<State> {
    fun action(state: State) : State
}