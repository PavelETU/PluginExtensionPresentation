package com.example.demo1

import com.intellij.execution.configurations.GeneralCommandLine
import com.intellij.execution.process.ScriptRunnerUtil
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.DialogWrapper
import com.intellij.ui.components.JBScrollPane
import java.awt.Component
import java.awt.Dimension
import java.awt.event.ActionEvent
import java.io.File
import java.nio.charset.Charset
import javax.swing.*

class JavaSwingDialog(private val project: Project?) : DialogWrapper(true) {
    init {
        init()
        isResizable = false
        title = "DEMO"
    }

    override fun createCenterPanel(): JComponent {
        val dialogContent = JPanel()
        val input = JTextField()
        val outputText = JTextArea()
        val processBtn = JButton("Process")
        val outputPanel =
            JBScrollPane(outputText, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER)
        processBtn.addActionListener { outputText.text = processCommandLine(input.text) }
        dialogContent.layout = BoxLayout(dialogContent, BoxLayout.PAGE_AXIS)
        processBtn.alignmentX = Component.CENTER_ALIGNMENT
        outputPanel.preferredSize = Dimension(500, 100)
        dialogContent.preferredSize = Dimension(500, 250)
        dialogContent.add(input)
        dialogContent.add(processBtn)
        dialogContent.add(outputPanel)
        return dialogContent
    }

    private fun processCommandLine(input: String): String = try {
        val commands = input.split(" ")
        val generalCommandLine = GeneralCommandLine(commands)
        generalCommandLine.charset = Charset.forName("UTF-8")
        generalCommandLine.workDirectory = File(project?.basePath ?: project.toString())
        val output = ScriptRunnerUtil.getProcessOutput(generalCommandLine)
        output
    } catch (t: Throwable) {
        "Invalid command"
    }

    override fun createActions(): Array<Action> {
        val closeAction = object : DialogWrapperAction("Close") {
            override fun doAction(e: ActionEvent?) {
                doOKAction()
            }
        }
        return arrayOf(closeAction)
    }
}