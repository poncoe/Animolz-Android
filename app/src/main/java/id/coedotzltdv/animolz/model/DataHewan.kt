package id.coedotzltdv.animolz.model

import id.coedotzltdv.animolz.db.AnimolzEntitiy

fun AnimolzEntitiy.dataHewan(): Hewan {
    val hewan = namaHewan
    val kategori = when {
        isJenis -> {
            when {
                isJenis.equals("Anjing") -> KategoriHewan.ANJING
                isJenis.equals("Kucing") -> KategoriHewan.KUCING
                else -> KategoriHewan.TIDAKADA
            }
        }
        else -> {KategoriHewan.TIDAKADA}
    }
    return Hewan(hewan, kategori)
}