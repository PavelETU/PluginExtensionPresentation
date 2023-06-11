package com.example.demo1

import com.intellij.execution.configurations.GeneralCommandLine
import com.intellij.execution.process.ScriptRunnerUtil
import com.intellij.openapi.project.Project
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.io.File
import java.nio.charset.Charset

class DemoViewModel(private val project: Project?) {
    private val _output = MutableStateFlow("")
    val output = _output.asStateFlow()

    fun processCommandLine(input: String) {
        _output.tryEmit(try {
            val commands = input.split(" ")
            val generalCommandLine = GeneralCommandLine(commands)
            generalCommandLine.charset = Charset.forName("UTF-8")
            generalCommandLine.workDirectory = File(project?.basePath ?: project.toString())
            ScriptRunnerUtil.getProcessOutput(generalCommandLine)
        } catch (t: Throwable) {
            "Invalid command"
        })
    }
}
