package com.example.mynotes.ui.screens.main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.livedata.observeAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.mynotes.ui.components.NoteItem
import com.example.mynotes.ui.navigation.Screens
import com.example.mynotes.ui.theme.MyNotesTheme

/**
 * @author Vitaly.N on 07.02.2023.
 */
@Composable
fun MainScreen(navController: NavHostController) {

    val viewModel = hiltViewModel<MainViewModel>()
    val listNotes = viewModel.notes.observeAsState(listOf()).value
    Scaffold(
        floatingActionButton =
        {
            FloatingActionButton(onClick = { navController.navigate(Screens.AddScreen.rout) }) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    tint = Color.White,
                    contentDescription = "add Note"
                )
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Text(
                text = "Note",
                fontSize = 43.sp,
                modifier = Modifier
                    .padding(top = 43.dp, start = 24.dp, bottom = 12.dp)
            )
            listNotes.forEach() { note ->
                NoteItem(
                    title = note.title,
                    backgroundColor = Color(note.backgroundColor),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp)
                        .padding(vertical = 12.dp)
                        .clickable { navController.navigate(Screens.DetailScreen.rout + "/${note.id}") }

                )
            }

        }

    }
}
@Preview(showBackground = true)
@Composable
fun previewMainScreen() {
    MyNotesTheme {
        MainScreen(rememberNavController())
    }
}

