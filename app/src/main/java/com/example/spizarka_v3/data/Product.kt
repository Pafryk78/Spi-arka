package com.example.spizarka_v3.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products_table")
data class Product(
    @PrimaryKey(autoGenerate = true) val uid: Int=0,
    val category: String,
    val name: String,
    val quantity: Int
)
