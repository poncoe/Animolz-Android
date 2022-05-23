package id.coedotzltdv.animolz.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface AnimolzDao {
    @Insert
    fun insert(animolz: AnimolzEntitiy)

    //@Query("SELECT * FROM animolz ORDER BY id DESC LIMIT 1")
    //fun getLastAnimolz(): LiveData<AnimolzEntity?>
    @Query("SELECT * FROM animolz ORDER BY id DESC")
    fun getLastAnimolz(): LiveData<List<AnimolzEntitiy>>

    @Query("DELETE FROM animolz")
    fun clearData()
}