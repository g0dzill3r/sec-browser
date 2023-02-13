package com.example.plugins

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.github.salomonbrys.kodein.instance
import graphql.ErrorClassification
import graphql.ExecutionInput
import graphql.GraphQLError
import graphql.language.SourceLocation
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import com.example.config.config
import graphql.GraphQLService

data class GraphQLQueryRes(
    val isDataPresent: Boolean,
    val data: Any?,
    val errors: List<GraphQLError?>,
    val extensions: Map<Any?, Any?>?
)

private val objectMapper = ObjectMapper().registerKotlinModule()


fun Application.installGraphQL() {
    routing {
        post("/graphql") {

            // Figure out what kind of request this is from the content type header

            var query: String? = null
            var map: Map<String, Any?>? = mapOf<String, Any?>()

            val isIt = call.request.isMultipart()

            val body = call.receiveText();
            val parsed = objectMapper.readValue(body, Map::class.java)
            query = parsed.get("query") as String
            map = parsed.get("variables") as? Map<String, Any?> ?: mapOf<String, Any?>()

            // Force the query and map to be non-null

            query as String
            map as Map<String, Any?>

            // Execute the GraphQL query

            try {

                // Now we're in a position to execute the GraphQL request in the authenticated
                // context of the requestor

                val ei = ExecutionInput.Builder()
                    .query(query)
                    .variables(map)
                    .context(context)

                val gql = config.instance<GraphQLService>()
                val e = ei.build()
                val v = gql.provider.graphQL.execute(e)
                val res = GraphQLQueryRes(v.isDataPresent, v.getData(), v.errors, v.extensions)

                // Now we'll run any post-processing listeners that have been registered; usually
                // these are responsible for managing the session cookies that we use for
                // authentication.

                call.respond(res)
            } catch (e: Exception) {
                e.printStackTrace()
                val error = object : GraphQLError {
                    override fun getMessage(): String = e.message ?: e::class.simpleName!!

                    override fun getErrorType(): ErrorClassification? {
                        return null
                    }

                    override fun getLocations(): MutableList<SourceLocation>? {
                        return null
                    }
                }
                val res = GraphQLQueryRes(false, null, listOf(error), null)
                call.respond(res)
            }
        }
    }
}