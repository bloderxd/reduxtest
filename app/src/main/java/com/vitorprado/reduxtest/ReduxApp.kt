package com.vitorprado.reduxtest

import android.app.Application
import com.vitorprado.reduxtest.logger.LoggerMiddleware
import com.vitorprado.reduxtest.redux.Store
import com.vitorprado.reduxtest.redux.middleware.ApplyMiddleware

object ReduxApp : Application() {

    private var myStore = Store(
            AppState("", arrayListOf()),
            { state, action ->
                when(action) {
                    is AppActions.Login -> AppState(action.user, state.events)
                    is AppActions.AddEvent -> AppState(state.user, state.events.plus(action.event))
                    is AppActions.RemoveEvent -> AppState(state.user, state.events.minus(action.event))
                    else -> state
                }
            },
            ApplyMiddleware(LoggerMiddleware())
    )

    override fun onCreate() {
        super.onCreate()
    }

    fun getStore() = myStore
}