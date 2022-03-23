package com.example.todomemo.data.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.todomemo.data.util.DataConstants.TABLE_NAME

@Entity(
    tableName = TABLE_NAME,
)
data class MemoEntity(
    @PrimaryKey(autoGenerate = true)
    val memoId: Int = 0,
    val title: String,
    val content: String
)