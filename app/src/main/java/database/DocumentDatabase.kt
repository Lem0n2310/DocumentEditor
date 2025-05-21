package database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Document::class], version = 1, exportSchema = false)
abstract class DocumentDatabase: RoomDatabase() {

    abstract fun DocumentDao(): DocumentDao

    companion object {
        @Volatile
        private var INSTANCE: DocumentDatabase? = null


        fun getDatabase(context: Context): DocumentDatabase {
            val tampInstance = INSTANCE
            if (tampInstance != null) {
                return tampInstance
            }

            @Synchronized
            fun NewEx(context: Context): DocumentDatabase {
                val instance = Room.databaseBuilder(
                    context,
                    DocumentDatabase::class.java,
                    "document_databse"
                ).build()
                INSTANCE = instance
                return instance
            }
            return NewEx(context)
        }

    }

}
