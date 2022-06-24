package id.coedotzltdv.animolz.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import id.coedotzltdv.animolz.R
import id.coedotzltdv.animolz.core.PicassoClient
import id.coedotzltdv.animolz.model.Artikel
import id.coedotzltdv.animolz.ui.DetailActivity

class AdapterArticle(var context: Context) : RecyclerView.Adapter<AdapterArticle.MyViewHolder>() {

    var artikelList: List<Artikel> = listOf()
    var artikelListFiltered: List<Artikel> = listOf()

    fun setArtikelList(context: Context, artikelList: List<Artikel>) {
        this.context = context
        if (artikelList == null) {
            this.artikelList = artikelList
            this.artikelListFiltered = artikelList
            notifyItemChanged(0, artikelListFiltered.size)
        } else {
            val result = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
                override fun getOldListSize(): Int {
                    return this@AdapterArticle.artikelList.size
                }

                override fun getNewListSize(): Int {
                    return artikelList.size
                }

                override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                    return this@AdapterArticle.artikelList.get(oldItemPosition)
                        .title === artikelList[newItemPosition].title
                }

                override fun areContentsTheSame(
                    oldItemPosition: Int,
                    newItemPosition: Int
                ): Boolean {
                    val newBook: Artikel = this@AdapterArticle.artikelList.get(oldItemPosition)
                    val oldBook: Artikel = artikelList[newItemPosition]
                    return newBook.title === oldBook.title
                }
            })
            this.artikelList = artikelList
            this.artikelListFiltered = artikelList
            result.dispatchUpdatesTo(this)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list_article, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: AdapterArticle.MyViewHolder, position: Int) {
        holder.titleBook!!.text = artikelListFiltered.get(position).title

        val artikel: Artikel = artikelListFiltered.get(position)

        val images: String = artikel.images
        val judul: String = artikel.title
        val isi: String = artikel.isi

        // Bind
        holder.image!!.setImageURI((Uri.parse(images)))
        holder.titleBook!!.text = judul
        holder.descBook!!.text = isi

        // Library picasso for handling cache image & when data cant load
        PicassoClient.downloadImage(context, artikelListFiltered.get(position).images, holder.image)

        holder.setItemClickListener(object : ItemClickListener {
            override fun onItemClick(pos: Int) {
                openDetailActivity(images, judul, isi)
            }
        })
    }

    override fun getItemCount(): Int {
        return if (artikelList != null) {
            artikelListFiltered.size
        } else {
            0
        }
    }

    // open activity
    private fun openDetailActivity(vararg details: String) {
        val i = Intent(context, DetailActivity::class.java)
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        i.putExtra("images", details[0])
        i.putExtra("title", details[1])
        i.putExtra("desk", details[2])
        context.startActivity(i)
    }

    fun getFilter(): Filter? {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence): FilterResults {
                val charString = charSequence.toString()
                if (charString.isEmpty()) {
                    artikelListFiltered = artikelList
                } else {
                    val filteredList: MutableList<Artikel> = ArrayList<Artikel>()
                    for (books in artikelList) {
                        if (books.title.lowercase().contains(charString.lowercase())) {
                            filteredList.add(books)
                        }
                    }
                    artikelListFiltered = filteredList
                }
                val filterResults = FilterResults()
                filterResults.values = artikelListFiltered
                return filterResults
            }

            override fun publishResults(charSequence: CharSequence, filterResults: FilterResults) {
                artikelListFiltered = filterResults.values as ArrayList<Artikel>
                notifyDataSetChanged()
            }
        }
    }

    fun setArtikelListItems(artikelList: List<Artikel>) {
        this.artikelList = artikelList
        notifyDataSetChanged()
    }

    interface ItemClickListener {
        fun onItemClick(pos: Int)
    }

    inner class MyViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!),
        View.OnClickListener {

        private lateinit var itemClickListener: ItemClickListener

        var image: ImageView? = null
        var titleBook: TextView? = null
        var descBook: TextView? = null

        init {
            image = itemView!!.findViewById<View>(R.id.imageView) as ImageView
            titleBook = itemView.findViewById<View>(R.id.title) as TextView
            descBook = itemView.findViewById<View>(R.id.isi) as TextView
            itemView.setOnClickListener(this)
        }

        override fun onClick(view: View) {
            this.itemClickListener.onItemClick(this.layoutPosition)
        }

        fun setItemClickListener(itemClickListener: ItemClickListener) {
            this.itemClickListener = itemClickListener
        }
    }
}