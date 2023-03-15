package com.ismail.creatvt.currency.ui.currencyconverter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.databinding.DataBindingUtil
import com.ismail.creatvt.currency.R
import com.ismail.creatvt.currency.databinding.ActivityCurrencyConversionBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CurrencyConversionActivity : AppCompatActivity() {

    private val viewModel: CurrencyConversionViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        val binding: ActivityCurrencyConversionBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_currency_conversion)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        viewModel.error.observe(this) {
            if(it != null) {
                Toast.makeText(this@CurrencyConversionActivity, it, Toast.LENGTH_LONG).show()
            }
        }
        if(savedInstanceState == null) {
            viewModel.loadCurrencies()
        }
    }
}