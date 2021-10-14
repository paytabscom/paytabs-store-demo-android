package com.example.paytabs_demo_store_android.onboarding.view

import android.content.Context
import android.os.Handler
import android.view.View
import android.widget.Toast


fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.GONE
}

fun View.shouldShow(boolean: Boolean?) {
    if (boolean == true) {
        show()
    } else
        hide()
}

fun postDelayed(delayMillis: Long, task: () -> Unit) {
    Handler().postDelayed(task, delayMillis)
}
