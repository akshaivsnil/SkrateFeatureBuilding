package com.akshai.skratefeaturebuilding

import android.os.Bundle
import com.akshai.skratefeaturebuilding.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SingleActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single)


    }
}