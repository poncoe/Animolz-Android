package id.coedotzltdv.animolz.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import id.coedotzltdv.animolz.databinding.ActivityIsenkdoankBinding

class IsenkDoank : AppCompatActivity() {

    private lateinit var binding: ActivityIsenkdoankBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityIsenkdoankBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        this.finish()
        return super.onSupportNavigateUp()
    }
}