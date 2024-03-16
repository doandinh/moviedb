package com.doan.example.extensions

import android.view.View

fun View.visibleOrGone(isVisible: Boolean) {
    visibility = if (isVisible) {
        View.VISIBLE
    } else {
        View.GONE
    }
}