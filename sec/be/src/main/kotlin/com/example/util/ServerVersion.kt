package com.example.util

import com.example.config.config
import com.github.salomonbrys.kodein.instance

object ServerVersion {
    val version: String by lazy {
        config.instance<String>("version")
    }
}

// EOF