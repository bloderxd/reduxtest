package com.vitorprado.reduxtest

import com.vitorprado.reduxtest.redux.Action

sealed class AppActions : Action<AppState> {

    class Login(val user: String) : AppActions() {
        override fun action(state: AppState): AppState = AppState(user, state.events)
    }

    class AddEvent(val event: String) : AppActions() {
        override fun action(state: AppState): AppState = AppState(state.user, state.events.plus(event))
    }

    class RemoveEvent(val event: String) : AppActions() {
        override fun action(state: AppState): AppState = AppState(state.user, state.events.minus(event))
    }

    class Execute(val action: () -> Any?) : AppActions() {
        override fun action(state: AppState): AppState {
            action.invoke()
            return state
        }
    }
}