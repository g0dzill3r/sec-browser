package graphql.sec

import com.example.config.config
import com.fasterxml.jackson.databind.ObjectMapper
import com.github.salomonbrys.kodein.instance
import graphql.schema.DataFetchingEnvironment

data class FetchResults<T> (val skip: Int?, val limit: Int?, val total: Int, val results: List<T>)

fun <T> ObjectMapper.readValue (map: Map<String, Any?>, clazz: Class<T>) : T {
    val json = writeValueAsString (map)
    val s = readValue (json, clazz)
    return s
}

inline fun <reified T> DataFetchingEnvironment.getObjectArgument (key: String): T {
    val map = getArgument<Map<String, Any?>>(key)
    val mapper = config.instance<ObjectMapper> ()
    return mapper.readValue (map, T::class.java)
}



// EOF
