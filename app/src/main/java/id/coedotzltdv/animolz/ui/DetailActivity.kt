package id.coedotzltdv.animolz.ui

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import id.coedotzltdv.animolz.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)

        //GET INTENT
        val i = this.intent

        //RECEIVE DATA
        val images = i.extras!!.getString("images")
        val name = i.extras!!.getString("title")
        val desc = i.extras!!.getString("desk")

        // Set title bar
        title = name

        //BIND DATA
        binding.images.setImageURI(Uri.parse(images))
        binding.nameTxt.text = name
        binding.detailTxt.text = desc

        Picasso.get()
            .load(images)
           // .resize(600, 900)
            .into(binding.images)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    override fun onBackPressed() {
        this.finish()
    }
}