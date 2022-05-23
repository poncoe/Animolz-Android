package id.coedotzltdv.animolz.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.coedotzltdv.animolz.db.AnimolzDao
import id.coedotzltdv.animolz.db.AnimolzEntitiy
import id.coedotzltdv.animolz.model.Hewan
import id.coedotzltdv.animolz.model.KategoriHewan
import id.coedotzltdv.animolz.model.dataHewan
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PendataanViewModel(private val db: AnimolzDao) : ViewModel() {
    private val hasilData = MutableLiveData<Hewan?>()
    private val navigasi = MutableLiveData<KategoriHewan?>()

    fun getdataHewan(): LiveData<Hewan?> = hasilData

    fun dataHewanAnimolz(pemilik: String, hewan: String, isMale: Boolean) {

        val dataAnimolz = AnimolzEntitiy(
            namaPemilik = pemilik,
            namaHewan = hewan,
            isJenis = isMale

        )
        hasilData.value = dataAnimolz.dataHewan()

        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                db.insert(dataAnimolz)
            }
        }
    }

    fun mulaiNavigasi() {
        navigasi.value = hasilData.value?.kategori
    }

    fun selesaiNavigasi() {
        navigasi.value = null
    }

    fun getNavigasi(): LiveData<KategoriHewan?> = navigasi
}