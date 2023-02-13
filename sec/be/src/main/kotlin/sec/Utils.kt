package sec

fun interpreter (prompt: String = "> ", absorb: Boolean = true, func: (String) -> Boolean) {
    while (true) {
        print (prompt)
        val input = readln ()
        try {
            if (!func(input)) {
                break
            }
        }
        catch (e: Exception) {
            if (absorb) {
                println ("ERROR: $e")
                e.printStackTrace()
            } else {
                throw e
            }
        }
    }
    return
}

// EOF