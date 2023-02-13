package sec.util

import sec.interpreter

/**
 * A really simple CLI for autogenerating the kotlin parser for XML elements.
 *
 * <Item1 Q1F5="2" Q1I="N" Q1M="N" Q1N="N" Q1O="N" Q1P="549300XR0J90YJAJ0H10"/>
 */

fun main() {
    interpreter ("mapping> ", true) {
        if (it.trim ().isNotEmpty ()) {
            val mapping = parseMapping (it)
            println (generateMapping (mapping))
        }
        true
    }
}

data class Mapping (val token: String, val pairs: Map<String, String> = mapOf<String, String> ())

private enum class ParseState {
    KEY,
    EQUALS,
    VALUE
}

fun parseAttributes (input: String): Map<String, String> {
    val map = mutableMapOf<String, String> ()
    val iter = input.iterator()

    val buf = StringBuffer ()
    var state = ParseState.KEY
    fun save () {
        val str = buf.toString ()
        val i = str.indexOf ('=')
        val key = str.substring (0, i).trim ()
        val value = str.substring (i + 1, str.length)
        map[key] = value
        buf.setLength (0)
        state = ParseState.KEY
    }

    while (iter.hasNext ()) {
        val c = iter.next ()
        if (c == '\"' && state == ParseState.VALUE) {
            save ()
            continue
        }
        buf.append (c)
        when (state) {
            ParseState.KEY -> if (c == '=') {
                state = ParseState.EQUALS
            }
            ParseState.EQUALS -> if (c != '\"') {
                error ("Expected \" found $c")
            } else {
                state = ParseState.VALUE
            }
            ParseState.VALUE -> Unit
        }
    }
    if (buf.isNotEmpty()) {
        save ()
    }

    return map
}

fun parseMapping (input: String): Mapping {
    var str = input.trim ()
    if (! str.startsWith ("<")) {
        error ("Doesn't start with '<'")
    }
    if (! str.endsWith ("/>")) {
        error ("Doesn't end with '/>'")
    }
    str = input.substring (1, input.length - 2)
    val i = str.indexOf (' ')
    if (i < 0) {
        val token = str.substring (0, str.length)
        return Mapping (token)
    }
    val token = str.substring (0, i)
    val rest = str.substring (i, str.length).trim ()
    val map = parseAttributes (rest)
    return Mapping (token, map)
}

fun generateMapping (mapping: Mapping): String {
    fun tweak (key: String): String = key.first ().toLowerCase() + key.substring (1, key.length)

    return StringBuffer ().apply {
        append ("@JacksonXmlRootElement (localName=\"${mapping.token}\")\n")
        append ("data class ${mapping.token} (\n")
        for ((key, value) in mapping.pairs) {
            val type = when (value) {
                "Y", "N" -> "YesNo"
                else -> "String"
            }
            append ("  @JacksonXmlProperty(localName=\"$key\", isAttribute=true)\n")
            append ("  val ${tweak (key)}: $type? = null,\n")
        }
        append (")\n")
    }.toString ()
}

// EOF