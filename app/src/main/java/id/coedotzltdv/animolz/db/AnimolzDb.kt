package id.coedotzltdv.animolz.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [AnimolzEntitiy::class], version = 1, exportSchema = false)
abstract class AnimolzDb : RoomDatabase() {
    abstract val dao: AnimolzDao

    companion object {

        @Volatile
        private var INSTANCE: AnimolzDb? = null

        fun getInstance(context: Context): AnimolzDb {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext, AnimolzDb::class.java,
                        "animolz.db"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}