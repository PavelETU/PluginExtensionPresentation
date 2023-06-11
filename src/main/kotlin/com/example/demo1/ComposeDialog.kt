package com.example.demo1

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.awt.ComposePanel
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.DialogWrapper
import com.teya.emvtagsviewer.theme.WidgetTheme
import java.awt.event.ActionEvent
import javax.swing.Action
import javax.swing.JComponent

class ComposeDialog(private val project: Project?) : DialogWrapper(true) {
    init {
        init()
        isResizable = false
        title = "DEMO"
    }

    override fun createCenterPanel(): JComponent {
        return ComposePanel().apply {
            setBounds(0, 0, 500, 250)
            setContent {
                WidgetTheme(darkTheme = true) {
                    Surface(modifier = Modifier.fillMaxSize()) {
                        ComposableFunction(DemoViewModel(project))
                    }
                }
            }
        }
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