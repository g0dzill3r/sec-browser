package com.example.util

import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 * An interface that automatically adds an appropriate logger property to
 * a class that implements it.
 */

interface ILoggable {
    val logger: Logger
        get() = LoggerFactory.getLogger(javaClass)


    fun Logger.debug(msg: () -> Any?) {
        if (isDebugEnabled) debug(msg.toStringSafe())
    }

    fun Logger.info(msg: () -> Any?) {
        if (isInfoEnabled) info(msg.toStringSafe())
    }

    fun Logger.warn(msg: () -> Any?) {
        if (isWarnEnabled) warn(msg.toStringSafe())
    }

    fun Logger.error(msg: () -> Any?) {
        if (isErrorEnabled) error(msg.toStringSafe())
    }

    fun Logger.error(t: Throwable?, msg: () -> Any?) {
        if (isErrorEnabled) error(msg.toStringSafe(), t)
    }

    fun Logger.trace(msg: () -> Any?) {
        if (isTraceEnabled) trace(msg.toStringSafe())
    }
}

fun (() -> Any?).toStringSafe (): String {
    return try {
        this ().toString ()
    }
    catch (e: Exception) {
        e.toString ()
    }
}

// EOF