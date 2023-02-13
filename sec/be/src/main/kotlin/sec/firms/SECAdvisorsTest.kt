package sec.firms

import sec.XMLUtil

fun main() {
    val sec = SECAdvisors.loadSample ()
    println (XMLUtil.encode(sec.firms[0]))
    println ("Loaded ${sec.firms.size} firms.")
    return
}

// EOF