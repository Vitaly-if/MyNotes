package com.example.mynotes.ui.screens.add_edit

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mynotes.ui.theme.MyNotesTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.toArgb
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.mynotes.domain.model.Note
import com.example.mynotes.ui.navigation.Screens
import java.util.*


/**
 * @author Vitaly.N on 08.02.2023.
 */
@Composable
fun AddScreen(
    navController: NavHostController,
) {
    val viewModel = hiltViewModel<AddEditViewModel>()
    var title by rememberSaveable { mutableStateOf("") }
    var desсription by rememberSaveable { mutableStateOf("") }
    Scaffold(
        topBar = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .padding(top = 52.dp)
                    .fillMaxWidth()
                    .height(48.dp)
                    .padding(horizontal = 24.dp)
            ) {
                Box(
                    modifier = Modifier
                        .width(48.dp)
                        .height(48.dp)
                        .clip(RoundedCornerShape(15.dp))
                        .background(Color(0xFF3B3B3B))
                        .clickable { navController.popBackStack() }
                ) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        tint = Color.White,
                        contentDescription = "nav back",
                        modifier = Modifier
                            .align(Alignment.Center)
                    )
                }
                Box(
                    modifier = Modifier
                        .width(48.dp)
                        .height(48.dp)
                        .clip(RoundedCornerShape(15.dp))
                        .background(Color(0xFF3B3B3B))
                        .clickable {
                            val color: Int = Color(
                                Random().nextInt(256),
                                Random().nextInt(256),
                                Random().nextInt(256)
                            ).toArgb()
                            viewModel.addNote(
                                Note(title = title,
                                    content = desсription,
                                    timestamp = 0L,
                                    backgroundColor = color)
                            ) {
                                navController.navigate(Screens.MainScreen.rout)
                            }
                        }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Add,
                        tint = Color.White,
                        contentDescription = "add note",
                        modifier = Modifier
                            .align(Alignment.Center)
                    )
                }
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            TextField(
                value = title,
                onValueChange = { title = it },
                label = { Text(text = "Title") }

            )
            TextField(
                value = desсription,
                onValueChange = { desсription = it },
                label = { Text(text = "Description") },
                modifier = Modifier.padding(top = 24.dp)

            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun previewAddScreen() {
    MyNotesTheme {
        AddScreen(rememberNavController())
    }
}