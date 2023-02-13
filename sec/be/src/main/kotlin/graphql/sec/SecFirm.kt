package graphql.sec

import sec.firms.SECFirm

data class SECFirmFindRequest (
    val skip: Int = 0,
    val limit: Int = -1,
    val filters: SECFirmFilters? = null
)

data class SECFirmFilters (
    val id: String?,
    val name: String?,
    val secId: String?,
    val state: String?
) {
    fun match (firm: SECFirm): Boolean {
        id?.let {
            if (firm.id != it) {
                return false
            }
        }
        secId?.let {
            if (firm.secId != it) {
                return false
            }
        }
        name?.let {
            val uppercase = it.toUpperCase()
            return firm.info.busNm.contains (uppercase)
        }
        state?.let {
            if (firm.mainAddr?.state != it) {
                return false
            }
        }
        return true
    }
}

// EOF