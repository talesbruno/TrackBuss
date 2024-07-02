package com.example.trackbuss.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DockedSearchBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> GenericSearchBar(
    items: List<T>,
    query: String,
    onQueryChange: (String) -> Unit,
    onSearch: () -> Unit,
    active: Boolean,
    onActiveChange: (Boolean) -> Unit,
    placeholder: @Composable () -> Unit,
    itemContent: @Composable (T) -> Unit
) {
    var searchResults by remember { mutableStateOf<List<T>>(emptyList()) }

    LaunchedEffect(query) {
        searchResults = if (query.isNotEmpty()) {
            items.filter { item ->
                item.toString().contains(query, ignoreCase = true)
            }
        } else {
            emptyList()
        }
    }
    LaunchedEffect(searchResults.isEmpty() && query.isEmpty()) {
        if (!active && searchResults.isNotEmpty()) {
            onActiveChange(false)
        }
    }

    DockedSearchBar(
        query = query,
        onQueryChange = onQueryChange,
        onSearch = { onSearch() },
        active = active,
        onActiveChange = onActiveChange,
        placeholder = placeholder,
        trailingIcon = {
            if (active) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Back",
                    modifier = Modifier
                        .padding(start = 16.dp)
                        .clickable {
                            onActiveChange(false)
                            onQueryChange("")
                        },
                )
            } else {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search",
                    modifier = Modifier.padding(start = 16.dp),
                )
            }
        }
    ) {
        if (searchResults.isNotEmpty()) {
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                items(searchResults) { item ->
                    itemContent(item)
                }
            }
        } else if (query.isNotEmpty()) {
            Text(
                text = "Nenhum resultado encontrado",
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}
