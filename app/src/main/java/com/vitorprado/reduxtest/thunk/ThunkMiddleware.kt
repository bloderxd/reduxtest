package com.vitorprado.reduxtest.thunk

import com.vitorprado.reduxtest.redux.Action
import com.vitorprado.reduxtest.redux.middleware.Middleware

/**
 * Created by bloder on 29/08/17.
 */
class ThunkMiddleware<State> : Middleware<State> {

    override fun compose(reducer: (State, Action<State>) -> State): (State, Action<State>) -> State {
        return { state, action ->
            (action as? Thunk<State>)?.action(state)?:reducer(state, action)
        }
    }
}