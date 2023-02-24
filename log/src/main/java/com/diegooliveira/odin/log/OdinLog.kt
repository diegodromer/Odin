package com.diegooliveira.odin.log

import android.util.Log

object OdinLog {

    val DEFAULT = ConsoleLogger()

    private val logs = mutableListOf<Logger>(DEFAULT)

    fun add(logger: Logger) {
        logs.add(logger)
    }

    fun v(message: String) {
        output(Level.VERBOSE) { logger ->
            logger.v(message)
        }
    }

    fun d(message: String) {
        output(Level.DEBUG) { logger ->
            logger.d(message)
        }
    }

    fun i(message: String) {
        output(Level.INFO) { logger ->
            logger.i(message)
        }
    }

    fun w(message: String) {
        output(Level.WARNING) { logger ->
            logger.w(message)
        }
    }

    fun e(message: String) {
        output(Level.ERROR) { logger ->
            logger.e(message)
        }
    }

    private fun output(level: Level, write: (Logger) -> Unit) {
        logs.forEach { logger ->
            if (logger.level.ordinal <= level.ordinal) {
                write.invoke(logger)
            }
        }
    }
}