package id.coedotzltdv.animolz.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import id.coedotzltdv.animolz.db.AnimolzDao
import id.coedotzltdv.animolz.viewmodel.ListDataViewModel

class ListDataViewModelFactory(
    private val db: AnimolzDao
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ListDataViewModel::class.java)) {
            return ListDataViewModel(db) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}