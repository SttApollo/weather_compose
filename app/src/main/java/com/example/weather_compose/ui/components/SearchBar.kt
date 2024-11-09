package com.example.weather_compose.ui.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weather_compose.models.City
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Composable
fun SearchBar(
    hint: String,
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
    height: Dp = 40.dp,
    elevation: Dp = 3.dp,
    cornerShape: Shape = RoundedCornerShape(8.dp),
    backgroundColor: Color = Color.White,
    onSearchClicked: (String) -> Unit = {},
    onTextChange: (String) -> Unit = {},
    suggestions: ImmutableList<City> = persistentListOf(),
    initialText: String, // Passed from HomeScreen
) {
    var text by remember(initialText) { mutableStateOf(initialText) }
    Log.d("SearchBar", "Initial Text: $initialText")

    var showSuggestions by remember { mutableStateOf(false) }

    var textFieldValue by remember { mutableStateOf(TextFieldValue(text = initialText)) }


       Column {
        Row(
            modifier = modifier
                .height(height)
                .fillMaxWidth()
                .shadow(elevation = elevation, shape = cornerShape)
                .background(color = backgroundColor, shape = cornerShape),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            BasicTextField(
                modifier = Modifier
                    .weight(5f)
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                value = initialText,
                onValueChange = { newText ->
                    //Log.d("TAG", "newText: $newText")
                    onTextChange(newText)
                    showSuggestions = newText.isNotEmpty()
                },
                enabled = isEnabled,
                textStyle = TextStyle(
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                ),
                decorationBox = { innerTextField ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        if (text.isEmpty()) {
                            Text(
                                text = hint,
                                color = Color.Gray.copy(alpha = 0.5f),
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                            )
                        }
                        innerTextField()
                    }
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Search
                ),
                keyboardActions = KeyboardActions(onSearch = {
                    if (text.isNotEmpty()) {
                        onSearchClicked(text)
                        showSuggestions = false
                    }
                }),
                singleLine = true
            )

            IconButton(
                modifier = Modifier
                    .weight(1f)
                    .size(40.dp),
                onClick = {
                    if (text.isNotEmpty()) {
                        onSearchClicked(text)
                        showSuggestions = false
                    }
                },
                enabled = text.isNotEmpty()
            ) {
                Icon(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp),
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search",
                    tint = MaterialTheme.colorScheme.primary,
                )
            }//end of icon
        }//end of Column


       // Dropdown menu to show suggestions
           CitySuggestionsDropdown(
               suggestions = suggestions.take(3), // Pass the suggestions
               showSuggestions = showSuggestions,
               onSuggestionClick = { suggestion ->
                   val newText = "${suggestion.name},${suggestion.country}"
                   text = newText
                   onTextChange(newText)
                   showSuggestions = false
               },
               onDismissRequest = { showSuggestions = false })

    }
}

