package com.example.composecolors

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.composecolors.ui.theme.ComposeColorsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeColorsTheme {
                val viewModel = viewModel<ColorsViewModel>()
                val flowColor by viewModel.colorFlow.collectAsStateWithLifecycle()
                val redFlowColor by viewModel.redFlow.collectAsStateWithLifecycle()
                val blueFlowColor by viewModel.blueFlow.collectAsStateWithLifecycle()
                val combinedFlowColor by viewModel.combinedFlow.collectAsStateWithLifecycle()
                val composeColor = viewModel.composeColor

                Column(modifier = Modifier
                    .fillMaxSize()
                    .clickable {
                        viewModel.pickRandomColor()
                    }) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color(redFlowColor))
                            .weight(.15f)
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color(flowColor))
                            .weight(.85f),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "click here")
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color(combinedFlowColor))
                            .weight(.15f)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposeColorsTheme {
        Greeting("Android")
    }
}