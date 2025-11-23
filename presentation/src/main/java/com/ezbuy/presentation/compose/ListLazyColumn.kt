package com.ezbuy.presentation.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.ezbuy.presentation.compose.component.GetProductList
import com.ezbuy.presentation.compose.ui.theme.ProductAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListLazyColumn : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProductAppTheme() {
                GetProductList()
            }
        }
    }
}