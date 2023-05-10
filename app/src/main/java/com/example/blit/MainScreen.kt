package com.example.blit

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.DisplayMetrics
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.TextView
import androidx.core.view.updateLayoutParams
import androidx.drawerlayout.widget.DrawerLayout.LayoutParams
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.blit.databinding.ActivityMainScreenBinding

class MainScreen : AppCompatActivity() {
    private lateinit var binding:ActivityMainScreenBinding
    private val adapter: RecyclerViewAdapter by lazy {
        RecyclerViewAdapter().apply {
            this.dataList = mDatas
        }
    }
    val mDatas= mutableListOf<ProfileData>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        supportActionBar?.hide()
        binding = ActivityMainScreenBinding.inflate(layoutInflater)

        binding.textLabelTotalAmount.addTextChangedListener(
            object: TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
                override fun afterTextChanged(p0: Editable?) {
                    binding.textHighlight.layoutParams.width = binding.textLabelTotalAmount.paint.measureText(binding.textLabelTotalAmount.text.toString()).toInt()
                }

            }
        )
        binding.calculateButton.setOnClickListener {
            val intent = Intent(this, SplitTheBill::class.java)
            startActivity(intent)
        }
        setContentView(binding.root)

        initList()
        initRecyclerView()
        setTextTotalAmount()
    }

    private fun setTextTotalAmount() {
//        binding.textTotalAmount.text = px2dp(getStatusBarHeight(this), this).toString()
        binding.textLabelTotalAmount.text = "123,456" + " 원"
    }

    fun getStatusBarHeight(context: Context): Int {
        var statusBarHeight = 0
        val resourceId: Int = context.resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0) {
            statusBarHeight = resources.getDimensionPixelSize(resourceId)
            return statusBarHeight
        }
        return 0
    }

    fun px2dp(px: Int, context: Context): Float {
        return px / ((context.resources.displayMetrics.densityDpi.toFloat()) / DisplayMetrics.DENSITY_DEFAULT)
    }
    private fun initRecyclerView() {

        binding.recyclerView.apply {
            this.layoutManager = LinearLayoutManager(this@MainScreen, LinearLayoutManager.HORIZONTAL, false)
            this.adapter = this@MainScreen.adapter
            this.addItemDecoration(ItemDecoration(30))
        }
    }

    private fun initList(){
        with(mDatas) {
            add(ProfileData("@mipmap/profile_image_foreground"))
            add(ProfileData("@mipmap/profile_image_foreground"))
            add(ProfileData("@mipmap/profile_image_foreground"))
            add(ProfileData("@mipmap/profile_image_foreground"))
            add(ProfileData("@mipmap/profile_image_foreground"))
            add(ProfileData("@mipmap/profile_image_foreground"))
        }
    }

}