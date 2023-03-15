package com.ismail.creatvt.currency.ui.currencyconverter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.databinding.DataBindingUtil
import com.ismail.creatvt.currency.R
import com.ismail.creatvt.currency.databinding.ActivityCurrencyConversionBinding
import com.ismail.creatvt.currency.ui.details.DetailsActivity
import com.ismail.creatvt.currency.utils.CURRENCY_VALUE
import com.ismail.creatvt.currency.utils.FROM_CURRENCY
import com.ismail.creatvt.currency.utils.TO_CURRENCY
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

        viewModel.navigateToDetails = { from, to, value ->
            startActivity(Intent(this@CurrencyConversionActivity, DetailsActivity::class.java).apply {
                putExtra(FROM_CURRENCY, from)
                putExtra(TO_CURRENCY, to)
                putExtra(CURRENCY_VALUE, value)
            })
        }

        viewModel.error.observe(this) {
            if(it != null) {
                Toast.makeText(this@CurrencyConversionActivity, it, Toast.LENGTH_LONG).show()
            }
        }
        if(savedInstanceState == null) {
            viewModel.loadCurrencies()
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        viewModel.navigateToDetails = null
    }
}