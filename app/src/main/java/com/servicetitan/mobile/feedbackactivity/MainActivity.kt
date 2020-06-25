package com.servicetitan.mobile.feedbackactivity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.compose.state
import androidx.ui.core.Alignment
import androidx.ui.core.Modifier
import androidx.ui.core.setContent
import androidx.ui.foundation.Icon
import androidx.ui.foundation.Text
import androidx.ui.graphics.Color
import androidx.ui.input.ImeAction
import androidx.ui.input.KeyboardType
import androidx.ui.layout.*
import androidx.ui.material.*
import androidx.ui.material.icons.Icons
import androidx.ui.material.icons.sharp.Star
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.dp
import com.servicetitan.mobile.feedbackactivity.ui.Colors
import com.servicetitan.mobile.feedbackactivity.ui.FeedbackActivityTheme

// https://developer.android.com/reference/kotlin/androidx/ui/material/package-summary#top-level-functions

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FeedbackActivityTheme(false) {
                FeedbackLayout()
            }
        }
    }
}

@Composable
fun FeedbackLayout() {

    val fullNameState = state { "" }
    val emailState = state { "" }
    val feedbackState = state { "" }
    val stars = state { 5 }
    val showSuccessDialog = state { false }

    val inputModifier = Modifier.padding(8.dp).fillMaxWidth()

    Column {
        TopAppBar(
            title = { Text(text = "Jetpack Compose") }
        )

        if (showSuccessDialog.value) {

            AlertDialog(
                onCloseRequest = {
                    showSuccessDialog.value = false
                },
                text = { Text(text = "Thank you for your feedback.") },
                title = { Text(text = "Thank you") },
                confirmButton = {
                    Button(onClick = {
                        showSuccessDialog.value = false
                    }) {
                        Text(text = "Ok")
                    }
                }
            )
        }


        Column(modifier = Modifier.padding(16.dp).fillMaxWidth()) {
            FilledTextField(
                modifier = inputModifier,
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Text,
                value = fullNameState.value,
                onValueChange = { fullNameState.value = it },
                label = { Text("Full Name") }
            )

            FilledTextField(
                modifier = inputModifier,
                imeAction = ImeAction.Next,
                value = emailState.value,
                keyboardType = KeyboardType.Email,
                onValueChange = { emailState.value = it },
                label = { Text("Email") }
            )

            FilledTextField(
                modifier = inputModifier,
                imeAction = ImeAction.Done,
                value = feedbackState.value,
                placeholder = {
                    Text(text = "Your feedback")
                },
                onValueChange = { feedbackState.value = it },
                label = { Text("Your feedback") }
            )

            Spacer(modifier = Modifier.padding(top = 8.dp))

            Row(modifier = Modifier.gravity(align = Alignment.CenterHorizontally)) {
                Star(currentValue = stars.value, index = 1, onClick = { stars.value = it })
                Star(currentValue = stars.value, index = 2, onClick = { stars.value = it })
                Star(currentValue = stars.value, index = 3, onClick = { stars.value = it })
                Star(currentValue = stars.value, index = 4, onClick = { stars.value = it })
                Star(currentValue = stars.value, index = 5, onClick = { stars.value = it })
            }

            Spacer(modifier = Modifier.padding(top = 8.dp))

            Button(
                modifier = Modifier.padding(start = 8.dp, end = 8.dp).fillMaxWidth(),
                onClick = {
                    showSuccessDialog.value = true
                }) {
                Text(text = "SEND")
            }
        }
    }
}


@Composable
fun Star(index: Int, currentValue: Int, onClick: (index: Int) -> Unit) {
    IconButton(onClick = {
        onClick(index)
    }) {
        Icon(
            asset = Icons.Sharp.Star,
            tint = if (currentValue >= index) Colors.StarYellow else Color.Black
        )
    }
}


@Preview(showBackground = true, name = "Preview 1")
@Composable
fun DefaultPreview() {
    FeedbackActivityTheme {
        FeedbackLayout()
    }
}