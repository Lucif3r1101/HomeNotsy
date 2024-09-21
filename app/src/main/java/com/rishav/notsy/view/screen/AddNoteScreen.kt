package com.rishav.notsy.view.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.rishav.notsy.domain.model.Note
import com.rishav.notsy.presentation.viewmodel.NoteViewModel

import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.remember
import androidx.compose.ui.draw.clip
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.rishav.notsy.data.entity.NoteEntity

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNoteScreen(navController: NavController, viewModel: NoteViewModel = hiltViewModel()) {
    var title by remember { mutableStateOf("") }
    var content by remember { mutableStateOf("") }
    var imageUri by remember { mutableStateOf<Uri?>(null) }

    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        imageUri = uri
    }

    Column(modifier = Modifier.padding(16.dp)) {
        TextField(value = title, onValueChange = { title = it }, label = { Text("Title") })
        TextField(value = content, onValueChange = { content = it }, label = { Text("Content") })

        Button(onClick = {
            imagePickerLauncher.launch("image/*")
        }) {
            Text("Pick Image")
        }

        imageUri?.let { uri ->
            Image(
                painter = rememberAsyncImagePainter(
                    model = uri,
                ),
                contentDescription = "Note Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .padding(top = 8.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
            Log.d("ImageURI", "Selected Image URI: ${uri}")

        }

        Button(onClick = {
            viewModel.addNote(
                Note(
                    title = title,
                    content = content,
                    timestamp = System.currentTimeMillis(),
                    imageUri = imageUri?.toString()
                )
            )
            navController.popBackStack()
        }) {
            Text("Save Note")
        }
    }
}

