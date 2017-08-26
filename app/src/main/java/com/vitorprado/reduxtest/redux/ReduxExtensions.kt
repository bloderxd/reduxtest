package com.vitorprado.reduxtest.redux

import android.app.Activity
import com.vitorprado.reduxtest.ReduxApp


fun Activity.store() = ReduxApp.getStore()