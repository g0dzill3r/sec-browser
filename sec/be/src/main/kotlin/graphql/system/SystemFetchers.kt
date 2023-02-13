package graphql.system

import com.example.util.ILoggable
import graphql.Wireable
import graphql.schema.DataFetcher
import graphql.schema.idl.RuntimeWiring
import graphql.schema.idl.TypeRuntimeWiring

data class GraphQLVoid (val success: Boolean =  true)

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
                )
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