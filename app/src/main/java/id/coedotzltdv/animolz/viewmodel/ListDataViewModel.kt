package id.coedotzltdv.animolz.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.coedotzltdv.animolz.db.AnimolzDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ListDataViewModel(private val db: AnimolzDao) : ViewModel() {
    val data = db.getLastAnimolz()

    fun hapusData() = viewModelScope.launch { withContext(Dispatchers.IO) {
        db.clearData() }
    }
}
