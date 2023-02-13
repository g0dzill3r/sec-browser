package com.example.config

import com.example.svc.executor.ExecutorService
import com.example.svc.init.Initializable
import com.example.util.ILoggable
import com.example.util.ifNull
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.github.salomonbrys.kodein.*
import graphql.GraphQLService
import sec.SecService


/**
 * Data classes that are used to import sections of the
 * application configuration.
 */

data class ServerConfig (val port: Int)
data class LocalFilesystem (val resourceRoot: String)

private object ENVIRONMENT {
    val PROPERTY = "sec.env"
    val DEFAULT = "localhost"
}

// Create a local logger that we can use for messaging

val logger = (object : ILoggable {}).logger

/**
 * Initialize the application configuration.
 */

val config = Kodein {

    // Figure out what environment we're operating and also
    // save it in the config so we know who we are

    val environment = System.getProperty (ENVIRONMENT.PROPERTY).ifNull {
        logger.warn ("Environment (${ENVIRONMENT.PROPERTY}) not set; using \"${ENVIRONMENT.DEFAULT}\".")
        ENVIRONMENT.DEFAULT
    }

    constant ("env") with environment
    logger.info(" environment configured: ${environment}")

    AppConfig.registerConfigType ("local", LocalFilesystem::class.java)

    val inputStream = AppConfig::class.java.getResourceAsStream("/app.toml")

    val Config = AppConfig
        .fromToml(inputStream)
        // we should probably be reading this from the args
        .get(environment)!!

    bind<Config>() with instance (Config)

    // Load the version and the githash from the resources directory

    constant ("version") with "0.0.1" //ResourceUtil.getAsString("version.txt")

    val initializers = mutableListOf<Initializable<*>> ()
    constant ("initializers") with initializers

    bind<ExecutorService>() with singleton {
        ExecutorService()
    }

    bind<ObjectMapper>() with singleton {
        ObjectMapper().registerKotlinModule()
    }

    // Database entity support

    bind<GraphQLService>() with eagerSingleton { GraphQLService (this) }
    bind<SecService>() with singleton { SecService (this) }
}

// EOF