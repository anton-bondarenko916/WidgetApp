package com.eligon.widget

import android.content.Context
import android.content.SharedPreferences
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ActionProvider
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.eligon.widget.databinding.ActivityMainBinding
import com.eligon.widget.model.PriceWithTime
import com.eligon.widget.repository.Repository
import com.eligon.widget.utils.Constants
import com.google.gson.Gson
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {

    private lateinit var repository: Repository
    private lateinit var viewModel: MainViewModel
    private lateinit var mSharedPreferences: SharedPreferences

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            progressBar.visibility = View.VISIBLE
            tvGold.visibility = View.INVISIBLE
            tvGoldPrice.visibility = View.INVISIBLE
            tvSilver.visibility = View.INVISIBLE
            tvSilverPrice.visibility = View.INVISIBLE
            tvPlatinum.visibility = View.INVISIBLE
            tvPlatinumPrice.visibility = View.INVISIBLE
            tvPalladium.visibility = View.INVISIBLE
            tvPalladiumPrice.visibility = View.INVISIBLE
        }

        repository = Repository()
        mSharedPreferences = getSharedPreferences(Constants.APP_PREFERENCES, Context.MODE_PRIVATE)

        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        viewModel.getMetals()
        viewModel.myResponse.observe(this, Observer { response ->
            val gson = Gson()
            val gold = gson.fromJson(response[0][0], PriceWithTime::class.java)
            val silver = gson.fromJson(response[1][0], PriceWithTime::class.java)
            val platinum = gson.fromJson(response[2][0], PriceWithTime::class.java)
            val palladium = gson.fromJson(response[3][0], PriceWithTime::class.java)

            binding.tvGoldPrice.text = "${gold.price}$"
            binding.tvSilverPrice.text = "${silver.price}$"
            binding.tvPlatinumPrice.text = "${platinum.price}$"
            binding.tvPalladiumPrice.text = "${palladium.price}$"

            mSharedPreferences.edit().putString(Constants.GOLD, gold.price).apply()
            mSharedPreferences.edit().putString(Constants.SILVER, silver.price).apply()
            mSharedPreferences.edit().putString(Constants.PLATINUM, platinum.price).apply()
            mSharedPreferences.edit().putString(Constants.PALLADIUM, palladium.price).apply()
            mSharedPreferences.edit().putLong(Constants.DATE, gold.timestamp).apply()

            with(binding) {
                progressBar.visibility = View.GONE
                tvGold.visibility = View.VISIBLE
                tvGoldPrice.visibility = View.VISIBLE
                tvSilver.visibility = View.VISIBLE
                tvSilverPrice.visibility = View.VISIBLE
                tvPlatinum.visibility = View.VISIBLE
                tvPlatinumPrice.visibility = View.VISIBLE
                tvPalladium.visibility = View.VISIBLE
                tvPalladiumPrice.visibility = View.VISIBLE
            }
        })

    }
}