package id.coedotzltdv.animolz.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import id.coedotzltdv.animolz.R
import id.coedotzltdv.animolz.databinding.FragmentAboutBinding

class AboutFragment: Fragment() {

    private lateinit var binding: FragmentAboutBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAboutBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnShare.setOnClickListener {
            val sharingIntent = Intent(Intent.ACTION_SEND)
            sharingIntent.type = "text/plain"
            sharingIntent.putExtra(Intent.EXTRA_SUBJECT, requireActivity().getString(R.string.app_name))
            sharingIntent.putExtra(Intent.EXTRA_TEXT, "Animolz Aplikasi Hewan!\n\nUnduh Sekarang!\nhttp://play.google.com/store/apps/details?id=id.coedotzltdv.animolz")
            requireActivity().startActivity(Intent.createChooser(sharingIntent, "Bagikan Melalui"))
        }
    }

}