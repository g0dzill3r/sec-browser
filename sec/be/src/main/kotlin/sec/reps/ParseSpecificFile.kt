package sec.reps

import sec.XMLUtil
import java.nio.charset.StandardCharsets.UTF_8

fun main() {
    val which = 11
    val file = SECAdvisorReps.getLocalFile (which)
    val parsed = XMLUtil.parseAs<SECIndividuals> (file.reader (UTF_8))
    println (parsed)
    return
}

// EOF