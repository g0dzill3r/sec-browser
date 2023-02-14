package sec

import com.example.util.ILoggable
import com.github.salomonbrys.kodein.Kodein
import sec.firms.SECAdvisors
import sec.firms.SECFirm
import sec.firms.SECReport
import sec.reps.SECAdvisorReps
import sec.reps.SECIndividual
import sec.reps.SECIndividuals

/**
 * A service that encapsulates access to the most current SEC report.
 */

class SecService (val kodein: Kodein) : ILoggable {
    var firms: SECReport = SECReport ()
        private set

    var individuals: SECIndividuals = SECIndividuals ()
        private set

    init {
        reloadFirms ()
        reloadIndividuals()
    }

    fun firmById (id: String): SECFirm? = firms.firms.find { it.id == id }
    fun individualById (id: String): SECIndividual? = individuals.individuals.find { it.id == id }

    fun reloadFirms (): SECReport {
        val elapsed = timed {
            logger.info("Reloading firms...")
            firms = SECReport ()
            firms = SECAdvisors.load()
            mapIndividuals()
        }
        logger.info ("${firms.firms.size} firms loaded in $elapsed ms")
        return firms
    }

    fun reloadIndividuals (): SECIndividuals {
        val elapsed = timed {
            logger.info("Reloading individuals...")
            individuals = SECIndividuals ()
            individuals = SECAdvisorReps.load()
            mapIndividuals()
        }
        logger.info ("${individuals.individuals.size} individuals loaded in $elapsed ms")
        return individuals
    }

    /**
     * Generates the mapping from the firms to the individuals that are
     * registered as current employees of those firms.
     */

    private fun mapIndividuals () {
        val map = buildMap {
            firms.firms.forEach {
                put (it.id, it)
                it.individuals.clear ()
            }
        }
        individuals.individuals.forEach { indv ->
            indv.currentEmployers.forEach { emp ->
                map[emp.orgPK]?.individuals?.add (indv)
            }
        }
        return
    }
}

// EOF