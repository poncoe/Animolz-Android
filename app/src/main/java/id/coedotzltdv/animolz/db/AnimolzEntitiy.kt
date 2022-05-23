package id.coedotzltdv.animolz.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "animolz")
data class AnimolzEntitiy(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,
    var namaPemilik: String,
    var namaHewan: String,
    var isJenis: Boolean,
    var tanggal: Long = System.currentTimeMillis(),
)