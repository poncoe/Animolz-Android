package id.coedotzltdv.animolz.ui

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import id.coedotzltdv.animolz.R
import id.coedotzltdv.animolz.databinding.FragmentPendataanBinding

class PendataanFragment : Fragment() {

    private lateinit var binding: FragmentPendataanBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPendataanBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
                Toast.makeText(context, R.string.nama_invalid, Toast.LENGTH_LONG).show()
                return
            }
            // pengecekan jika inputan nama hewan kosong
            TextUtils.isEmpty(namaHewan) -> {
                Toast.makeText(context, R.string.namaHewan_invalid, Toast.LENGTH_LONG).show()
                return
            }
            // pengecekan jika inputan jenis hewan kosong
            selectedId == -1 -> {
                Toast.makeText(context, R.string.jenis_invalid, Toast.LENGTH_LONG).show()
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
}