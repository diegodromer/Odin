package com.diegooliveira.odin.log

import java.text.SimpleDateFormat
import java.util.*

interface Logger {

    var level: Level

    fun v(message: String)

    fun d(message: String)

    fun i(message: String)

    fun w(message: String)

    fun e(message: String)

    fun builderTag(): Triple<String, Int, String> {
        val stackElement = Thread.currentThread().stackTrace.dropWhile {
            it.className != OdinLog.javaClass.canonicalName
        }.drop(2).firstOrNull()
            ?: throw java.lang.IllegalArgumentException("Classname not found at stacktrace!")

        val className = stackElement.className.takeLastWhile { it != '.' }
        val fileName = stackElement.fileName
        val lineNumber = stackElement.lineNumber

        return Triple(className, lineNumber, fileName)
    }

    fun buildMessage(message: String, fileName: String, lineNumber: Int) =
        ":: $message :: ($fileName:$lineNumber)"
}