package sec.reps

import com.example.util.asJsonMap
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement
import sec.*


@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JacksonXmlRootElement(localName = "IAPDFirmIndividualReport")
class SECIndividuals (
    @field:JacksonXmlProperty(isAttribute=true, localName="GenOn")
    val generatedOn: String = "",
) {
    @get:JacksonXmlElementWrapper(localName = "Indvls")
    @get:JacksonXmlProperty(localName = "Indvl")
    var individuals: MutableList<SECIndividual> = mutableListOf ()

    val count: Int
        get () = individuals.size
}

@JacksonXmlRootElement(localName = "Indvl")
data class SECIndividual (
    @JacksonXmlProperty(localName="Info")
    val info: SECIndividualInfo,

) {
    val id: String
        get () = info.indvlPK

    @get:JacksonXmlElementWrapper(localName = "OthrNms")
    @get:JacksonXmlProperty(localName = "OthrNm")
    var otherNms: List<SECIndividualOtherName> = listOf ()

    @get:JacksonXmlElementWrapper(localName = "CrntEmps")
    @get:JacksonXmlProperty(localName = "CrntEmp")
    var currentEmployers: List<SECIndividualCurrentEmployer> = listOf ()

    @get:JacksonXmlElementWrapper(localName = "Exms")
    @get:JacksonXmlProperty(localName = "Exm")
    var exams: List<SECIndividualExam> = listOf ()

    @get:JacksonXmlElementWrapper(localName = "Dsgntns")
    @get:JacksonXmlProperty(localName = "Dsgntn")
    var designation: List<SECIndividualDesignation> = listOf ()

    @get:JacksonXmlElementWrapper(localName = "PrevRgstns")
    @get:JacksonXmlProperty(localName = "PrevRgstns")
    var previousRegistrations: List<SECIndividualPreviousRegistration> = listOf ()

    @get:JacksonXmlElementWrapper(localName = "EmpHss")
    @get:JacksonXmlProperty(localName = "EmpHss")
    var employmentHistory: List<SECIndividualEmploymentHistory> = listOf ()

    @get:JacksonXmlElementWrapper(localName = "OthrBuss")
    @get:JacksonXmlProperty(localName = "OthrBus")
    var otherBusiness: List<SECIndividualOtherBusiness> = listOf ()

    @get:JacksonXmlElementWrapper(localName = "DRPs")
    @get:JacksonXmlProperty(localName = "DRP")
    var drps: List<SECIndividualDRP> = listOf ()

    @get:JsonIgnore
    val json: Any?
        get () = this.asJsonMap
}

@JacksonXmlRootElement (localName="Info")
data class SECIndividualInfo (
    @JacksonXmlProperty(localName="lastNm", isAttribute=true)
    val lastNm: String? = null,
    @JacksonXmlProperty(localName="firstNm", isAttribute=true)
    val firstNm: String? = null,
    @JacksonXmlProperty(localName="midNm", isAttribute=true)
    val midNm: String? = null,
    @JacksonXmlProperty(localName="indvlPK", isAttribute=true)
    val indvlPK: String,
    @JacksonXmlProperty(localName="actvAGReg", isAttribute=true)
    val actvAGReg: String? = null,
    @JacksonXmlProperty(localName="link", isAttribute=true)
    val link: String? = null,
    @JacksonXmlProperty(localName="sufNm", isAttribute=true)
    val sufNm: String? = null,
)

@JacksonXmlRootElement (localName="OthrNm")
data class SECIndividualOtherName (
    @JacksonXmlProperty(localName="lastNm", isAttribute=true)
    val lastNm: String? = null,
    @JacksonXmlProperty(localName="firstNm", isAttribute=true)
    val firstNm: String? = null,
    @JacksonXmlProperty(localName="midNm", isAttribute=true)
    val midNm: String? = null,
    @JacksonXmlProperty(localName="sufNm", isAttribute=true)
    val sufNm: String? = null,
)

@JacksonXmlRootElement (localName="CrntEmp")
data class SECIndividualCurrentEmployer (
    @JacksonXmlProperty(localName="orgNm", isAttribute=true)
    val orgNm: String? = null,
    @JacksonXmlProperty(localName="orgPK", isAttribute=true)
    override val orgPK: String? = null,
    @JacksonXmlProperty(localName="str1", isAttribute=true)
    val str1: String? = null,
    @JacksonXmlProperty(localName="str2", isAttribute=true)
    val str2: String? = null,
    @JacksonXmlProperty(localName="city", isAttribute=true)
    val city: String? = null,
    @JacksonXmlProperty(localName="state", isAttribute=true)
    val state: String? = null,
    @JacksonXmlProperty(localName="cntry", isAttribute=true)
    val cntry: String? = null,
    @JacksonXmlProperty(localName="postlCd", isAttribute=true)
    val postlCd: String? = null,
) : FirmReferencer {
    @get:JacksonXmlElementWrapper(localName = "CrntRgstns")
    @get:JacksonXmlProperty(localName = "CrntRgstns")
    var currentRegistrations: List<SECCurrentRegistration> = listOf ()

    @get:JacksonXmlElementWrapper(localName = "BrnchOfLocs")
    @get:JacksonXmlProperty(localName = "BrnchOfLoc")
    var branchLocations: List<SECBranchLocation> = listOf ()
}

data class SECCurrentRegistration (
    @JacksonXmlProperty(localName="regAuth", isAttribute=true)
    val regAuth: String? = null,
    @JacksonXmlProperty(localName="regCat", isAttribute=true)
    val regCat: String? = null,
    @JacksonXmlProperty(localName="st", isAttribute=true)
    val st: String? = null,
    @JacksonXmlProperty(localName="stDt", isAttribute=true)
    val stDt: String? = null,
)

@JacksonXmlRootElement (localName="BrnchOfLoc")
data class SECBranchLocation (
    @JacksonXmlProperty(localName="str1", isAttribute=true)
    val str1: String? = null,
    @JacksonXmlProperty(localName="str2", isAttribute=true)
    val str2: String? = null,
    @JacksonXmlProperty(localName="city", isAttribute=true)
    val city: String? = null,
    @JacksonXmlProperty(localName="state", isAttribute=true)
    val state: String? = null,
    @JacksonXmlProperty(localName="cntry", isAttribute=true)
    val cntry: String? = null,
    @JacksonXmlProperty(localName="postlCd", isAttribute=true)
    val postlCd: String? = null,
)

@JacksonXmlRootElement (localName="Exm")
data class SECIndividualExam (
    @JacksonXmlProperty(localName="exmCd", isAttribute=true)
    val exmCd: String? = null,
    @JacksonXmlProperty(localName="exmNm", isAttribute=true)
    val exmNm: String? = null,
    @JacksonXmlProperty(localName="exmDt", isAttribute=true)
    val exmDt: String? = null,
)

@JacksonXmlRootElement (localName="Dsgntn")
data class SECIndividualDesignation (
    @JacksonXmlProperty(localName="dsgntnNm", isAttribute=true)
    val dsgntnNm: String? = null,
)

interface FirmReferencer {
    val orgPK: String?
}

@JacksonXmlRootElement (localName="PrevRgstn")
data class SECIndividualPreviousRegistration (
    @JacksonXmlProperty(localName="orgNm", isAttribute=true)
    val orgNm: String? = null,
    @JacksonXmlProperty(localName="orgPK", isAttribute=true)
    override val orgPK: String? = null,
    @JacksonXmlProperty(localName="regBeginDt", isAttribute=true)
    val regBeginDt: String? = null,
    @JacksonXmlProperty(localName="regEndDt", isAttribute=true)
    val regEndDt: String? = null,
): FirmReferencer {
    @get:JacksonXmlElementWrapper(localName = "BrnchOfLocs")
    @get:JacksonXmlProperty(localName = "BrnchOfLoc")
    var branchLocations: List<SECBranchLocation> = listOf ()
}

@JacksonXmlRootElement (localName="EmpHs")
data class SECIndividualEmploymentHistory (
    @JacksonXmlProperty(localName="fromDt", isAttribute=true)
    val fromDt: String? = null,
    @JacksonXmlProperty(localName="toDt", isAttribute=true)
    val toDt: String? = null,
    @JacksonXmlProperty(localName="orgNm", isAttribute=true)
    val orgNm: String? = null,
    @JacksonXmlProperty(localName="city", isAttribute=true)
    val city: String? = null,
    @JacksonXmlProperty(localName="state", isAttribute=true)
    val state: String? = null,
)

@JacksonXmlRootElement (localName="OthrBus")
data class SECIndividualOtherBusiness (
    @JacksonXmlProperty(localName="desc", isAttribute=true)
    val desc: String? = null,
)

@JacksonXmlRootElement (localName="DRP")
data class SECIndividualDRP (
    @JacksonXmlProperty(localName="hasRegAction", isAttribute=true)
    val hasRegAction: String? = null,
    @JacksonXmlProperty(localName="hasCriminal", isAttribute=true)
    val hasCriminal: String? = null,
    @JacksonXmlProperty(localName="hasBankrupt", isAttribute=true)
    val hasBankrupt: String? = null,
    @JacksonXmlProperty(localName="hasCivilJudc", isAttribute=true)
    val hasCivilJudc: String? = null,
    @JacksonXmlProperty(localName="hasBond", isAttribute=true)
    val hasBond: String? = null,
    @JacksonXmlProperty(localName="hasJudgment", isAttribute=true)
    val hasJudgment: String? = null,
    @JacksonXmlProperty(localName="hasInvstgn", isAttribute=true)
    val hasInvstgn: String? = null,
    @JacksonXmlProperty(localName="hasCustComp", isAttribute=true)
    val hasCustComp: String? = null,
    @JacksonXmlProperty(localName="hasTermination", isAttribute=true)
    val hasTermination: String? = null,
)

// EOF