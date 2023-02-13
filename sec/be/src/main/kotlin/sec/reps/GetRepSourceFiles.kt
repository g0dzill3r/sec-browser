package sec.reps

/**
 * Test applications that dumps the paths to the local input files.
 */

fun main() {
    val seq = SECAdvisorReps.getLocalFiles()
    var i = 0;
    for (path in seq) {
        println ("${i++}: $path")
    }
    println ("$i files found.")
    return
}

// EOF