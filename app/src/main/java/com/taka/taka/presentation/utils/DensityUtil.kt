package com.taka.taka.presentation.utils

import android.content.res.Resources

inline val Number.dpToPx: Float
    get() = toFloat() * Resources.getSystem().displayMetrics.density