package sec.reps

import com.example.config.Config
import com.example.config.LocalFilesystem
import com.example.config.config
import com.example.util.ResourceUtil
import com.github.salomonbrys.kodein.instance
import sec.firms.SECReport
import sec.XMLUtil
import java.io.File
import java.nio.charset.StandardCharsets

object SECAdvisorReps {
    private val RESOURCE_DATA = "individuals/IA_Indvl_Feeds%d.xml"
    private val RESOURCE_SAMPLE = "individuals/sample-rep.xml"

    private val filesystem: LocalFilesystem
        get () {
            val conf = config.instance<Config> ()
            return conf.get (LocalFilesystem::class.java) as LocalFilesystem
        }

    val sampleFile: File
        get () {
            return File (filesystem.resourceRoot, RESOURCE_SAMPLE);
        }

    fun getLocalFile (which: Int): File {
        val filename = String.format (RESOURCE_DATA, which)
        return File (filesystem.resourceRoot, filename)
    }

    fun getLocalFiles () : Sequence<File> {
        var i = 1
        return sequence {
            while (true) {
                val file = getLocalFile (i ++)
                if (file.exists()) {
                    yield (file)
                } else {
                    break
                }
            }
        }
    }

    fun loadSample (): SECReport {
        return XMLUtil.parseAs (sampleFile.reader ())
    }

    fun load (): SECIndividuals {
        val seq = getLocalFiles ()
        var all: SECIndividuals? = null
        seq.forEachIndexed { index, file ->

            // A hack to limit the number of individuals that are loaded in the
            // dev environment since the EC2 instance doesn't have much memory.

            if (config.instance<String> ("env") == "dev") {
                if (index > 5) {
                    return all as SECIndividuals
                }
            }

            val reader = file.reader (StandardCharsets.UTF_8)
            val next = XMLUtil.parseAs<SECIndividuals> (reader)
            if (all == null) {
                all = next
            } else {
                all!!.individuals.addAll (next.individuals)
            }
        }
        return all as SECIndividuals
    }
}

// EOF