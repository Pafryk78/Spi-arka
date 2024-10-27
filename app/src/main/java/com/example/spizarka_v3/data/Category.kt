package com.example.spizarka_v3.data

import androidx.room.PrimaryKey

data class Category(@PrimaryKey(autoGenerate = true) val uid: Int=0,val name: String, val imageResId : Int)
