package graphql.sec

import com.example.config.config
import com.example.util.ILoggable
import com.example.util.inc
import com.example.util.window
import com.github.salomonbrys.kodein.instance
import graphql.schema.DataFetcher
import graphql.schema.idl.RuntimeWiring
import graphql.schema.idl.TypeRuntimeWiring
import graphql.Wireable
import sec.firms.SECFirm
import sec.firms.SECReport
import sec.SecService
import sec.reps.FirmReferencer
import sec.reps.SECIndividual
import sec.reps.SECIndividuals



object SecFetchers : ILoggable, Wireable {
    private val secService: SecService by lazy {
        config.instance ()
    }

    override fun wire(wiring: RuntimeWiring.Builder) {
        wiring
            .type(
                TypeRuntimeWiring.newTypeWiring("Mutation")
                    .dataFetcher ("reloadFirms", reloadFirms)
                    .dataFetcher ("reloadIndividuals", reloadIndividuals)
            )
            .type(
                TypeRuntimeWiring.newTypeWiring("Query")
                    // Firm operations
                    .dataFetcher ("secReport", report)
                    .dataFetcher ("secReportByState", byState)
                    .dataFetcher ("findFirms", find)
                    .dataFetcher ("firmById", byId)
                    // Individual operations
                    .dataFetcher ("secIndividuals", individuals)
                    .dataFetcher ("findIndividuals", findIndividuals)
                    .dataFetcher ("individualById", individualById)
            )
            .type (
                TypeRuntimeWiring.newTypeWiring("SECIndividualCurrentEmployer")
                    .dataFetcher ("firm", byOrgPK)
            )
            .type (
                TypeRuntimeWiring.newTypeWiring("SECIndividualPreviousRegistration")
                    .dataFetcher ("firm", byOrgPK)
            )
    }

    private val byOrgPK: DataFetcher<SECFirm?>
        get () = DataFetcher { dfe ->
            val ref = dfe.getSource<FirmReferencer>();
            val id = ref.orgPK as String
            secService.firmById (id)
        }

    private val individualById: DataFetcher<SECIndividual>
        get () = DataFetcher<SECIndividual> { dfe ->
            val id = dfe.getArgument<String> ("id")
            secService.individualById (id)
        }

    private val findIndividuals: DataFetcher<FetchResults<SECIndividual>>
        get () = DataFetcher<FetchResults<SECIndividual>> { dfe ->
            val req = dfe.getObjectArgument<SECIndividualFindRequest> ("req")
            var individuals: List<SECIndividual> = secService.individuals.individuals
            if (req.filters != null) {
                individuals = individuals.filter {
                    req.filters.match (it)
                }
            }
            val total = individuals.size
            val subset = individuals.window (req.skip, req.limit)
            FetchResults (req.skip, req.limit, total, subset)
        }

    private val individuals:  DataFetcher<SECIndividuals>
        get () = DataFetcher {
            secService.individuals
        }

    private val byId: DataFetcher<SECFirm>
        get () = DataFetcher<SECFirm> { dfe ->
            val id = dfe.getArgument<String> ("id")
            secService.firmById (id)
        }

    private val find: DataFetcher<FetchResults<SECFirm>>
        get () = DataFetcher<FetchResults<SECFirm>> { dfe ->
            val req = dfe.getObjectArgument<SECFirmFindRequest> ("req")
            var firms = secService.firms.firms
            if (req.filters != null) {
                firms = firms.filter { req.filters.match(it) }
            }
            val total = firms.size
            val subset = firms.window (req.skip, req.limit)
            FetchResults (req.skip, req.limit, total, subset)
        }

    private val report:  DataFetcher<SECReport>
        get () = DataFetcher {
            secService.firms
        }

    private val reloadFirms: DataFetcher<SECReport>
        get () = DataFetcher {
            secService.reloadFirms ()
        }

    private val reloadIndividuals: DataFetcher<SECIndividuals>
        get () = DataFetcher {
            secService.reloadIndividuals ()
        }

    private val byState: DataFetcher<Map<String, Int>>
        get () = DataFetcher {
            val report = secService.firms
            buildMap {
                report.firms.forEach { firm ->
                    firm.mainAddr?.state?.let {
                        inc (it)
                    }
                }
            }
        }
}

// EOF