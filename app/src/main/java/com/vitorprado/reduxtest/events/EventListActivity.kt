package com.vitorprado.reduxtest.events

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Button
import com.vitorprado.reduxtest.AppActions
import com.vitorprado.reduxtest.AppState
import com.vitorprado.reduxtest.R
import com.vitorprado.reduxtest.redux.store
import java.util.*

class EventListActivity : AppCompatActivity() {

    private val list by lazy { findViewById(R.id.list) as RecyclerView }
    private val add by lazy { findViewById(R.id.add) as Button }
    private var subscription: () -> Any? = {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.event_listing)
        list.layoutManager = LinearLayoutManager(this)
        add.setOnClickListener { store().dispatch(AppActions.AddEvent(UUID.randomUUID().toString())) }
    }

    override fun onResume() {
        super.onResume()
        subscription = store().subscribe { render(it) }
    }

    override fun onPause() {
        super.onPause()
        subscription()
    }

    private fun render(state: AppState) {
        list.adapter = EventAdapter(this, state.events, { store().dispatch(AppActions.RemoveEvent(it)) })
    }
}