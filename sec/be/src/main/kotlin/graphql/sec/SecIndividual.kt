package graphql.sec

import sec.reps.SECIndividual

data class SECIndividualFindRequest (
    val skip: Int = 0,
    val limit: Int = -1,
    val filters: SECIndividualFilters? = null
)

data class SECIndividualFilters (
    val id: String?,
    val firstName: String?,
    val lastName: String?,
) {
    fun match (individual: SECIndividual): Boolean {
        id?.let {
            individual.id == id
        }
        firstName?.let {
            val uppercase = it.toUpperCase()
            return individual.info.firstNm!!.toUpperCase().contains (uppercase)
        }
        lastName?.let {
            val uppercase = it.toUpperCase()
            return individual.info.lastNm!!.toUpperCase().contains (uppercase)
        }
        return true
    }
}