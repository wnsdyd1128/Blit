package com.example.blit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.blit.databinding.ActivitySplitTheBillBinding
import com.yuyakaido.android.cardstackview.CardStackLayoutManager
import com.yuyakaido.android.cardstackview.Direction
import com.yuyakaido.android.cardstackview.StackFrom

class SplitTheBill : AppCompatActivity() {
    private lateinit var binding : ActivitySplitTheBillBinding
    private val mStageDatas = mutableListOf<StageData>()
    private val mPurchaseDatas = mutableListOf<PurchaseData>()
    private val stageAdapter: StageRecyclerViewAdapter by lazy {
        StageRecyclerViewAdapter().apply {
            this.dataList = mStageDatas
        }
    }
    private val purchaseAdapter: PurchaseRecyclerViewAdapter by lazy {
        PurchaseRecyclerViewAdapter().apply {
            this.dataList = mPurchaseDatas
        }
    }
    private val manager: CardStackLayoutManager by lazy {
        CardStackLayoutManager(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        supportActionBar?.hide()
        binding = ActivitySplitTheBillBinding.inflate(layoutInflater)

        binding.nextButton.setOnClickListener {
            val intent = Intent(this, FinalPayment::class.java)
            startActivity(intent)
        }
        setContentView(binding.root)

        initList()
        initRecyclerView()
    }

    private fun initRecyclerView() {
        binding.StageRecyclerView.apply {
            this.layoutManager = LinearLayoutManager(this@SplitTheBill, LinearLayoutManager.HORIZONTAL, false)
            this.adapter = this@SplitTheBill.stageAdapter
            this.addItemDecoration(ItemDecoration(100))
        }
        binding.purchaseListView.apply {
            manager.setDirections(Direction.HORIZONTAL)
            manager.setCanScrollVertical(false)
            manager.setScaleInterval(0.9f)
            manager.setStackFrom(StackFrom.Right)
            this.layoutManager = manager
            this.adapter = this@SplitTheBill.purchaseAdapter
        }
    }

    private fun initList(){
        with(mStageDatas) {
            add(StageData("1차", "컴포즈 카페"))
            add(StageData("2차", "북경 깐풍기"))
            add(StageData("3차", "준코 노래방"))
        }
        with(mPurchaseDatas) {
            add(PurchaseData("", "아이스", "아메리카노1"))
            add(PurchaseData("", "아이스", "아메리카노2"))
            add(PurchaseData("", "아이스", "아메리카노3"))
        }
    }

}