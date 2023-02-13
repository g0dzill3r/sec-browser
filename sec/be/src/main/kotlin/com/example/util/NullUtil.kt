package com.example.util

fun <T> T?.ifNull (func: () -> T): T = this ?: func ()

// EOF