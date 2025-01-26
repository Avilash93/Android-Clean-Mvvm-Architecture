package com.example.cleanmvvmarchitecture.core.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.cleanmvvmarchitecture.R
import com.example.cleanmvvmarchitecture.core.domain.model.Language
import com.example.cleanmvvmarchitecture.core.domain.model.supportedLanguages

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppDrawer(
    currentLanguage: String,
    onLanguageSelected: (String) -> Unit,
    onSettingsClick: () -> Unit,
    onDrawerCloseClick: () -> Unit
) {
    var showLanguageDialog by remember { mutableStateOf(false) }

    ModalDrawerSheet {
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(R.string.app_name),
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(16.dp)
        )
        Divider()

        NavigationDrawerItem(
            icon = { Icon(Icons.Default.Face, contentDescription = null) },
            label = { Text(stringResource(R.string.language)) },
            selected = false,
            onClick = { showLanguageDialog = true }
        )

        NavigationDrawerItem(
            icon = { Icon(Icons.Default.Settings, contentDescription = null) },
            label = { Text(stringResource(R.string.settings)) },
            selected = false,
            onClick = onSettingsClick
        )
    }

    if (showLanguageDialog) {
        LanguageSelectionDialog(
            currentLanguage = currentLanguage,
            onLanguageSelected = { 
                onLanguageSelected(it)
                showLanguageDialog = false
            },
            onDismiss = { showLanguageDialog = false }
        )
    }
}

@Composable
fun LanguageSelectionDialog(
    currentLanguage: String,
    onLanguageSelected: (String) -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(stringResource(R.string.select_language)) },
        text = {
            LazyColumn {
                items(supportedLanguages) { language ->
                    LanguageItem(
                        language = language,
                        isSelected = language.code == currentLanguage,
                        onClick = { onLanguageSelected(language.code) }
                    )
                }
            }
        },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text(stringResource(R.string.cancel))
            }
        }
    )
}

@Composable
fun LanguageItem(
    language: Language,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = isSelected,
            onClick = onClick
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = "${language.flag} ${language.name}",
            style = MaterialTheme.typography.bodyLarge
        )
    }
} 