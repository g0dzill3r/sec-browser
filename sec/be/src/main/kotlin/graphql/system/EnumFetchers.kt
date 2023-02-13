package graphql.system

import com.example.util.EnumUtil
import graphql.Wireable
import graphql.schema.DataFetcher
import graphql.schema.idl.RuntimeWiring
import graphql.schema.idl.TypeRuntimeWiring
import kotlin.reflect.KClass

data class EnumValues (
        val type: String,
        val values: List<String>
)

private val types = listOf<KClass<out Enum<*>>> (
)

object EnumFetchers : Wireable {
    private val enums by lazy {
        val list = mutableListOf<EnumValues> ()
        for (type in types) {
            val values = EnumUtil.getEnumValues (type)
            values.sortBy { it.name }
            val name = type.simpleName as String
            list.add (EnumValues (name, values.map { it.toString () }))
        }
        list
    }

    private val getEnums: DataFetcher<List<EnumValues>>
        get () = DataFetcher { dfe ->
            enums
        }

    override fun wire (wiring: RuntimeWiring.Builder) {
        wiring
            .type(TypeRuntimeWiring.newTypeWiring("Mutation")
                // EMPTY
            )
            .type(TypeRuntimeWiring.newTypeWiring("Query")
                    .dataFetcher ("getEnums", getEnums)
            )
    }
}

// EOF