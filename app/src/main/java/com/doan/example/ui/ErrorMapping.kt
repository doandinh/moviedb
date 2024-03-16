package com.doan.example.ui

import android.content.Context
import com.doan.example.R
import com.doan.example.domain.exceptions.ApiException

fun Throwable.userReadableMessage(context: Context): String {
    return when (this) {
        is ApiException -> error?.message
        else -> message
    } ?: context.getString(R.string.error_generic)
}
