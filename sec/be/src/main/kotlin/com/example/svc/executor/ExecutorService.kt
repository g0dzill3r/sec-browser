package com.example.svc.executor

import com.example.util.ILoggable
import java.util.concurrent.Executors

/**
 * A simple service for background execution of tasks.
 */

class ExecutorService() : ILoggable {
    private val executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2)

    fun execute (task: () -> Unit) {
        executor.execute(task)
    }

    fun execute (task: Runnable) {
        executor.execute(task)
    }
}
// EOF