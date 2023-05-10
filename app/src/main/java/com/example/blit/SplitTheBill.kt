package com.example.blit

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import com.example.blit.databinding.ActivitySplitTheBillBinding
import java.text.DecimalFormat

class SplitTheBill : AppCompatActivity() {
    private lateinit var binding : ActivitySplitTheBillBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        supportActionBar?.hide()
        binding = ActivitySplitTheBillBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setTextAmount(50525)

    }
    private fun setTextAmount(money: Int) {
        val format = DecimalFormat("#,###")
        val strMoney = format.format(money)
        binding.totalAmount.text = strMoney + resources.getString(R.string.cash_unit)
    }

}