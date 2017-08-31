package com.vitorprado.reduxtest.redux

class Store<State>(initialState: State, initialReducer: (state: State, action: Action<State>) -> State, enhancer: Enhancer<State>? = null) {

    private val subscribers: MutableList<(State) -> Any?> = mutableListOf()
    private var isCurrentDispatching = false
    private val reducer: (state: State, action: Action<State>) -> State = enhancer?.enhance(initialReducer) ?: initialReducer
    private var state: State = initialState

    fun subscribe(subscriber: (State) -> Any?): () -> Any? {
        subscribers.add(subscriber)
        return { subscribers.remove(subscriber) }
    }

    fun dispatch(action: Action<State>) {
        startDispatching(action)
        reduce(action)
        stopDispatching()
        notifySubscribers()
    }

    private fun reduce(action: Action<State>) {
        state = reducer(state, action)
    }

    private fun notifySubscribers() {
        subscribers.forEach { it(state) }
    }

    private fun startDispatching(action: Action<State>) {
        if (isCurrentDispatching) throw IllegalStateException("You can't dispatch inside a dispatch -- ${action.javaClass.name}")
        isCurrentDispatching = true
    }

    private fun stopDispatching() {
        isCurrentDispatching = false
    }
}