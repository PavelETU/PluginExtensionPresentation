package com.example.demo1

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent

class MyAction : AnAction() {
    override fun actionPerformed(e: AnActionEvent) {
        ComposeDialog(e.project).show()
    }
}
