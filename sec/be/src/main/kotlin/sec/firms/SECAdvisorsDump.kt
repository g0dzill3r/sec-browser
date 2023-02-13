package sec.firms

fun main() {
    val br = SECAdvisors.sampleFile.reader ()
    var i = 0
    while (br.ready()){
        if (i > 83292) {
            break
        }
        val c = br.read ()
        print (c.toChar())
        i ++
    }
    return
}

// EOF