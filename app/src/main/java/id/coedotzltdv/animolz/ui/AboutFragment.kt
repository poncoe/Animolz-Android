package id.coedotzltdv.animolz.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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

        binding.btnSaran.setOnClickListener {

        }
    }
}