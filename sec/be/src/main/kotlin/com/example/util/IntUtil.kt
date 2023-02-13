package com.example.util

fun Int.random (): Int {
    return Math.floor (Math.random() * this).toInt()
}

// EOF