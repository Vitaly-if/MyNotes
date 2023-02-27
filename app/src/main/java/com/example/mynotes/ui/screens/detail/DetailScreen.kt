package com.example.mynotes.ui.screens.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.mynotes.ui.navigation.Screens
import com.example.mynotes.ui.theme.MyNotesTheme
import java.util.*

/**
 * @author Vitaly.N on 08.02.2023.
 */
@Composable
fun DetailScreen(
    navController: NavHostController,
    id: String?,
) {
    val viewModel = hiltViewModel<DetailViewModel>()
    val note = viewModel.note.observeAsState().value
    id?.toInt()?.let { viewModel.fetchNote(id = it) }

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
                           viewModel.deleteNote {
                               navController.navigateUp()
                           }
                        }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Delete,
                        tint = Color.White,
                        contentDescription = "delete note",
                        modifier = Modifier
                            .align(Alignment.Center)
                    )
                }
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 68.dp)
                .padding(horizontal = 27.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = note?.title ?: "",
                fontSize = 35.sp,
                style = TextStyle(color = Color(0xFF303030), fontWeight = FontWeight.Light))

            Text(text = note?.content ?: "",
                fontSize = 24.sp,
                style = TextStyle(color = Color(0xFF303030), fontWeight = FontWeight.Light))

        }
    }
}

@Preview(showBackground = true)
@Composable
fun previewAddScreen() {
    MyNotesTheme {
        DetailScreen(rememberNavController(), "id")
    }
}