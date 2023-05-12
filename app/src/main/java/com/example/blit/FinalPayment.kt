package com.example.blit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import com.example.blit.databinding.ActivityFinalPaymentBinding
import java.text.DecimalFormat

class FinalPayment : AppCompatActivity() {
    private lateinit var binding : ActivityFinalPaymentBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        supportActionBar?.hide()
        binding = ActivityFinalPaymentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setTextAmount(50525)

    }
    private fun setTextAmount(money: Int) {
        val format = DecimalFormat("#,###")
        val strMoney = format.format(money)
        binding.totalAmount.text = strMoney + resources.getString(R.string.cash_unit)
    }
}