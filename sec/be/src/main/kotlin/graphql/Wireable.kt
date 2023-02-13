package graphql

import graphql.schema.idl.RuntimeWiring

interface Wireable {
    fun wire (wiring: RuntimeWiring.Builder)
}

// EOF