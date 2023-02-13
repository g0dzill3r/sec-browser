package com.example.config

import com.example.util.ILoggable
import com.moandjiezana.toml.Toml
import java.io.File
import java.io.FileInputStream
import java.io.InputStream
import java.time.Duration


abstract class AppConfig {
    abstract fun get(name: String) : Config?

    companion object  {
        val configTypeMapping: MutableMap<String, Class<*>> = mutableMapOf()

        fun registerConfigType(name: String, type: Class<*>) {
            configTypeMapping[name] = type
        }

        fun fromToml (inputStream: InputStream) : AppConfig {
            val toml = Toml().read(inputStream)
            return TomlAppConfig(toml)
        }

        fun fromToml (tomlConfig: String) : AppConfig {
            val toml = Toml().read(tomlConfig)
            return TomlAppConfig(toml)
        }

        fun fromToml (tomlFile: File) : AppConfig {
            return fromToml(FileInputStream(tomlFile))
        }
    }
}

abstract class Config {
    var port: Int = -1
    var urlPrefix: String? = null
    val graphTracing: Boolean = false
    var debug: Boolean = false
    var passwordSecret: String = "whipped cream"
    var tokenSecret: String = "0xdeadbeef"
    var csrfSecret: String = "st3v1ew0nd3r"
    var tokenLifespan: Long = Duration.ofDays(3650).toMillis()
    val configs: MutableMap<Class<*>, Any> = mutableMapOf()

    inline fun <reified T> get(): T? {
        return configsAccessor[T::class.java] as T?
    }

    fun <T> get(type: Class<T>): T? {
        return configsAccessor[type] as T?
    }

    @PublishedApi
    internal val configsAccessor: Map<Class<*>, Any>
        get() = configs
}

class TomlAppConfig internal constructor(val baseToml: Toml) : AppConfig(), ILoggable {
    private val configMap = mutableMapOf<String, Config>()

    init {
        configMap["default"] = readConfig(baseToml)

        val localhostToml = baseToml.getTable("env.localhost") ?: baseToml
        configMap["localhost"] = readConfig(localhostToml)

        val devToml = baseToml.getTable("env.dev") ?: baseToml
        configMap["dev"] = readConfig(devToml)

        val stagingToml = baseToml.getTable("env.staging") ?: baseToml
        configMap["staging"] = readConfig(stagingToml)

        val prodToml = baseToml.getTable("env.prod") ?: baseToml
        configMap["prod"] = readConfig(prodToml)
    }

    private fun readConfig(toml: Toml) : Config {
        val config = toml.to(TomlConfig::class.java)
        AppConfig.configTypeMapping.forEach { name, clazz ->
            val t = (toml.getTable(name) ?: baseToml.getTable(name)) ?: return@forEach
            config.configs[clazz] = t.to(clazz)
        }
        return config
    }

    override fun get(name: String): Config? = configMap[name]
}


class TomlConfig : Config()

// EOF
