package graphql

import com.example.util.ILoggable
import com.github.salomonbrys.kodein.Kodein

/**
 *
 */

class GraphQLService (val config: Kodein) : ILoggable {
    var provider = GraphQLProvider (config)
        private set

    fun reload () {
        provider = GraphQLProvider (config)
        return
    }
}

// EOF