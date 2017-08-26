package com.vitorprado.reduxtest.redux

interface Enhancer<State> {

    fun enhance(reducer: (state: State, action: Action) -> State) : (state: State, action: Action) -> State
}