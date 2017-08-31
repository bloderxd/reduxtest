package com.vitorprado.reduxtest.events

import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Button
import com.vitorprado.reduxtest.AppActions
import com.vitorprado.reduxtest.AppState
import com.vitorprado.reduxtest.R
import com.vitorprado.reduxtest.redux.Action
import com.vitorprado.reduxtest.redux.store
import com.vitorprado.reduxtest.thunk.asyncThunk

class EventListActivity : AppCompatActivity() {

    private val list by lazy { findViewById(R.id.list) as RecyclerView }
    private val add by lazy { findViewById(R.id.add) as Button }
    private val showDialog by lazy { findViewById(R.id.show_dialog) }
    private var subscription: () -> Any? = {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.event_listing)
        list.layoutManager = LinearLayoutManager(this)
        showDialog.setOnClickListener { store().dispatch(addEventWithThunk()) }
    }

    private fun addEventWithThunk() : Action<AppState> = asyncThunk {
        make { dispatch(AppActions.AddEvent("1")) }
        then { dispatch(AppActions.AddEvent("2")) }
        then { dispatch(AppActions.AddEvent("3")) }
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

    private fun showPresentationDialog() {
        AlertDialog.Builder(this)
                .setTitle("Welcome!")
                .setMessage("This app is a redux implementation test!!!")
                .setPositiveButton("Ok", { dialog, _ -> dialog.dismiss() })
                .show()
    }
}