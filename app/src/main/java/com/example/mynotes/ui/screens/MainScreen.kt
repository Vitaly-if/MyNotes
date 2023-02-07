package com.example.mynotes.ui

import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.mynotes.ui.theme.MyNotesTheme

/**
 * @author Vitaly.N on 07.02.2023.
 */
@Composable
fun MainScreen() {
    Scaffold(
        floatingActionButton =
        {
            FloatingActionButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Filled.Add, tint = Color.White, contentDescription = "add Note")
            }
        }
    ) {

    }
}

@Preview(showBackground = true)
@Composable
fun previewMainScreen() {
    MyNotesTheme {
        MainScreen()
    }
}