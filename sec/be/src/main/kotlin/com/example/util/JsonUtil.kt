package com.example.util

import com.example.config.config
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.node.ObjectNode
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.github.salomonbrys.kodein.instance
import java.io.IOException

/**
 * Created by crawford on 2/9/17.
 */

val Any?.asJson
    get () = JsonUtil.stringify (this)

val Any?.asPrettyJson
    get () = JsonUtil.prettyPrint (this)

val Any?.asJsonMap: Map<String, Any?>?
    get () {
        val mapper = config.instance<ObjectMapper> ()
        return if (this == null) {
            null
        } else {
            val json = mapper.writeValueAsString (this)
            JsonUtil.parseMap (json)
        }
    }

object JsonUtil {
    var mapper = ObjectMapper ().registerKotlinModule().setSerializationInclusion (JsonInclude.Include.NON_NULL);

    fun isJsonObject (json: String): Boolean {
        return try {
            parseMap (json)
            true
        } catch (t: Throwable) {
            false
        }
    }

    fun prettyPrint (any: Any?): String {
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString (any)
    }

    fun stringify (any: Any?): String {
        return mapper.writeValueAsString (any)
    }

    fun reformatJson (json: String): String {
        val parsed = mapper.readTree(json)
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(parsed)
    }

    fun parseResource (path: String): ObjectNode {
        val cls = Thread.currentThread().contextClassLoader
        val inputStream = cls.getResourceAsStream(path) ?: throw IOException ("Resource not found: ${path}")
        return mapper.readTree(inputStream) as ObjectNode
    }

    fun parseString (str: String): ObjectNode {
        return mapper.readTree(str) as ObjectNode
    }

    fun parseMap (str: String): MutableMap<String, Any?> {
        return mapper.readValue (str, MutableMap::class.java) as MutableMap<String, Any?>
    }

    fun parseList (str: String): List<Map<String, Any?>> {
        return mapper.readValue (str, List::class.java) as List<Map<String, Any?>>
    }
}

// EOF