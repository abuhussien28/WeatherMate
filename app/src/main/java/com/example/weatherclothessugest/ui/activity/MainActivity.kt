package com.example.weatherclothessugest.ui.activity


import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.weatherapp.models.base.WeatherData
import com.example.weatherclothessugest.R
import com.example.weatherclothessugest.databinding.ActivityMainBinding
import com.example.weatherclothessugest.ui.prsenter.IWeatherView
import com.example.weatherclothessugest.ui.prsenter.WeatherPresenter
import com.example.weatherclothessugest.utils.SharedPrefsUtils
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity(), IWeatherView {
    private lateinit var binding: ActivityMainBinding
    private lateinit var presenter: WeatherPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setup()
    }

    private fun setup() {
        presenter = WeatherPresenter(this)
        presenter.getWeather()
        initPrefs()
    }

    @SuppressLint("SetTextI18n")
    override fun onWeatherSuccess(weatherData: WeatherData) {
        runOnUiThread {
            binding.layoutWeatherCard.apply {
                textViewDate.text = getFormattedDate(weatherData.dt.toString().toLong())
                textViewTemperature.text = "${convertToCelsius(weatherData.main.temp)} °C"
                textViewLocation.text = "${weatherData.sys.country},${weatherData.name}"
                textViewWindSpeed.text = "${weatherData.wind.speed} m/s"
                textViewHumidityCard.text = "${weatherData.main.humidity} %"
                imageViewStatus.setImageResource(getTemperatureIcon(
                    convertToCelsius(weatherData.main.temp).toDouble()))
            }

            binding.layoutClothesCard.apply {
                imageViewClothes.setImageResource(getRandomImageForTemperature(
                    convertToCelsius(weatherData.main.temp).toDouble()))
                nextSuggest.compoundDrawables[2].let { drawable ->
                    if (drawable != null) {
                        drawable.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)
                        binding.layoutClothesCard.nextSuggest.setOnClickListener {
                            updateClothImage(convertToCelsius(weatherData.main.temp).toDouble())
                        }
                    }
                }
            }
        }
    }

    override fun onWeatherFailure(error: String) {
        if (!isConnected()) {
            showNoInternetSnackBar()
        }
    }


    private fun getFormattedDate(timestamp: Long): String {
        val date = Date(timestamp * 1000L)
        return SimpleDateFormat("EEEE dd, MMMM", Locale.getDefault()).format(date)
    }

    private fun convertToCelsius(kelvin: Double): Int {
        return (kelvin - 273.15).toInt()
    }


    private fun getRandomImageForTemperature(temperature: Double): Int {
        val coldImages = listOf(
            R.drawable.winter_one,
            R.drawable.winter_two,
            R.drawable.winter_three,
            R.drawable.winter_four,
            R.drawable.winter_five,
            R.drawable.winter_six
        )
        val normalImages = listOf(R.drawable.summer_one,
            R.drawable.winter_six,
            R.drawable.summer_three,
            R.drawable.winter_two)

        val hotImages = listOf(
            R.drawable.summer_one,
            R.drawable.one,
            R.drawable.summer_three,
            R.drawable.summer_four,
            R.drawable.summer_six
        )

        val images = when {
            temperature <= 15 -> coldImages
            temperature in 16.0..24.0 -> normalImages
            else -> hotImages
        }
        return images.random()
    }

    private fun updateClothImage(currentTemperature: Double) {
        val newImage = getRandomImageForTemperature(currentTemperature)
        binding.layoutClothesCard.imageViewClothes.setImageResource(newImage)
        SharedPrefsUtils.clothName = newImage.toString()
    }

    private fun getTemperatureIcon(temperature: Double): Int {
        return when {
            temperature <= 15 -> R.drawable.cloudy
            temperature in 16.0..24.0 -> R.drawable.normal
            else -> R.drawable.sunny
        }
    }

    private fun isConnected(): Boolean {
        val connectivityManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }

    private fun showNoInternetSnackBar() {
        Snackbar.make(binding.root, "No internet connection", Snackbar.LENGTH_LONG).show()
    }

    private fun initPrefs() = SharedPrefsUtils.initPrefUtil(applicationContext)


}