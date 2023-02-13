package graphql

import com.example.config.Config
import com.example.util.ILoggable
import com.example.util.JsonUtil
import com.example.util.ResourceUtil
import com.example.util.random
import com.fasterxml.jackson.databind.node.ArrayNode
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.instance
import graphql.execution.instrumentation.tracing.TracingInstrumentation
import graphql.schema.*
import graphql.schema.GraphQLScalarType
import graphql.schema.idl.RuntimeWiring
import graphql.schema.idl.SchemaGenerator
import graphql.schema.idl.SchemaParser
import graphql.schema.idl.TypeDefinitionRegistry
import graphql.schema.idl.TypeRuntimeWiring.newTypeWiring
import graphql.sec.SecFetchers
import graphql.system.SystemFetchers
import com.example.util.ServerVersion
import java.io.File

/**
 * The list of registered GraphQL operations that will be loaded
 * at startup time.
 */

private val wireables = listOf<Wireable> (
    SystemFetchers,
    SecFetchers
)

//data class RandomResult (val max: Int, val value: Int = max.random ())

private object SCHEMA {
    val ROOT = "graphql"
    val MANIFEST = "graphql/schema.json"
}

data class ServerVersionResponse (val version: String, var timestamp: Long = System.currentTimeMillis())

public class GraphQLProvider (var config: Kodein?) : ILoggable {
    val graphQL: GraphQL = initialize ()

    private fun initialize (): GraphQL {
        val schema = getSchema ()
        val builder = GraphQL.newGraphQL (schema)
        config?.let {
            val Config = it.instance<Config> ()
            if (Config.graphTracing) {
                builder.instrumentation (TracingInstrumentation ())
            }
        }
        return builder.build ();
    }

    fun getSchema (): GraphQLSchema {
        val typeRegistry = TypeDefinitionRegistry()
        val schemaParser = SchemaParser()
        val schemas = getSchemas ()
        for (schema in schemas) {
            val inputStream = ResourceUtil.getAsStream(schema)
            try {
                typeRegistry.merge(schemaParser.parse(inputStream));
            }
            catch (t: Throwable) {
                logger.error ("Error parsing $schema")
                throw t
            }
        }
        val runtimeWiring = buildWiring ();
        val schemaGenerator = SchemaGenerator();
        val graphQLSchema = schemaGenerator.makeExecutableSchema(typeRegistry, runtimeWiring);
        return graphQLSchema
    }

    private fun getSchemas (): List<String> {
        val manifest = JsonUtil.parseResource(SCHEMA.MANIFEST)
        val schemas = manifest.get("schema") as ArrayNode

        return schemas.map {
            val rsrc = it.asText()
            SCHEMA.ROOT + File.separatorChar + rsrc + ".graphqls"
        }
    }

    private val randomNumberFetcher: DataFetcher<Int>
        get () = DataFetcher<Int> { dfe ->
            val max = dfe.getArgument<Int>("max")
            max.random ()
        }

    private val getServerVersion: DataFetcher<ServerVersionResponse>
        get () = DataFetcher { dfe ->
            ServerVersionResponse (ServerVersion.version)
        }

    /**
     * Adds support for raw maps.
     */

    fun addAny (wiring: RuntimeWiring.Builder) {
        val any = GraphQLScalarType.Builder ()
            .name ("Any")
            .description ("A raw map")
            .coercing(object: Coercing<Map<String, Any?>, Map<String, Any?>> {
                override fun serialize(dataFetcherResult: Any): Map<String, Any?>? {
                    return dataFetcherResult as Map<String, Any?>
                }

                override fun parseValue(input: Any): Map<String, Any?>? {
                    return input as Map<String, Any?>
                }

                override fun parseLiteral(input: Any): Map<String, Any?>? {
                    return input as Map<String, Any?>
                }
            })

        wiring.scalar (any.build ())
        return
    }

    private fun buildWiring(): RuntimeWiring {
        val wiring = RuntimeWiring.newRuntimeWiring()
                .type(newTypeWiring ("Mutation")
                )
                .type(newTypeWiring("Query")
                        .dataFetcher ("randomNumber", randomNumberFetcher)
                        .dataFetcher ("getServerVersion", getServerVersion)
                )

        // Add wiring for our functionality

        for (wireable in wireables) {
            logger.info ("Invoking wireable ${wireable}")
            wireable.wire(wiring)
        }

        // Add support for the Upload file type and the native Long

        addAny (wiring)
//        wiring.scalar (Scalars.GraphQLLong)

        return wiring.build()
    }
}

// EOF