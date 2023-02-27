package com.example.mynotes.ui.screens.add_edit

/**
 * @author Vitaly.N on 17.02.2023.
 */
data class NoteTextFieldState(
    val text: String = "",
    val hint: String = "",
    val isHintVisible: Boolean = true
) {
}