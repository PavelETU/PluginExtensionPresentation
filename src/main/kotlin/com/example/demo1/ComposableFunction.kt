package com.example.demo1

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue

@Composable
fun ComposableFunction(viewModel: DemoViewModel) {
    val output by viewModel.output.collectAsState()
    ComposableView(output) { viewModel.processCommandLine(it) }
}

@Composable
fun ComposableView(output: String, action: (String) -> Unit) {
    val textState = remember { mutableStateOf(TextFieldValue("")) }
    Column {
        TextField(
            value = textState.value,
            onValueChange = { textState.value = it },
            label = { Text("Input") })
        Button(onClick = { action(textState.value.text) }) {
            Text("Process")
        }
        Column(Modifier.verticalScroll(rememberScrollState())) {
            Text(output)
        }
    }
}

@Preview
@Composable
fun preview() {
    ComposableView("Here we go") { }
}
