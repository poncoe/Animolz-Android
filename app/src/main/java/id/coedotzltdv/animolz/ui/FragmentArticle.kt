package id.coedotzltdv.animolz.ui

import android.app.SearchManager
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.facebook.shimmer.ShimmerFrameLayout
import com.google.android.material.snackbar.Snackbar
import id.coedotzltdv.animolz.R
import id.coedotzltdv.animolz.adapter.AdapterArticle
import id.coedotzltdv.animolz.api.ApiArtikel
import id.coedotzltdv.animolz.databinding.FragmentArticleBinding
import id.coedotzltdv.animolz.model.Artikel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FragmentArticle : Fragment() {

    private lateinit var binding: FragmentArticleBinding
    lateinit var recyclerView: RecyclerView
    lateinit var recyclerAdapter: AdapterArticle
    private var shimmer: ShimmerFrameLayout? = null
    lateinit var searchView: SearchView
    lateinit var artikelList: List<Artikel>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentArticleBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = binding.recyclerview
        shimmer = binding.shimmerHome
        recyclerAdapter = AdapterArticle(requireContext())
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = recyclerAdapter

        artikelList = ArrayList<Artikel>()
        val apiInterface = ApiArtikel.create().getArtikel()

        //apiInterface.enqueue( Callback<List<Artikel>>())
        apiInterface.enqueue( object : Callback<List<Artikel>> {
            override fun onResponse(call: Call<List<Artikel>>?, response: Response<List<Artikel>>?) {

//                if (response?.body() != null)
//                    recyclerAdapter.setArtikelListItems(response.body()!!)

                // check if data empty or not

                if(response?.body()?.isEmpty() == true) { // if data null
                    binding.recyclerview.visibility = View.GONE
                    binding.emptyView.visibility = View.VISIBLE

                    //shimmer
                    shimmer!!.stopShimmer()
                    shimmer!!.visibility = View.GONE
                } else { // data not null
                    binding.emptyView.visibility = View.GONE

                    artikelList = response!!.body()!!
                    Log.d("TAG", "Response = $artikelList")
                    recyclerAdapter.setArtikelList(requireContext(), artikelList)

                    //shimmer
                    shimmer!!.stopShimmer()
                    shimmer!!.visibility = View.GONE
                }
            }

            override fun onFailure(call: Call<List<Artikel>>?, t: Throwable?) {
                val snackbar = Snackbar.make(view, getString(R.string.failed_load_data_api),
                    Snackbar.LENGTH_LONG).setAction("OK", null)
                val snackbarView = snackbar.view
                val textView =
                    snackbarView.findViewById(com.google.android.material.R.id.snackbar_text) as TextView
                textView.setTextColor(Color.BLACK)
                snackbar.show()
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        requireActivity().menuInflater.inflate(R.menu.main_menu, menu)
        val searchManager = requireActivity().getSystemService(AppCompatActivity.SEARCH_SERVICE) as SearchManager
        searchView = menu.findItem(R.id.action_search)
            .actionView as SearchView
        searchView.setSearchableInfo(
            searchManager
                .getSearchableInfo(requireActivity().componentName)
        )
        searchView.maxWidth = Int.MAX_VALUE
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                recyclerAdapter.getFilter()?.filter(query)
                return false
            }

            override fun onQueryTextChange(query: String): Boolean {
                recyclerAdapter.getFilter()?.filter(query)
                return false
            }
        })
//        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        return if (id == R.id.action_search) {
            true
        } else super.onOptionsItemSelected(item)
    }

    override fun onResume() {
        super.onResume()
        shimmer!!.startShimmer()
    }

    override fun onPause() {
        shimmer!!.stopShimmer()
        super.onPause()
    }

//    override fun onBackPressed() {
//        if (!searchView.isIconified) {
//            searchView.isIconified = true
//            return
//        }
//        super.requireActivity().onBackPressed()
//    }

}