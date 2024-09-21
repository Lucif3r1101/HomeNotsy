package com.rishav.notsy.view.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.rishav.notsy.domain.model.Note
import com.rishav.notsy.presentation.viewmodel.NoteViewModel


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ListNoteScreen(navController: NavController, viewModel: NoteViewModel = hiltViewModel()) {
    val notes by viewModel.notesState.collectAsState()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate("add") }) {
                Icon(Icons.Default.Add, contentDescription = "Add Note")
            }
        }
    ) {
        LazyColumn {
            items(notes) { note ->
                NoteItem(note = note, onClick = { /* Handle click */ })
            }
        }
    }
}



@Composable
fun NoteItem(note: Note, onClick: () -> Unit) {
    val backgroundColor = when (note.id?.rem(5)) {
        0 -> Color(0xFFFFD54F) // Yellow
        1 -> Color(0xFF64B5F6) // Blue
        2 -> Color(0xFF81C784) // Green
        3 -> Color(0xFFBA68C8) // Purple
        4 -> Color(0xFFEF5350) // Red
        else -> Color.White
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable(onClick = onClick),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = backgroundColor)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = note.title, style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(4.dp))

            Text(text = note.content, style = MaterialTheme.typography.bodyMedium)

            note.imageUri?.let { uri ->
                Image(
                    painter = rememberAsyncImagePainter(uri),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp)
                        .padding(top = 8.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(text = formatTimestamp(note.timestamp), style = MaterialTheme.typography.bodySmall)
        }
    }
}

fun formatTimestamp(timestamp: Long): String {
    val now = System.currentTimeMillis()
    val diffInSeconds = (now - timestamp) / 1000
    return when {
        diffInSeconds < 60 -> "now"
        diffInSeconds < 3600 -> "${diffInSeconds / 60} min ago"
        diffInSeconds < 86400 -> "${diffInSeconds / 3600} hours ago"
        diffInSeconds < 2592000 -> "${diffInSeconds / 86400} days ago"
        else -> "${diffInSeconds / 2592000} months ago"
    }
}


