package sec

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.MapperFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule
import com.fasterxml.jackson.dataformat.xml.XmlMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import java.io.Reader

/**
 * Utility methods for working with XML-based content streams.
 */

object XMLUtil {
    val kotlinXmlMapper: ObjectMapper = XmlMapper (JacksonXmlModule().apply {
        setDefaultUseWrapper(false)
    }).registerKotlinModule()
        .configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true)
        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true)
        .configure(SerializationFeature.INDENT_OUTPUT, true)

    inline fun <reified T : Any> parseAs (reader: Reader): T {
        return kotlinXmlMapper.readValue (reader, T::class.java)
    }

    fun encode (obj: Any?): String {
        return kotlinXmlMapper.writeValueAsString (obj)
    }
}

// EOF