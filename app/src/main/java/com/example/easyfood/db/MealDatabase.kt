package com.example.easyfood.db

import android.content.Context
import androidx.room.*
import com.example.easyfood.model.Meal

@Database(entities = [Meal::class], version = 1)
@TypeConverters(MealTypeConverter::class)
abstract class MealDatabase : RoomDatabase() {
    abstract fun mealDao(): MealDao

    companion object {
        @Volatile
        private var instance: MealDatabase? = null

        @Synchronized
        fun getInstance(context: Context): MealDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context,
                    MealDatabase::class.java,
                    "name.db"
                ).fallbackToDestructiveMigration().build()
            }
            return instance as MealDatabase
        }
    }
}