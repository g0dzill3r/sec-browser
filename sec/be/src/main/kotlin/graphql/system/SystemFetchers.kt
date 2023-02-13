package graphql.system

import com.example.config.Config
import com.example.config.LocalFilesystem
import com.example.config.config
import com.example.util.ILoggable
import com.github.salomonbrys.kodein.instance
import graphql.Wireable
import graphql.schema.DataFetcher
import graphql.schema.idl.RuntimeWiring
import graphql.schema.idl.TypeRuntimeWiring

data class GraphQLVoid (val success: Boolean =  true)

data class SystemConfiguration (
    val env: String,
    val filesystem: String
)

/**
 *
 */

object SystemFetchers : ILoggable, Wireable {
    override fun wire(wiring: RuntimeWiring.Builder) {
        wiring
                .type(TypeRuntimeWiring.newTypeWiring("Mutation")
                    .dataFetcher ("noOp", noOp)
                )
                .type(TypeRuntimeWiring.newTypeWiring("Query")
                    .dataFetcher ("noOp", noOp)
                    .dataFetcher ("throwException", throwException)
                    .dataFetcher ("delay", delay)
                    .dataFetcher ("getSystemConfiguration", getConfig)
                )
    }

    private val getConfig: DataFetcher<SystemConfiguration>
        get () = DataFetcher { dfe ->
            val conf: Config = config.instance ()
            val filesystem = conf.get (LocalFilesystem::class.java)
            SystemConfiguration (config.instance("env"), filesystem!!.resourceRoot)
        }

    private val delay: DataFetcher<Boolean>
        get () = DataFetcher { dfe ->
            val duration = dfe.getArgument<Int>("duration").toLong()
            Thread.sleep(duration)
            true
        }

    private val noOp: DataFetcher<Boolean>
        get () = DataFetcher { dfe -> true }

    val throwException: DataFetcher<Boolean>
        get () = DataFetcher { dfe ->
            throw Exception ("This is an exception.")
        }
}

// EOF