package com.fairphone.assignment.rss.utils

import android.util.Log
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.xml.sax.SAXException
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.io.IOException

class RSSParserTest {

    private lateinit var rSSParser: RSSParser;

    @Before
    fun setUp() {
        rSSParser = RSSParser()
    }

    @Test
    @Throws(Exception::class)
    fun getRSSFeedItems() {
        val inputStream = readFile(RSSParserTest::class.java, "feedvalue")
        if (inputStream != null) {
            val item = rSSParser.getRSSFeedItems(inputStream)
            assertEquals("Rethinking plastic waste", item.get(0).title)
        }
    }

    @Test
    @Throws(Exception::class)
    fun getRSSFeedNoItems() {
        val inputStream = readFile(RSSParserTest::class.java, "feedvalueNoItem")
        if (inputStream != null) {
            val item = rSSParser.getRSSFeedItems(inputStream)
            assertEquals(0, item.size)
        }
    }

    fun readFile(cls: Class<*>, fileName: String): String? {
        var br: BufferedReader? = null
        val path = prepareCurrentPath(cls, fileName)
        try {
            br = BufferedReader(FileReader(path))
            val sb = StringBuilder()
            var line: String? = br.readLine()

            while (line != null) {
                sb.append(line)
                sb.append("\n")
                line = br.readLine()
            }

            return sb.toString()
        } catch (e: Exception) {
            Log.e("", e.message)

        } finally {
            try {
                br?.close()
            } catch (e: IOException) {
                Log.e("", e.message)
            }

        }
        return null
    }

    fun prepareCurrentPath(cls: Class<*>, fileName: String): String {
        val pckg = cls.name
        val paths = pckg.split("\\.".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        val sb = StringBuilder()
        sb.append("src").append(File.separator)
        sb.append("test").append(File.separator)
        sb.append("java").append(File.separator)
        for (index in 0 until paths.size - 1) {

            sb.append(paths[index]).append(File.separator)
        }
        sb.append(fileName)
        return sb.toString()
    }

}

