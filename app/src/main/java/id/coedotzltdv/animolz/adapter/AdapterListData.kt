package id.coedotzltdv.animolz.adapter

import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import id.coedotzltdv.animolz.R
import id.coedotzltdv.animolz.databinding.ItemListPendataanBinding
import id.coedotzltdv.animolz.db.AnimolzEntitiy
import id.coedotzltdv.animolz.model.KategoriHewan
import id.coedotzltdv.animolz.model.dataHewan
import java.text.SimpleDateFormat
import java.util.*

class AdapterListData : ListAdapter<AnimolzEntitiy, AdapterListData.ViewHolder>(DIFF_CALLBACK) {
    companion object {
        private val DIFF_CALLBACK =
            object : DiffUtil.ItemCallback<AnimolzEntitiy>() {
                override fun areItemsTheSame(
                    oldData: AnimolzEntitiy, newData: AnimolzEntitiy
                ): Boolean {
                    return oldData.id == newData.id
                }

                override fun areContentsTheSame(
                    oldData: AnimolzEntitiy, newData: AnimolzEntitiy
                ): Boolean {
                    return oldData == newData
                }
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemListPendataanBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(
        private val binding: ItemListPendataanBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        private val dateFormatter = SimpleDateFormat(
            "dd MMMM yyyy",
            Locale("id", "ID")
        )

        fun bind(item: AnimolzEntitiy) = with(binding) {
            val hasilData = item.dataHewan()
            kategoriTextView.text = hasilData.kategori.toString().substring(0, 1)

            val colorRes = when (hasilData.kategori) {
                KategoriHewan.ANJING -> androidx.constraintlayout.widget.R.color.material_blue_grey_800
                KategoriHewan.KUCING -> com.google.android.material.R.color.m3_ref_palette_dynamic_neutral50
                else -> KategoriHewan.TIDAKADA
            }

            val circleBg = kategoriTextView.background as GradientDrawable
            circleBg.setColor(ContextCompat.getColor(root.context, colorRes as Int))
            tanggalTextView.text = dateFormatter.format(Date(item.tanggal))
            bmiTextView.text =
                root.context.getString(R.string.hasilHewan, hasilData.hewan, hasilData.kategori.toString())

            val gender = root.context.getString(if (item.isJenis) R.string.anjing else R.string.kucing)
            dataTextView.text =
                root.context.getString(R.string.data_x, item.namaPemilik, item.namaHewan, gender)
        }
    }
}