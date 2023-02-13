package sec.reps

import sec.XMLUtil
import java.nio.charset.StandardCharsets.UTF_8

fun main() {
    val seq = SECAdvisorReps.getLocalFiles()
    val all = SECIndividuals ("")
    for (file in seq) {
        println ("Parsing [${file.name}]")
        val reader = file.reader (UTF_8)
        val parsed = XMLUtil.parseAs<SECIndividuals> (reader)
        all.individuals.addAll (parsed.individuals)
    }
    println (all)
    println ("Read ${all.count} individuals.")
    return
}

// EOF