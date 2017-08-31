package com.vitorprado.reduxtest

import android.app.Application
import com.vitorprado.reduxtest.logger.LoggerMiddleware
import com.vitorprado.reduxtest.redux.Store
import com.vitorprado.reduxtest.redux.middleware.ApplyMiddleware
import com.vitorprado.reduxtest.thunk.ThunkMiddleware

object ReduxApp : Application() {

    private var myStore = Store(
            AppState("", arrayListOf()),
            { state, action -> action.action(state) },
            ApplyMiddleware(LoggerMiddleware(), ThunkMiddleware())
    )

    override fun onCreate() {
        super.onCreate()
    }

    fun getStore() = myStore
}