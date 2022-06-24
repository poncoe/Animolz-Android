package id.coedotzltdv.animolz.api

import id.coedotzltdv.animolz.model.Artikel
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiArtikel {

    @GET("data-json-meonk.php")
    fun getArtikel() : Call<List<Artikel>>

    companion object {

        var BASE_URL = "https://api.luckytruedev.com/ltdv-admin/admin2/"

        fun create() : ApiArtikel {

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(ApiArtikel::class.java)

        }
    }
}