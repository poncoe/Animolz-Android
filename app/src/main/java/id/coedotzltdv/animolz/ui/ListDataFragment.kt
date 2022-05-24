package id.coedotzltdv.animolz.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import id.coedotzltdv.animolz.R
import id.coedotzltdv.animolz.adapter.AdapterListData
import id.coedotzltdv.animolz.databinding.FragmentListdataBinding
import id.coedotzltdv.animolz.db.AnimolzDb
import id.coedotzltdv.animolz.viewmodel.ListDataViewModel
import id.coedotzltdv.animolz.viewmodel.factory.ListDataViewModelFactory

class ListDataFragment: Fragment() {
    private lateinit var binding: FragmentListdataBinding
    private lateinit var myAdapter: AdapterListData

    private val viewModel: ListDataViewModel by lazy {
        val db = AnimolzDb.getInstance(requireContext())
        val factory = ListDataViewModelFactory(db.dao)
        ViewModelProvider(this, factory)[ListDataViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListdataBinding.inflate(
            layoutInflater, container, false
        )
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        myAdapter = AdapterListData()
        with(binding.recyclerView) {
            addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))
            adapter = myAdapter
            setHasFixedSize(true)
        }

        viewModel.data.observe(viewLifecycleOwner, {
//            Log.d("HistoriFragment", "Jumlah data: ${it.size}")
            binding.emptyView.visibility = if (it.isEmpty())
                View.VISIBLE else View.GONE
            myAdapter.submitList(it)
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.list_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_hapus) {
            hapusData()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun hapusData() { MaterialAlertDialogBuilder(requireContext())
        .setMessage(R.string.konfirmasi_hapus)
        .setPositiveButton(getString(R.string.hapus)) { _, _ ->
            viewModel.hapusData()
        }
        .setNegativeButton(getString(R.string.batal)) { dialog, _ ->
            dialog.cancel()
        }
        .show()
    }
}