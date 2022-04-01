package id.coedotzltdv.animolz

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.RadioButton
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

        val isKucing = selectedId == R.id.kucingRadioButton
        val isAnjing = selectedId == R.id.anjingRadioButton

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
    private fun DialogExit() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(R.string.app_name)
        builder.setMessage(R.string.exit_dialog)

        // jika user memilih iya
        builder.setPositiveButton(R.string.exit_ya) { dialog, which ->
            // mengakhiri program
            this.finish()
        }

        // jika user memilih tidak
        builder.setNegativeButton(R.string.exit_gak) { dialog, which ->
            // membatalkan keluar dan menghilangkan dialog keluar
            dialog.cancel()
        }

        // menampilkan sebuah dialog
        builder.show()
    }

    // method kembali, dan jika dipencet (tombol kembali dihalaman utama) akan memanggil si dialog keluar
    override fun onBackPressed() {
        DialogExit()
    }
}