package com.example.svc.init

import com.github.salomonbrys.kodein.Kodein

interface Initializable<T> {
    fun initialize (config: Kodein): T
}

// EOF