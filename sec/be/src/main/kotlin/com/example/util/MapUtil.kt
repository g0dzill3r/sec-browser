package com.example.util

fun <T> MutableMap<T, Int>.inc (t: T) {
    if (containsKey (t)) {
        put (t, get (t)!! + 1)
    } else {
        put (t, 1)
    }
    return
}

// EOF