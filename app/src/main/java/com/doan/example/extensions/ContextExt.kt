package com.doan.example.extensions

import android.content.Context
import androidx.annotation.IdRes

/**
 * Get string by a given resource ID.
 *
 * @param resId String resource id
 * @return A string holding the name of the resource.
 */
fun Context.getResourceName(@IdRes resId: Int?): String? =
    resId?.let { resources.getResourceName(resId) }
