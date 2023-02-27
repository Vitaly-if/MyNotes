package com.example.mynotes.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.BlendMode.Companion.Screen
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.NavHost
import androidx.navigation.navArgument
import com.example.mynotes.ui.screens.add_edit.AddEditScreen
import com.example.mynotes.ui.screens.main.NotesScreen

/**
 * @author Vitaly.N on 07.02.2023.
 */
sealed class Screens(val rout: String) {
    object NotesScreen : Screens(rout = "notes_screen")
    object AddEditScreen : Screens(rout = "add_edit_screen")
}

@Composable
fun SetupNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screens.NotesScreen.rout
    )
    {
        composable(route = Screens.NotesScreen.rout) {
            NotesScreen(navController = navController)
        }
        composable(
            route = Screens.AddEditScreen.rout + "?id = {id}&backgroundColor={backgroundColor}",
            arguments = listOf(navArgument("id") {
                type = NavType.IntType
                defaultValue = -1
            },
                (navArgument("backgroundColor") {
                    type = NavType.IntType
                    defaultValue = -1
                }
                        )
            )) {
            val color = it.arguments?.getInt("backgroundColor") ?: -1
            AddEditScreen(navController = navController, noteColor = color)
        }
    }
}