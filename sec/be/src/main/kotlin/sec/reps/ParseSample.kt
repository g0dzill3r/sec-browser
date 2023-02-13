package sec.reps

import sec.XMLUtil
import java.io.File

fun main() {
    val path = SECAdvisorReps.sampleFile.reader ()
    val parsed = XMLUtil.parseAs<SECIndividuals> (path)
    println (parsed)
    return
}

// EOF