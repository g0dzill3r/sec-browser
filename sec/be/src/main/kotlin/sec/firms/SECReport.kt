package sec.firms

import com.example.util.asJsonMap
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonRootName
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement
import sec.reps.SECIndividual

enum class YesNo { N, Y }

@JacksonXmlRootElement(localName = "Firm")
data class SECFirm (
    @JacksonXmlProperty(localName="Info")
    val info: SECFirmInfo,
    @JacksonXmlProperty(localName="MainAddr")
    val mainAddr: SECFirmAddress?,
    @JacksonXmlProperty(localName="MailingAddr")
    val mailingAddr: SECFirmAddress?,
    @JacksonXmlProperty(localName="Rgstn")
    val registration: SECFirmRegistration,
    @JacksonXmlProperty(localName="NoticeFiled")
    val noticeFiled: SECFirmNoticeFiled,
    @JacksonXmlProperty(localName="Filing")
    val filing: SECFirmFiling,
    @JacksonXmlProperty(localName="FormInfo")
    val formInfo: SECFirmFormInfo,
) {
    val id: String
        get () = info?.firmCrdNb ?: "id?"

    val secId: String
        get () = info?.secNb ?: "secId?"

    val individuals = mutableListOf<SECIndividual> ()

    val individualsCount: Int
        get () = individuals.size

    @get:JsonIgnore
    val json: Any?
        get () = this.asJsonMap
}

@JacksonXmlRootElement(localName="")
data class SECFirmRegistration (
    @JacksonXmlProperty(localName="FirmType", isAttribute=true)
    val firmType: String,
    @JacksonXmlProperty(localName="St", isAttribute=true)
    val state: String,
    @JacksonXmlProperty(localName="Dt", isAttribute=true)
    val date: String
)

@JacksonXmlRootElement(localName="NoticeFiled")
class SECFirmNoticeFiled {
    @JacksonXmlElementWrapper(useWrapping = false)
//    @field:JacksonXmlProperty(localName = "State")
    var states = listOf<SECFirmNoticeFiledState> ()
}

@JacksonXmlRootElement(localName="FormInfo")
class SECFirmFormInfo (
    @JacksonXmlProperty(localName="Part1A")
    val part1A: Part1A
)

@JacksonXmlRootElement(localName="Part1A")
class Part1A (
    @JacksonXmlProperty(localName="Item1")
    val item1: Item1? = null,

    @JacksonXmlProperty(localName="Item2A")
    val item2a: Item2A? = null,
    @JacksonXmlProperty(localName="Item2B")
    val item2b: Item2B? = null,

    @JacksonXmlProperty(localName="Item3A")
    val item3a: Item3A? = null,
    @JacksonXmlProperty(localName="Item3B")
    val item3b: Item3B? = null,
    @JacksonXmlProperty(localName="Item3C")
    val item3c: Item3C? = null,

    @JacksonXmlProperty(localName="Item5A")
    val item5a: Item5A? = null,
    @JacksonXmlProperty(localName="Item5B")
    val item5b: Item5B? = null,
    @JacksonXmlProperty(localName="Item5C")
    val item5c: Item5C? = null,
    @JacksonXmlProperty(localName="Item5D")
    val item5d: Item5D? = null,
    @JacksonXmlProperty(localName="Item5E")
    val item5e: Item5E? = null,
    @JacksonXmlProperty(localName="Item5F")
    val item5f: Item5F? = null,
    @JacksonXmlProperty(localName="Item5G")
    val item5g: Item5G? = null,
    @JacksonXmlProperty(localName="Item5H")
    val item5h: Item5H? = null,
    @JacksonXmlProperty(localName="Item5I")
    val item5i: Item5I? = null,
    @JacksonXmlProperty(localName="Item5J")
    val item5j: Item5J? = null,
    @JacksonXmlProperty(localName="Item5K")
    val item5k: Item5K? = null,
    @JacksonXmlProperty(localName="Item5L")
    val item5l: Item5L? = null,

    @JacksonXmlProperty(localName="Item6A")
    val item6a: Item6A? = null,
    @JacksonXmlProperty(localName="Item6B")
    val item6b: Item6B? = null,

    @JacksonXmlProperty(localName="Item7A")
    val item7a: Item7A? = null,
    @JacksonXmlProperty(localName="Item7B")
    val item7b: Item7B? = null,

    @JacksonXmlProperty(localName="Item8A")
    val item8a: Item8A? = null,
    @JacksonXmlProperty(localName="Item8B")
    val item8b: Item8B? = null,
    @JacksonXmlProperty(localName="Item8C")
    val item8c: Item8C? = null,
    @JacksonXmlProperty(localName="Item8D")
    val item8d: Item8D? = null,
    @JacksonXmlProperty(localName="Item8E")
    val item8e: Item8E? = null,
    @JacksonXmlProperty(localName="Item8F")
    val item8f: Item8F? = null,
    @JacksonXmlProperty(localName="Item8G")
    val item8g: Item8G? = null,
    @JacksonXmlProperty(localName="Item8H")
    val item8h: Item8H? = null,
    @JacksonXmlProperty(localName="Item8I")
    val item8i: Item8I? = null,

    @JacksonXmlProperty(localName="Item9A")
    val item9a: Item9A? = null,
    @JacksonXmlProperty(localName="Item9B")
    val item9b: Item9B? = null,
    @JacksonXmlProperty(localName="Item9C")
    val item9c: Item9C? = null,
    @JacksonXmlProperty(localName="Item9D")
    val item9d: Item9D? = null,
    @JacksonXmlProperty(localName="Item9E")
    val item9e: Item9E? = null,
    @JacksonXmlProperty(localName="Item9F")
    val item9f: Item9F? = null,

    @JacksonXmlProperty(localName="Item10A")
    val item10a: Item10A? = null,

    @JacksonXmlProperty(localName="Item11")
    val item11: Item11? = null,
    @JacksonXmlProperty(localName="Item11A")
    val item11a: Item11A? = null,
    @JacksonXmlProperty(localName="Item11B")
    val item11b: Item11B? = null,
    @JacksonXmlProperty(localName="Item11C")
    val item11c: Item11C? = null,
    @JacksonXmlProperty(localName="Item11D")
    val item11d: Item11D? = null,
    @JacksonXmlProperty(localName="Item11E")
    val item11e: Item11E? = null,
    @JacksonXmlProperty(localName="Item11F")
    val item11f: Item11F? = null,
    @JacksonXmlProperty(localName="Item11G")
    val item11g: Item11G? = null,
    @JacksonXmlProperty(localName="Item11H")
    val item11h: Item11H? = null,
)


@JacksonXmlRootElement(localName="Item1")
data class Item1 (
    @JacksonXmlProperty(localName="Q1F5", isAttribute=true)
    val q1f5: String? = null,
    @JacksonXmlProperty(localName="Q1I", isAttribute=true)
    val q1i: YesNo? = null,
    @JacksonXmlProperty(localName="Q1M", isAttribute=true)
    val q1m: YesNo? = null,
    @JacksonXmlProperty(localName="Q1N", isAttribute=true)
    val q1n: YesNo? = null,
    @JacksonXmlProperty(localName="Q1O", isAttribute=true)
    val q10: YesNo? = null,
    @JacksonXmlProperty(localName="Q1ODesc", isAttribute=true)
    val q10desc: String? = null,
    @JacksonXmlProperty(localName="Q1P", isAttribute=true)
    val q1p: String? = null,
) {
    @JacksonXmlElementWrapper(localName = "Webaddrs")
    @JacksonXmlProperty(localName = "Webaddr")
    val webaddrs: List<String> = listOf ()
}

@JacksonXmlRootElement(localName="Item2A")
data class Item2A (
    @JacksonXmlProperty(localName="Q2A1", isAttribute=true)
    val q2a1: YesNo? = null,
    @JacksonXmlProperty(localName="Q2A2", isAttribute=true)
    val q2a2: YesNo? = null,
    @JacksonXmlProperty(localName="Q2A3", isAttribute=true)
    val q2a3: YesNo? = null,
    @JacksonXmlProperty(localName="Q2A4", isAttribute=true)
    val q2a4: YesNo? = null,
    @JacksonXmlProperty(localName="Q2A5", isAttribute=true)
    val q2a5: YesNo? = null,
    @JacksonXmlProperty(localName="Q2A6", isAttribute=true)
    val q2a6: YesNo? = null,
    @JacksonXmlProperty(localName="Q2A7", isAttribute=true)
    val q2a7: YesNo? = null,
    @JacksonXmlProperty(localName="Q2A8", isAttribute=true)
    val q2a8: YesNo? = null,
    @JacksonXmlProperty(localName="Q2A9", isAttribute=true)
    val q2a9: YesNo? = null,
    @JacksonXmlProperty(localName="Q2A10", isAttribute=true)
    val q2a10: YesNo? = null,
    @JacksonXmlProperty(localName="Q2A11", isAttribute=true)
    val q2a11: YesNo? = null,
    @JacksonXmlProperty(localName="Q2A12", isAttribute=true)
    val q2a12: YesNo? = null,
    @JacksonXmlProperty(localName="Q2A13", isAttribute=true)
    val q2a13: YesNo? = null,
)

@JacksonXmlRootElement (localName="Item2B")
data class Item2B (
    @JacksonXmlProperty(localName="Q2B1", isAttribute=true)
    val q2B1: YesNo? = null,
    @JacksonXmlProperty(localName="Q2B2", isAttribute=true)
    val q2B2: YesNo? = null,
    @JacksonXmlProperty(localName="Q2B3", isAttribute=true)
    val q2B3: YesNo? = null,
)

@JacksonXmlRootElement (localName="Item3A")
data class Item3A (
    @JacksonXmlProperty(localName="OrgFormNm", isAttribute=true)
    val orgFormNm: String? = null,
    @JacksonXmlProperty(localName="OrgFormOthNm", isAttribute=true)
    val orgFormOthNm: String? = null,
)

@JacksonXmlRootElement (localName="Item3B")
data class Item3B (
    @JacksonXmlProperty(localName="Q3B", isAttribute=true)
    val q3B: String? = null,
)

@JacksonXmlRootElement (localName="Item3C")
data class Item3C (
    @JacksonXmlProperty(localName="StateCD", isAttribute=true)
    val stateCD: String? = null,
    @JacksonXmlProperty(localName="CntryNm", isAttribute=true)
    val cntryNm: String? = null,
)


@JacksonXmlRootElement (localName="Item5A")
data class Item5A (
    @JacksonXmlProperty(localName="TtlEmp", isAttribute=true)
    val ttlEmp: String? = null,
)

@JacksonXmlRootElement (localName="Item5B")
data class Item5B (
    @JacksonXmlProperty(localName="Q5B1", isAttribute=true)
    val q5B1: String? = null,
    @JacksonXmlProperty(localName="Q5B2", isAttribute=true)
    val q5B2: String? = null,
    @JacksonXmlProperty(localName="Q5B3", isAttribute=true)
    val q5B3: String? = null,
    @JacksonXmlProperty(localName="Q5B4", isAttribute=true)
    val q5B4: String? = null,
    @JacksonXmlProperty(localName="Q5B5", isAttribute=true)
    val q5B5: String? = null,
    @JacksonXmlProperty(localName="Q5B6", isAttribute=true)
    val q5B6: String? = null,
)

@JacksonXmlRootElement (localName="Item5C")
data class Item5C (
    @JacksonXmlProperty(localName="Q5C1", isAttribute=true)
    val q5C1: String? = null,
    @JacksonXmlProperty(localName="Q5C2", isAttribute=true)
    val q5C2: String? = null,
    @JacksonXmlProperty(localName="Q5C1Oth", isAttribute=true)
    val q5C1Oth: String? = null,
)

@JacksonXmlRootElement (localName="Item5D")
data class Item5D (
    @JacksonXmlProperty(localName="Q5D1A", isAttribute=true)
    val q5D1A: String? = null,
    @JacksonXmlProperty(localName="Q5D1B", isAttribute=true)
    val q5D1B: String? = null,
    @JacksonXmlProperty(localName="Q5D1C", isAttribute=true)
    val q5D1C: String? = null,
    @JacksonXmlProperty(localName="Q5D1D", isAttribute=true)
    val q5D1D: String? = null,
    @JacksonXmlProperty(localName="Q5D1E", isAttribute=true)
    val q5D1E: String? = null,
    @JacksonXmlProperty(localName="Q5D1F", isAttribute=true)
    val q5D1F: String? = null,
    @JacksonXmlProperty(localName="Q5D1G", isAttribute=true)
    val q5D1G: String? = null,
    @JacksonXmlProperty(localName="Q5D1H", isAttribute=true)
    val q5D1H: String? = null,
    @JacksonXmlProperty(localName="Q5D1I", isAttribute=true)
    val q5D1I: String? = null,
    @JacksonXmlProperty(localName="Q5D1J", isAttribute=true)
    val q5D1J: String? = null,
    @JacksonXmlProperty(localName="Q5D1K", isAttribute=true)
    val q5D1K: String? = null,
    @JacksonXmlProperty(localName="Q5D1L", isAttribute=true)
    val q5D1L: String? = null,
    @JacksonXmlProperty(localName="Q5D1M", isAttribute=true)
    val q5D1M: String? = null,

    @JacksonXmlProperty(localName="Q5D2A", isAttribute=true)
    val q5D2A: String? = null,
    @JacksonXmlProperty(localName="Q5D2B", isAttribute=true)
    val q5D2B: String? = null,
    @JacksonXmlProperty(localName="Q5D2C", isAttribute=true)
    val q5D2C: String? = null,
    @JacksonXmlProperty(localName="Q5D2D", isAttribute=true)
    val q5D2D: String? = null,
    @JacksonXmlProperty(localName="Q5D2E", isAttribute=true)
    val q5D2E: String? = null,
    @JacksonXmlProperty(localName="Q5D2F", isAttribute=true)
    val q5D2F: String? = null,
    @JacksonXmlProperty(localName="Q5D2G", isAttribute=true)
    val q5D2G: String? = null,
    @JacksonXmlProperty(localName="Q5D2H", isAttribute=true)
    val q5D2H: String? = null,
    @JacksonXmlProperty(localName="Q5D2I", isAttribute=true)
    val q5D2I: String? = null,
    @JacksonXmlProperty(localName="Q5D2J", isAttribute=true)
    val q5D2J: String? = null,
    @JacksonXmlProperty(localName="Q5D2K", isAttribute=true)
    val q5D2K: String? = null,
    @JacksonXmlProperty(localName="Q5D2L", isAttribute=true)
    val q5D2L: String? = null,
    @JacksonXmlProperty(localName="Q5D2M", isAttribute=true)
    val q5D2M: String? = null,

    @JacksonXmlProperty(localName="Q5DA1", isAttribute=true)
    val q5DA1: String? = null,
    @JacksonXmlProperty(localName="Q5DA2", isAttribute=true)
    val q5DA2: String? = null,
    @JacksonXmlProperty(localName="Q5DA3", isAttribute=true)
    val q5DA3: String? = null,

    @JacksonXmlProperty(localName="Q5DB1", isAttribute=true)
    val q5DB1: String? = null,
    @JacksonXmlProperty(localName="Q5DB2", isAttribute=true)
    val q5DB2: String? = null,
    @JacksonXmlProperty(localName="Q5DB3", isAttribute=true)
    val q5DB3: String? = null,

    @JacksonXmlProperty(localName="Q5DC1", isAttribute=true)
    val q5DC1: String? = null,
    @JacksonXmlProperty(localName="Q5DC2", isAttribute=true)
    val q5DC2: String? = null,
    @JacksonXmlProperty(localName="Q5DC3", isAttribute=true)
    val q5DC3: String? = null,

    @JacksonXmlProperty(localName="Q5DD1", isAttribute=true)
    val q5DD1: String? = null,
    @JacksonXmlProperty(localName="Q5DD2", isAttribute=true)
    val q5DD2: String? = null,
    @JacksonXmlProperty(localName="Q5DD3", isAttribute=true)
    val q5DD3: String? = null,

    @JacksonXmlProperty(localName="Q5DE1", isAttribute=true)
    val q5DE1: String? = null,
    @JacksonXmlProperty(localName="Q5DE2", isAttribute=true)
    val q5DE2: String? = null,
    @JacksonXmlProperty(localName="Q5DE3", isAttribute=true)
    val q5DE3: String? = null,

    @JacksonXmlProperty(localName="Q5DF1", isAttribute=true)
    val q5DF1: String? = null,
    @JacksonXmlProperty(localName="Q5DF2", isAttribute=true)
    val q5DF2: String? = null,
    @JacksonXmlProperty(localName="Q5DF3", isAttribute=true)
    val q5DF3: String? = null,

    @JacksonXmlProperty(localName="Q5DG1", isAttribute=true)
    val q5DG1: String? = null,
    @JacksonXmlProperty(localName="Q5DG2", isAttribute=true)
    val q5DG2: String? = null,
    @JacksonXmlProperty(localName="Q5DG3", isAttribute=true)
    val q5DG3: String? = null,

    @JacksonXmlProperty(localName="Q5DH1", isAttribute=true)
    val q5DH1: String? = null,
    @JacksonXmlProperty(localName="Q5DH2", isAttribute=true)
    val q5DH2: String? = null,
    @JacksonXmlProperty(localName="Q5DH3", isAttribute=true)
    val q5DH3: String? = null,

    @JacksonXmlProperty(localName="Q5DI1", isAttribute=true)
    val q5DI1: String? = null,
    @JacksonXmlProperty(localName="Q5DI2", isAttribute=true)
    val q5DI2: String? = null,
    @JacksonXmlProperty(localName="Q5DI3", isAttribute=true)
    val q5DI3: String? = null,

    @JacksonXmlProperty(localName="Q5DJ1", isAttribute=true)
    val q5DJ1: String? = null,
    @JacksonXmlProperty(localName="Q5DJ2", isAttribute=true)
    val q5DJ2: String? = null,
    @JacksonXmlProperty(localName="Q5DJ3", isAttribute=true)
    val q5DJ3: String? = null,

    @JacksonXmlProperty(localName="Q5DK1", isAttribute=true)
    val q5DK1: String? = null,
    @JacksonXmlProperty(localName="Q5DK2", isAttribute=true)
    val q5DK2: String? = null,
    @JacksonXmlProperty(localName="Q5DK3", isAttribute=true)
    val q5DK3: String? = null,

    @JacksonXmlProperty(localName="Q5DL1", isAttribute=true)
    val q5DL1: String? = null,
    @JacksonXmlProperty(localName="Q5DL2", isAttribute=true)
    val q5DL2: String? = null,
    @JacksonXmlProperty(localName="Q5DL3", isAttribute=true)
    val q5DL3: String? = null,

    @JacksonXmlProperty(localName="Q5DM1", isAttribute=true)
    val q5DM1: String? = null,
    @JacksonXmlProperty(localName="Q5DM2", isAttribute=true)
    val q5DM2: String? = null,
    @JacksonXmlProperty(localName="Q5DM3", isAttribute=true)
    val q5DM3: String? = null,

    @JacksonXmlProperty(localName="Q5DN1", isAttribute=true)
    val q5DN1: String? = null,
    @JacksonXmlProperty(localName="Q5DN2", isAttribute=true)
    val q5DN2: String? = null,
    @JacksonXmlProperty(localName="Q5DN3", isAttribute=true)
    val q5DN3: String? = null,
    @JacksonXmlProperty(localName="Q5DN3Oth", isAttribute=true)
    val q5DN3Oth: String? = null,

    )

@JacksonXmlRootElement (localName="Item5E")
data class Item5E (
    @JacksonXmlProperty(localName="Q5E1", isAttribute=true)
    val q5E1: String? = null,
    @JacksonXmlProperty(localName="Q5E2", isAttribute=true)
    val q5E2: String? = null,
    @JacksonXmlProperty(localName="Q5E3", isAttribute=true)
    val q5E3: String? = null,
    @JacksonXmlProperty(localName="Q5E4", isAttribute=true)
    val q5E4: String? = null,
    @JacksonXmlProperty(localName="Q5E5", isAttribute=true)
    val q5E5: String? = null,
    @JacksonXmlProperty(localName="Q5E6", isAttribute=true)
    val q5E6: String? = null,
    @JacksonXmlProperty(localName="Q5E7", isAttribute=true)
    val q5E7: String? = null,
    @JacksonXmlProperty(localName="Q5E7Oth", isAttribute=true)
    val q5E7Oth: String? = null,
)

@JacksonXmlRootElement (localName="Item5F")
data class Item5F (
    @JacksonXmlProperty(localName="Q5F1", isAttribute=true)
    val q5F1: String? = null,
    @JacksonXmlProperty(localName="Q5F2A", isAttribute=true)
    val q5F2A: String? = null,
    @JacksonXmlProperty(localName="Q5F2B", isAttribute=true)
    val q5F2B: String? = null,
    @JacksonXmlProperty(localName="Q5F2C", isAttribute=true)
    val q5F2C: String? = null,
    @JacksonXmlProperty(localName="Q5F2D", isAttribute=true)
    val q5F2D: String? = null,
    @JacksonXmlProperty(localName="Q5F2E", isAttribute=true)
    val q5F2E: String? = null,
    @JacksonXmlProperty(localName="Q5F2F", isAttribute=true)
    val q5F2F: String? = null,
    @JacksonXmlProperty(localName="Q5F3", isAttribute=true)
    val q5F3: String? = null,
)

@JacksonXmlRootElement (localName="Item5G")
data class Item5G (
    @JacksonXmlProperty(localName="Q5G1", isAttribute=true)
    val q5G1: String? = null,
    @JacksonXmlProperty(localName="Q5G2", isAttribute=true)
    val q5G2: String? = null,
    @JacksonXmlProperty(localName="Q5G3", isAttribute=true)
    val q5G3: String? = null,
    @JacksonXmlProperty(localName="Q5G4", isAttribute=true)
    val q5G4: String? = null,
    @JacksonXmlProperty(localName="Q5G5", isAttribute=true)
    val q5G5: String? = null,
    @JacksonXmlProperty(localName="Q5G6", isAttribute=true)
    val q5G6: String? = null,
    @JacksonXmlProperty(localName="Q5G7", isAttribute=true)
    val q5G7: String? = null,
    @JacksonXmlProperty(localName="Q5G8", isAttribute=true)
    val q5G8: String? = null,
    @JacksonXmlProperty(localName="Q5G9", isAttribute=true)
    val q5G9: String? = null,
    @JacksonXmlProperty(localName="Q5G10", isAttribute=true)
    val q5G10: String? = null,
    @JacksonXmlProperty(localName="Q5G11", isAttribute=true)
    val q5G11: String? = null,
    @JacksonXmlProperty(localName="Q5G12", isAttribute=true)
    val q5G12: String? = null,
    @JacksonXmlProperty(localName="Q5G12Oth", isAttribute=true)
    val q5G120th: String? = null,

    )

@JacksonXmlRootElement (localName="Item5H")
data class Item5H (
    @JacksonXmlProperty(localName="Q5H", isAttribute=true)
    val q5H: String? = null,
    @JacksonXmlProperty(localName="Q5HMT500", isAttribute=true)
    val q5HMT500: String? = null,
)

@JacksonXmlRootElement (localName="Item5I")
data class Item5I (
    @JacksonXmlProperty(localName="Q5I1", isAttribute=true)
    val q5I1: String? = null,
    @JacksonXmlProperty(localName="Q5I2A", isAttribute=true)
    val q5I2A: String? = null,
    @JacksonXmlProperty(localName="Q5I2B", isAttribute=true)
    val q5I2B: String? = null,
    @JacksonXmlProperty(localName="Q5I2C", isAttribute=true)
    val q5I2C: String? = null,
)

@JacksonXmlRootElement (localName="Item5J")
data class Item5J (
    @JacksonXmlProperty(localName="Q5J1", isAttribute=true)
    val q5J1: String? = null,
    @JacksonXmlProperty(localName="Q5J2", isAttribute=true)
    val q5J2: String? = null,
)

@JacksonXmlRootElement (localName="Item5K")
data class Item5K (
    @JacksonXmlProperty(localName="Q5K1", isAttribute=true)
    val q5K1: String? = null,
    @JacksonXmlProperty(localName="Q5K2", isAttribute=true)
    val q5K2: String? = null,
    @JacksonXmlProperty(localName="Q5K3", isAttribute=true)
    val q5K3: String? = null,
    @JacksonXmlProperty(localName="Q5K4", isAttribute=true)
    val q5K4: String? = null,
)

@JacksonXmlRootElement (localName="Item5L")
data class Item5L (
    @JacksonXmlProperty(localName="Q5L1A", isAttribute=true)
    val q5L1A: String? = null,
    @JacksonXmlProperty(localName="Q5L1B", isAttribute=true)
    val q5L1B: String? = null,
    @JacksonXmlProperty(localName="Q5L1C", isAttribute=true)
    val q5L1C: String? = null,
    @JacksonXmlProperty(localName="Q5L1D", isAttribute=true)
    val q5L1D: String? = null,
    @JacksonXmlProperty(localName="Q5L1E", isAttribute=true)
    val q5L1E: String? = null,
    @JacksonXmlProperty(localName="Q5L2", isAttribute=true)
    val q5L2: String? = null,
    @JacksonXmlProperty(localName="Q5L3", isAttribute=true)
    val q5L3: String? = null,
    @JacksonXmlProperty(localName="Q5L4", isAttribute=true)
    val q5L4: String? = null,
)

@JacksonXmlRootElement (localName="Item6A")
data class Item6A (
    @JacksonXmlProperty(localName="Q6A1", isAttribute=true)
    val q6A1: String? = null,
    @JacksonXmlProperty(localName="Q6A2", isAttribute=true)
    val q6A2: String? = null,
    @JacksonXmlProperty(localName="Q6A3", isAttribute=true)
    val q6A3: String? = null,
    @JacksonXmlProperty(localName="Q6A4", isAttribute=true)
    val q6A4: String? = null,
    @JacksonXmlProperty(localName="Q6A5", isAttribute=true)
    val q6A5: String? = null,
    @JacksonXmlProperty(localName="Q6A6", isAttribute=true)
    val q6A6: String? = null,
    @JacksonXmlProperty(localName="Q6A7", isAttribute=true)
    val q6A7: String? = null,
    @JacksonXmlProperty(localName="Q6A8", isAttribute=true)
    val q6A8: String? = null,
    @JacksonXmlProperty(localName="Q6A9", isAttribute=true)
    val q6A9: String? = null,
    @JacksonXmlProperty(localName="Q6A10", isAttribute=true)
    val q6A10: String? = null,
    @JacksonXmlProperty(localName="Q6A11", isAttribute=true)
    val q6A11: String? = null,
    @JacksonXmlProperty(localName="Q6A12", isAttribute=true)
    val q6A12: String? = null,
    @JacksonXmlProperty(localName="Q6A13", isAttribute=true)
    val q6A13: String? = null,
    @JacksonXmlProperty(localName="Q6A14", isAttribute=true)
    val q6A14: String? = null,
    @JacksonXmlProperty(localName="Q6A14Oth", isAttribute=true)
    val q6A14Oth: String? = null,
)

@JacksonXmlRootElement (localName="Item6B")
data class Item6B (
    @JacksonXmlProperty(localName="Q6B1", isAttribute=true)
    val q6B1: String? = null,
    @JacksonXmlProperty(localName="Q6B2", isAttribute=true)
    val q6B2: String? = null,
    @JacksonXmlProperty(localName="Q6B3", isAttribute=true)
    val q6B3: String? = null,
)

@JacksonXmlRootElement (localName="Item7A")
data class Item7A (
    @JacksonXmlProperty(localName="Q7A1", isAttribute=true)
    val q7A1: String? = null,
    @JacksonXmlProperty(localName="Q7A2", isAttribute=true)
    val q7A2: String? = null,
    @JacksonXmlProperty(localName="Q7A3", isAttribute=true)
    val q7A3: String? = null,
    @JacksonXmlProperty(localName="Q7A4", isAttribute=true)
    val q7A4: String? = null,
    @JacksonXmlProperty(localName="Q7A5", isAttribute=true)
    val q7A5: String? = null,
    @JacksonXmlProperty(localName="Q7A6", isAttribute=true)
    val q7A6: String? = null,
    @JacksonXmlProperty(localName="Q7A7", isAttribute=true)
    val q7A7: String? = null,
    @JacksonXmlProperty(localName="Q7A8", isAttribute=true)
    val q7A8: String? = null,
    @JacksonXmlProperty(localName="Q7A9", isAttribute=true)
    val q7A9: String? = null,
    @JacksonXmlProperty(localName="Q7A10", isAttribute=true)
    val q7A10: String? = null,
    @JacksonXmlProperty(localName="Q7A11", isAttribute=true)
    val q7A11: String? = null,
    @JacksonXmlProperty(localName="Q7A12", isAttribute=true)
    val q7A12: String? = null,
    @JacksonXmlProperty(localName="Q7A13", isAttribute=true)
    val q7A13: String? = null,
    @JacksonXmlProperty(localName="Q7A14", isAttribute=true)
    val q7A14: String? = null,
    @JacksonXmlProperty(localName="Q7A15", isAttribute=true)
    val q7A15: String? = null,
    @JacksonXmlProperty(localName="Q7A16", isAttribute=true)
    val q7A16: String? = null,
)

@JacksonXmlRootElement (localName="Item7B")
data class Item7B (
    @JacksonXmlProperty(localName="Q7B", isAttribute=true)
    val q7B: String? = null,
)

@JacksonXmlRootElement (localName="Item8A")
data class Item8A (
    @JacksonXmlProperty(localName="Q8A1", isAttribute=true)
    val q8A1: String? = null,
    @JacksonXmlProperty(localName="Q8A2", isAttribute=true)
    val q8A2: String? = null,
    @JacksonXmlProperty(localName="Q8A3", isAttribute=true)
    val q8A3: String? = null,
)

@JacksonXmlRootElement (localName="Item8B")
data class Item8B (
    @JacksonXmlProperty(localName="Q8B1", isAttribute=true)
    val q8B1: String? = null,
    @JacksonXmlProperty(localName="Q8B2", isAttribute=true)
    val q8B2: String? = null,
    @JacksonXmlProperty(localName="Q8B3", isAttribute=true)
    val q8B3: String? = null,
)

@JacksonXmlRootElement (localName="Item8C")
data class Item8C (
    @JacksonXmlProperty(localName="Q8C1", isAttribute=true)
    val q8C1: String? = null,
    @JacksonXmlProperty(localName="Q8C2", isAttribute=true)
    val q8C2: String? = null,
    @JacksonXmlProperty(localName="Q8C3", isAttribute=true)
    val q8C3: String? = null,
    @JacksonXmlProperty(localName="Q8C4", isAttribute=true)
    val q8C4: String? = null,
)

@JacksonXmlRootElement (localName="Item8D")
data class Item8D (
    @JacksonXmlProperty(localName="Q8D", isAttribute=true)
    val q8D: String? = null,
)

@JacksonXmlRootElement (localName="Item8E")
data class Item8E (
    @JacksonXmlProperty(localName="Q8E", isAttribute=true)
    val q8E: String? = null,
)

@JacksonXmlRootElement (localName="Item8F")
data class Item8F (
    @JacksonXmlProperty(localName="Q8F", isAttribute=true)
    val q8F: String? = null,
)

@JacksonXmlRootElement (localName="Item8G")
data class Item8G (
    @JacksonXmlProperty(localName="Q8G1", isAttribute=true)
    val q8G1: String? = null,
    @JacksonXmlProperty(localName="Q8G2", isAttribute=true)
    val q8G2: String? = null,
)

@JacksonXmlRootElement (localName="Item8H")
data class Item8H (
    @JacksonXmlProperty(localName="Q8H", isAttribute=true)
    val q8H: String? = null,
    @JacksonXmlProperty(localName="Q8H1", isAttribute=true)
    val q8H1: String? = null,
    @JacksonXmlProperty(localName="Q8H2", isAttribute=true)
    val q8H2: String? = null,
)

@JacksonXmlRootElement (localName="Item8I")
data class Item8I (
    @JacksonXmlProperty(localName="Q8I", isAttribute=true)
    val q8I: String? = null,
)

@JacksonXmlRootElement (localName="Item9A")
data class Item9A (
    @JacksonXmlProperty(localName="Q9A1A", isAttribute=true)
    val q9A1A: String? = null,
    @JacksonXmlProperty(localName="Q9A1B", isAttribute=true)
    val q9A1B: String? = null,
    @JacksonXmlProperty(localName="Q9A2A", isAttribute=true)
    val q9A2A: String? = null,
    @JacksonXmlProperty(localName="Q9A2B", isAttribute=true)
    val q9A2B: String? = null,
)

@JacksonXmlRootElement (localName="Item9B")
data class Item9B (
    @JacksonXmlProperty(localName="Q9B1A", isAttribute=true)
    val q9B1A: String? = null,
    @JacksonXmlProperty(localName="Q9B1B", isAttribute=true)
    val q9B1B: String? = null,
    @JacksonXmlProperty(localName="Q9B2A", isAttribute=true)
    val q9B2A: String? = null,
    @JacksonXmlProperty(localName="Q9B2B", isAttribute=true)
    val q9B2B: String? = null,
)

@JacksonXmlRootElement (localName="Item9C")
data class Item9C (
    @JacksonXmlProperty(localName="Q9C1", isAttribute=true)
    val q9C1: String? = null,
    @JacksonXmlProperty(localName="Q9C2", isAttribute=true)
    val q9C2: String? = null,
    @JacksonXmlProperty(localName="Q9C3", isAttribute=true)
    val q9C3: String? = null,
    @JacksonXmlProperty(localName="Q9C4", isAttribute=true)
    val q9C4: String? = null,
)

@JacksonXmlRootElement (localName="Item9D")
data class Item9D (
    @JacksonXmlProperty(localName="Q9D1", isAttribute=true)
    val q9D1: String? = null,
    @JacksonXmlProperty(localName="Q9D2", isAttribute=true)
    val q9D2: String? = null,
)

@JacksonXmlRootElement (localName="Item9E")
data class Item9E (
    @JacksonXmlProperty(localName="Q9E", isAttribute=true)
    val q9E: String? = null,
)

@JacksonXmlRootElement (localName="Item9F")
data class Item9F (
    @JacksonXmlProperty(localName="Q9F", isAttribute=true)
    val q9F: String? = null,
)

@JacksonXmlRootElement (localName="Item10A")
data class Item10A (
    @JacksonXmlProperty(localName="Q10A", isAttribute=true)
    val q10A: String? = null,
)

@JacksonXmlRootElement (localName="Item11")
data class Item11 (
    @JacksonXmlProperty(localName="Q11", isAttribute=true)
    val q11: String? = null,
)

@JacksonXmlRootElement (localName="Item11A")
data class Item11A (
    @JacksonXmlProperty(localName="Q11A1", isAttribute=true)
    val q11A1: String? = null,
    @JacksonXmlProperty(localName="Q11A2", isAttribute=true)
    val q11A2: String? = null,
)

@JacksonXmlRootElement (localName="Item11B")
data class Item11B (
    @JacksonXmlProperty(localName="Q11B1", isAttribute=true)
    val q11B1: String? = null,
    @JacksonXmlProperty(localName="Q11B2", isAttribute=true)
    val q11B2: String? = null,
)

@JacksonXmlRootElement (localName="Item11C")
data class Item11C (
    @JacksonXmlProperty(localName="Q11C1", isAttribute=true)
    val q11C1: String? = null,
    @JacksonXmlProperty(localName="Q11C2", isAttribute=true)
    val q11C2: String? = null,
    @JacksonXmlProperty(localName="Q11C3", isAttribute=true)
    val q11C3: String? = null,
    @JacksonXmlProperty(localName="Q11C4", isAttribute=true)
    val q11C4: String? = null,
    @JacksonXmlProperty(localName="Q11C5", isAttribute=true)
    val q11C5: String? = null,
)

@JacksonXmlRootElement (localName="Item11D")
data class Item11D (
    @JacksonXmlProperty(localName="Q11D1", isAttribute=true)
    val q11D1: String? = null,
    @JacksonXmlProperty(localName="Q11D2", isAttribute=true)
    val q11D2: String? = null,
    @JacksonXmlProperty(localName="Q11D3", isAttribute=true)
    val q11D3: String? = null,
    @JacksonXmlProperty(localName="Q11D4", isAttribute=true)
    val q11D4: String? = null,
    @JacksonXmlProperty(localName="Q11D5", isAttribute=true)
    val q11D5: String? = null,
)

@JacksonXmlRootElement (localName="Item11E")
data class Item11E (
    @JacksonXmlProperty(localName="Q11E1", isAttribute=true)
    val q11E1: String? = null,
    @JacksonXmlProperty(localName="Q11E2", isAttribute=true)
    val q11E2: String? = null,
    @JacksonXmlProperty(localName="Q11E3", isAttribute=true)
    val q11E3: String? = null,
    @JacksonXmlProperty(localName="Q11E4", isAttribute=true)
    val q11E4: String? = null,
)

@JacksonXmlRootElement (localName="Item11F")
data class Item11F (
    @JacksonXmlProperty(localName="Q11F", isAttribute=true)
    val q11F: String? = null,
)

@JacksonXmlRootElement (localName="Item11G")
data class Item11G (
    @JacksonXmlProperty(localName="Q11G", isAttribute=true)
    val q11G: String? = null,
)

@JacksonXmlRootElement (localName="Item11H")
data class Item11H (
    @JacksonXmlProperty(localName="Q11H1A", isAttribute=true)
    val q11H1A: String? = null,
    @JacksonXmlProperty(localName="Q11H1B", isAttribute=true)
    val q11H1B: String? = null,
    @JacksonXmlProperty(localName="Q11H1C", isAttribute=true)
    val q11H1C: String? = null,
    @JacksonXmlProperty(localName="Q11H2", isAttribute=true)
    val q11H2: String? = null,
)

@JacksonXmlRootElement(localName="State")
data class SECFirmNoticeFiledState (
    @JacksonXmlProperty(localName="RgltrCd", isAttribute=true)
    val regltrCd: String,
    @JacksonXmlProperty(localName="St", isAttribute=true)
    val state: String,
    @JacksonXmlProperty(localName="Dt", isAttribute=true)
    val date: String

)

@JacksonXmlRootElement(localName="Filing")
data class SECFirmFiling (
    @JacksonXmlProperty(localName="Dt", isAttribute=true)
    val date: String,
    @JacksonXmlProperty(localName="FormVrsn", isAttribute=true)
    val formVersion: String
)

@JsonRootName("Info")
data class SECFirmInfo (
    @JacksonXmlProperty(localName="SECRgnCD", isAttribute = true)
    val secRegnCD: String,
    @JacksonXmlProperty(localName="FirmCrdNb", isAttribute = true)
    val firmCrdNb: String,
    @JacksonXmlProperty(localName="SECNb", isAttribute = true)
    val secNb: String? = null,
    @JacksonXmlProperty(localName="BusNm", isAttribute = true)
    val busNm: String,
    @JacksonXmlProperty(localName="LegalNm", isAttribute = true)
    val legalNm: String,
    @JacksonXmlProperty(localName="UmbrRgstn", isAttribute = true)
    val umbrRgstn: String? = null
)

data class SECFirmAddress (
    @get:JacksonXmlProperty(localName="Strt1", isAttribute = true)
    val strt1: String? = null,
    @get:JacksonXmlProperty(localName="Strt2", isAttribute = true)
    val strt2: String? = null,
    @get:JacksonXmlProperty(localName="City", isAttribute = true)
    val city: String? = null,
    @get:JacksonXmlProperty(localName="State", isAttribute = true)
    val state: String? = null,
    @get:JacksonXmlProperty(localName="Cntry", isAttribute = true)
    val cntry: String? = null,
    @get:JacksonXmlProperty(localName="PostlCd", isAttribute = true)
    val postlCd: String? = null,
    @get:JacksonXmlProperty(localName="PhNb", isAttribute = true)
    val phNb: String? = null,
    @get:JacksonXmlProperty(localName="FaxNb", isAttribute = true)
    val faxNb: String? = null
) {
    @get:JsonIgnore
    val postalCode: String?
        get() = postlCd?.let {
            val i = it.indexOf("-")
            return it.substring(0, i)
        }
}

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JacksonXmlRootElement(localName = "IAPDFirmSECReport")
class SECReport (
    @field:JacksonXmlProperty(isAttribute=true, localName="GenOn")
    val generatedOn: String = "",
) {
    @get:JacksonXmlElementWrapper(localName = "Firms")
    @get:JacksonXmlProperty(localName = "Firm")
    var firms: List<SECFirm> = listOf ()

    val count: Int
        get () = firms.size
}

// EOF