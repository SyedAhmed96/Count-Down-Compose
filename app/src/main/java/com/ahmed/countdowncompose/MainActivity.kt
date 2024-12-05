package com.ahmed.countdowncompose

// import androidx.activity.enableEdgeToEdge

import com.google.accompanist.flowlayout.FlowRow
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.flowlayout.FlowCrossAxisAlignment
import com.google.accompanist.flowlayout.FlowMainAxisAlignment

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Uncomment this line if edge-to-edge layout is needed
        // enableEdgeToEdge()

        // Set the content for the activity using Jetpack Compose
        setContent {
            MyApp()
        }
    }
}

@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyApp() {
    // Create an instance of the ViewModel
    val viewModel = CountDownViewModel()

    // Main structure of the app using Material3 Scaffold
    Scaffold(
        topBar = {
            TopAppBar(
                // Sets the title of the app bar
                title = { Text("Timer App") },
                // Applies a background color to the top app bar
                modifier = Modifier.background(MaterialTheme.colorScheme.secondary),
            )
        },
        content = { paddingValues ->
            // Main content area with padding applied from the Scaffold
            Box(
                modifier = Modifier
                    .padding(paddingValues) // Ensures content respects the Scaffold's padding
                    .fillMaxSize()
            ) {
                CountDownTimerScreen(viewModel = viewModel)
            }
        }
    )
}

@Composable
fun CountDownTimerScreen(viewModel: CountDownViewModel) {
    // Collect the current timer value from the ViewModel's Flow
    val timerValue by viewModel.timerState.collectAsState()

    // Collect the running state of the timer from the ViewModel's Flow
    val isRunning by viewModel.isRunning.collectAsState()

    // Main layout of the timer screen
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally, // Centers content horizontally
        verticalArrangement = Arrangement.Center // Centers content vertically
    ) {
        // Displays the current timer value
        Text(
            text = "$timerValue",
            style = MaterialTheme.typography.displayMedium,
            modifier = Modifier.testTag("timerValueText")
        )

        Spacer(modifier = Modifier.height(32.dp))

        // FlowRow for 2x2 button layout
        FlowRow(
            mainAxisSpacing = 8.dp, // Spacing between buttons horizontally
            crossAxisSpacing = 16.dp, // Spacing between rows
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(), // Ensures the row wraps properly
            mainAxisAlignment = FlowMainAxisAlignment.Center // Centers buttons horizontally
        ) {
            // Start button
            Button(
                onClick = { viewModel.startTimer(10) },
                enabled = !isRunning,
                modifier = Modifier
                    .width(120.dp) // Ensures uniform button width
                    .testTag("startButton")
            ) {
                Text(text = "Start")
            }

            // Pause button
            Button(
                onClick = { viewModel.pauseTimer() },
                enabled = isRunning,
                modifier = Modifier
                    .width(120.dp) // Ensures uniform button width
                    .testTag("pauseButton")
            ) {
                Text(text = "Pause")
            }

            // Resume button
            Button(
                onClick = { viewModel.startTimer(timerValue) },
                enabled = !isRunning,
                modifier = Modifier
                    .width(120.dp) // Ensures uniform button width
                    .testTag("resumeButton")
            ) {
                Text(text = "Resume")
            }

            // Reset button
            Button(
                onClick = { viewModel.resetTimer() },
                modifier = Modifier
                    .width(120.dp) // Ensures uniform button width
                    .testTag("resetButton")
            ) {
                Text(text = "Reset")
            }
        }
    }


}


//@Composable
//fun CountDownTimerScreen(viewModel: CountDownViewModel) {
//    // Collect the current timer value from the ViewModel's Flow
//    val timerValue by viewModel.timerState.collectAsState()
//
//    // Collect the running state of the timer from the ViewModel's Flow
//    val isRunning by viewModel.isRunning.collectAsState()
//
//    // Main layout of the timer screen
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp), // Adds padding around the content
//        horizontalAlignment = Alignment.CenterHorizontally, // Centers content horizontally
//        verticalArrangement = Arrangement.Center // Centers content vertically
//    ) {
//
//        // Displays the current timer value
//        Text(
//            text = "$timerValue", // Shows the current countdown value
//            style = MaterialTheme.typography.displayMedium // Applies a large text style
//        )
//
//        Spacer(modifier = Modifier.height(16.dp)) // Adds vertical spacing
//
//        // Row for action buttons
//        Row {
//            // Start button
//            Button(
//                onClick = { viewModel.startTimer(10) }, // Starts the timer from 10 seconds
//                enabled = !isRunning // Button is disabled if the timer is running
//            ) {
//                Text(text = "Start")
//            }
//
//            Spacer(modifier = Modifier.width(8.dp)) // Adds horizontal spacing
//
//            // Pause button
//            Button(
//                onClick = { viewModel.pauseTimer() }, // Pauses the timer
//                enabled = isRunning // Button is enabled only if the timer is running
//            ) {
//                Text(text = "Pause")
//            }
//
//            Spacer(modifier = Modifier.width(8.dp)) // Adds horizontal spacing
//
//            // Resume button
//            Button(onClick = { viewModel.startTimer(timerValue) },
//                enabled = !isRunning) {
//                Text(text = "Resume")
//            }
//
//            Spacer(modifier = Modifier.width(8.dp)) // Adds horizontal spacing
//
//            // Reset button
//            Button(
//                onClick = { viewModel.resetTimer() } // Resets the timer to its initial state
//            ) {
//                Text(text = "Reset")
//            }
//
//        }
//    }
//}