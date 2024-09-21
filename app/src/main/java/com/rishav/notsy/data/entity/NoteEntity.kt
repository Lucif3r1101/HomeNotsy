package com.rishav.notsy.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.rishav.notsy.domain.model.Note

@Entity(tableName = "notes")
data class NoteEntity(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    val title: String,
    val content: String,
    val timestamp: Long
)

fun NoteEntity.toDomain(): Note {
    return Note(
        id = this.id,
        title = this.title,
        content = this.content,
        timestamp = this.timestamp
    )
}



fun Note.toEntity(): NoteEntity = NoteEntity(
    id = id, title = title, content = content, timestamp = timestamp
)
