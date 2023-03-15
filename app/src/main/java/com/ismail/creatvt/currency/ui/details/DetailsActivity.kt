package com.ismail.creatvt.currency.ui.details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ismail.creatvt.currency.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
    }
}