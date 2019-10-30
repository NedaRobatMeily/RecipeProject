package com.nedarm.myapplication.testutils

import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.Moshi.Builder
import java.io.File
import java.io.IOException
import java.nio.charset.Charset

object TestUtils {

    private val TEST_MOSHI = initializeMoshi()

    fun <T> loadJson(path: String, clazz: Class<T>): T? {
        try {
            val json = getFileString(path)

            return TEST_MOSHI.adapter(clazz).fromJson(json)
        } catch (e: IOException) {
            throw IllegalArgumentException("Could not deserialize: $path into class: $clazz")
        }
    }

    private fun getFileString(path: String): String {
        try {
            return File(path).inputStream().readBytes().toString(Charset.defaultCharset())
        } catch (e: IOException) {
            throw IllegalArgumentException("Could not read from resource at: $path")
        }
    }

    private fun initializeMoshi(): Moshi {
        val builder = Builder()
        builder.add(KotlinJsonAdapterFactory())
        return builder.build()
    }
}
