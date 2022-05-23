package id.coedotzltdv.animolz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import id.coedotzltdv.animolz.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // binding proses
        binding.btnProses.setOnClickListener {
            DataAnimolz()
        }

        // binding reset
        binding.btnReset.setOnClickListener {
            DataReset()
        }
    }

    private fun DataAnimolz() {
        // mengambil data inputan

        val nama = binding.namaInp.text.toString()
        val namaHewan = binding.namaHewanInp.text.toString()
        val selectedId = binding.radioGroup.checkedRadioButtonId

        // menggabungkan data hewan
        val animolzKucing =
            "Nama Anda Adalah $nama, Memiliki Hewan Peliharaan bernama $namaHewan dan jenis peliharaannya adalah Kucing"
        val animolzAnjing =
            "Nama Anda Adalah $nama, Memiliki Hewan Peliharaan bernama $namaHewan dan jenis peliharaannya adalah Anjing"

        // melakukan pengecekan input dan jika data terisi atau tidak

        when {
            // pengecekan jika inputan nama kosong
            TextUtils.isEmpty(nama) -> {
                Toast.makeText(this, R.string.nama_invalid, Toast.LENGTH_LONG).show()
                return
            }
            // pengecekan jika inputan nama hewan kosong
            TextUtils.isEmpty(namaHewan) -> {
                Toast.makeText(this, R.string.namaHewan_invalid, Toast.LENGTH_LONG).show()
                return
            }
            // pengecekan jika inputan jenis hewan kosong
            selectedId == -1 -> {
                Toast.makeText(this, R.string.jenis_invalid, Toast.LENGTH_LONG).show()
                return
            }
            // pengecekan jika user memilih input kucing
            selectedId == R.id.kucingRadioButton -> {
                // mengset gambar kucing
                binding.gambarHewan.setImageResource(R.drawable.kucing)
                // membuat gambar kucing terlihat
                binding.gambarHewan.visibility = VISIBLE
                // mengset text kucing
                binding.txtDataAnimolz.text = animolzKucing
                // membuat text kucing terlihat
                binding.txtDataAnimolz.visibility = VISIBLE
                return
            }
            // pengecekan jika user memilih input anjing
            selectedId == R.id.anjingRadioButton -> {
                // mengset gambar anjing
                binding.gambarHewan.setImageResource(R.drawable.anjing)
                // membuat gambar anjing terlihat
                binding.gambarHewan.visibility = VISIBLE
                // mengset text anjing
                binding.txtDataAnimolz.text = animolzAnjing
                // membuat text anjing terlihat
                binding.txtDataAnimolz.visibility = VISIBLE
                return
            }
        }
    }

    // reset data
    private fun DataReset() {
        binding.namaInp.text?.clear()
        binding.namaHewanInp.text?.clear()
        binding.radioGroup.clearCheck()
        binding.txtDataAnimolz.text = ""
        binding.gambarHewan.visibility = GONE
    }

    // membuat dialog jika ingin keluar dari aplikasi
    var exitTime: Long = 0
    private fun DialogExit() {
        if (System.currentTimeMillis() - exitTime > 2000) {
            Toast.makeText(this, R.string.press_again_exit_app, Toast.LENGTH_SHORT).show()
            exitTime = System.currentTimeMillis()
        } else {
            finish()
        }
    }

    // method kembali, dan jika dipencet (tombol kembali dihalaman utama) akan memanggil si dialog toast keluar
    override fun onBackPressed() {
        DialogExit()
    }
}