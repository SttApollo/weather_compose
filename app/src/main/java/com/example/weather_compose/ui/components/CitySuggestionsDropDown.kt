package com.example.weather_compose.ui.components

import android.util.Log
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.weather_compose.models.City

@Composable
fun CitySuggestionsDropdown(
    suggestions: List<City>,
    showSuggestions: Boolean,
    onSuggestionClick: (City) -> Unit,
    onDismissRequest: () -> Unit
) {
    Log.d("CitySuggestionsDropdown", "Recomposing")
    DropdownMenu(
        expanded = showSuggestions,
        onDismissRequest = onDismissRequest,
        modifier = Modifier.fillMaxWidth()
    ) {
        suggestions.forEach { suggestion ->
            Log.d("DropdownMenuItem", "Recomposing for ${suggestion.name}")
            DropdownMenuItem(
                text = { Text("${suggestion.name}, ${suggestion.country}") },onClick = {
                    onSuggestionClick(suggestion)
                }
            )
        }
    }
}