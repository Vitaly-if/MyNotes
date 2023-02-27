package com.example.mynotes.ui.screens.main

import androidx.compose.animation.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.mynotes.ui.NotesEvent
import com.example.mynotes.ui.components.NoteItem
import com.example.mynotes.ui.components.OrderSection
import com.example.mynotes.ui.navigation.Screens
import com.example.mynotes.ui.theme.MyNotesTheme
import kotlinx.coroutines.launch

/**
 * @author Vitaly.N on 07.02.2023.
 */
@Composable
fun NotesScreen(navController: NavHostController) {

    val viewModel = hiltViewModel<MainViewModel>()
    val state = viewModel.state.value
    val scaffoldState = rememberScaffoldState()
    var scope = rememberCoroutineScope()
    //val listNotes = viewModel.state.observeAsState(listOf()).value
    Scaffold(
        floatingActionButton =
        {
            FloatingActionButton(onClick = { navController.navigate(Screens.AddEditScreen.rout) }) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    tint = Color.White,
                    contentDescription = "add Note"
                )
            }
        },
        scaffoldState = scaffoldState
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Your note",
                    style = MaterialTheme.typography.h4,
                    fontSize = 43.sp,
                    modifier = Modifier
                        .padding(top = 43.dp, start = 24.dp, bottom = 12.dp)
                )
                IconButton(onClick = { viewModel.onEvent(NotesEvent.ToggleOrderSection) }) {
                    Icon(
                        imageVector = Icons.Default.MoreVert,
                        contentDescription = "Sort"
                    )
                }
            }
            AnimatedVisibility(
                visible = state.isOrderSectionVisible,
                enter = fadeIn() + slideInVertically(),
                exit = fadeOut() + slideOutVertically()
            ) {
                OrderSection(modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                    noteOrder = state.noteOrder,
                    onOrderChange = {
                        viewModel.onEvent(NotesEvent.Order(it))
                    })
            }
            Spacer(modifier = Modifier.height(16.dp))
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 8.dp)
            ) {
                items(state.notes) { note ->
                    NoteItem(note = note, modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            navController.navigate(Screens.AddEditScreen.rout + "?${note.id}${note.backgroundColor}")
                        }, onDeleteClick = {
                        viewModel.onEvent(NotesEvent.DeleteNote(note))
                        scope.launch {
                            val result = scaffoldState.snackbarHostState.showSnackbar(
                                message = "Note deleted",
                                actionLabel = "Undo"
                            )
                            if (result == SnackbarResult.ActionPerformed) {
                                viewModel.onEvent(NotesEvent.RestoreNote)
                            }
                        }
                    }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }

        }

    }
}

@Preview(showBackground = true)
@Composable
fun previewMainScreen() {
    MyNotesTheme {
        NotesScreen(rememberNavController())
    }
}

