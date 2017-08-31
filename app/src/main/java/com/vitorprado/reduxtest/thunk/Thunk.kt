package com.vitorprado.reduxtest.thunk

import com.vitorprado.reduxtest.redux.Action

/**
 * Created by bloder on 29/08/17.
 */
class Thunk<State>(private var state: State? = null) : Action<State> {

    private val make: MutableList<Action<State>> = mutableListOf()

    fun make(init: Thunk<State>.() -> Any?) : Thunk<State> {
        this.init()
        return this
    }

    fun then(init: Thunk<State>.() -> Any?) : Thunk<State> {
        this.init()
        return this
    }

    fun dispatch(action: Action<State>) = make.add(action)

    override fun action(state: State): State {
        this.state = state
        make.forEach { this.state = it.action(this.state!!) }
        return this.state!!
    }
}

fun <State> Any.asyncThunk(init: Thunk<State>.() -> Thunk<State>) : Thunk<State> = Thunk<State>().init()