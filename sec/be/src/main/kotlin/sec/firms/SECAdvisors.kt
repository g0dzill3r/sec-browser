package sec.firms

import com.example.config.Config
import com.example.config.LocalFilesystem
import com.example.config.config
import com.github.salomonbrys.kodein.instance
import sec.XMLUtil
import java.io.File

/**
 * A utility class used to load the SEC firm dataset from the filesystem.
 */

object SECAdvisors {
//    private val FEED_URL = "https://reports.adviserinfo.sec.gov/reports/CompilationReports/IA_FIRM_SEC_Feed_02_06_2023.xml.gz"
//
//    fun getRemoteReader (): BufferedReader {
//        return GZIPInputStream (URL (FEED_URL).openStream ()).bufferedReader (UTF_8)
//    }

//    fun load (): SECReport = XMLUtil.parseAs(getRemoteReader())

    private val RESOURCE_FILE = "firms/IA_FIRM_SEC_Feed_02_06_2023.xml"
    private val RESOURCE_SAMPLE = "firms/sample.xml"

    private val filesystem: LocalFilesystem
        get () {
            val conf = config.instance<Config> ()
            return conf.get (LocalFilesystem::class.java) as LocalFilesystem
        }

    val dataFile: File
        get () {
            return File (filesystem.resourceRoot, RESOURCE_FILE)
        }


    val sampleFile: File
        get () {
            return File (filesystem.resourceRoot, RESOURCE_SAMPLE)
        }

    fun load (): SECReport = XMLUtil.parseAs (dataFile.reader ())
    fun loadSample (): SECReport = XMLUtil.parseAs (sampleFile.reader ())
}

// EOF