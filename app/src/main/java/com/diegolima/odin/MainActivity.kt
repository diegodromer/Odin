package com.diegolima.odin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.diegooliveira.odin.log.FileLogger
import com.diegooliveira.odin.log.Level
import com.diegooliveira.odin.log.LogcatLogger
import com.diegooliveira.odin.log.OdinLog
import java.io.File

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val button = Button(this)

//        val logcatLogger = LogcatLogger()
//        logcatLogger.level = Level.INFO

        OdinLog.DEFAULT.level = Level.VERBOSE

        OdinLog.add(LogcatLogger().apply {
            level = Level.INFO
        })

        val file = File(applicationContext.filesDir, "app.log")
        OdinLog.add(FileLogger(file).apply {
            level = Level.ERROR
        })

        button.setOnClickListener {
            OdinLog.v("log VERBOSE")
            OdinLog.d("log DEBUG")
            OdinLog.i("log INFO")
            OdinLog.w("log WARNING")
            OdinLog.e("log ERROR")
        }
        setContentView(button)
    }
}