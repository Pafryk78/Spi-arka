package com.example.spizarka_v3.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Product::class], version = 1)
abstract class ProductDatabase :RoomDatabase() {
    abstract fun productDao(): ProductDao

}

object ProductDb{
    private var db: ProductDatabase? = null

    fun getInstance(context: Context): ProductDatabase {
        if (db == null) {
            db = Room.databaseBuilder(
                context.applicationContext,
                ProductDatabase::class.java,
                "product_database").build()
    }
        return db!!
    }

}