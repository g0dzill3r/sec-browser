package com.example.util

/**
 * Cuts a chunk out of a list according to the fetch request skip/limit parameters. Used
 * when we're pretending that we have a database underlying our queries.
 */

fun <T> List<T>.window (skip: Int, limit: Int): List<T> {
    val skipped = if (skip == 0) this else this.subList (skip, this.size)
    return if (limit < 0 || limit >= skipped.size) {
        skipped
    } else  {
        skipped.subList (0, limit)
    }
}

// EOF