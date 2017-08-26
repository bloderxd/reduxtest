package com.vitorprado.reduxtest.logger

import android.util.Log
import com.vitorprado.reduxtest.redux.Action
import com.vitorprado.reduxtest.redux.middleware.Middleware

class LoggerMiddleware<State> : Middleware<State> {
    override fun compose(reducer: (State, Action) -> State): (State, Action) -> State {
        return { state, action ->
            Log.d("Logger", "Reducing: ${state.toString()} with action: ${action.javaClass.name}")
            val result = reducer(state, action)
            Log.d("Logger", "Result in: ${result.toString()}")
            result
        }
    }
}