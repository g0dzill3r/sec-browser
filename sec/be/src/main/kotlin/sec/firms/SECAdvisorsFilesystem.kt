package sec.firms

import com.example.config.Config
import com.example.config.LocalFilesystem
import com.example.config.config
import com.github.salomonbrys.kodein.instance

fun main() {
    val conf = config.instance<Config> ()
    val local = conf.get (LocalFilesystem::class.java)
    println (local)
    return
}

// EOF