package com.example.util

import java.io.*
import java.net.URL
import java.nio.charset.StandardCharsets.UTF_8

object ResourceUtil {

    fun getPath (path: String): URL {
        val cls = Thread.currentThread().contextClassLoader
        val url = cls.getResource (path)
        return url
    }

    /**
     *
     */

    fun getAsStream (path: String): InputStream {
        val cls = Thread.currentThread().contextClassLoader
        val inputStream = cls.getResourceAsStream(path) ?: throw IOException ("Resource not found: $path")
        return inputStream
    }

    fun getAsReader (path: String): Reader {
        return Thread.currentThread ().contextClassLoader.getResourceAsStream(path)?.reader (UTF_8) ?: throw IOException ("Resource not found: $path")
    }

    /**
     * Used to fetch the contents of a resource as a String.
     */

    fun getAsString (path: String): String {
        val cls = Thread.currentThread().contextClassLoader
        val inputStream = cls.getResourceAsStream(path) ?: throw IOException ("Resource not found: $path")
        val bufferSize = 1024
        val buffer = CharArray(bufferSize)
        val out = StringBuilder()
        val istr = java.io.InputStreamReader(inputStream, "UTF-8")
        while (true) {
            val rsz = istr.read(buffer, 0, buffer.size)
            if (rsz < 0)
                break
            out.append(buffer, 0, rsz)
        }
        val results = out.toString ()
        return results
    }
}

// EOF