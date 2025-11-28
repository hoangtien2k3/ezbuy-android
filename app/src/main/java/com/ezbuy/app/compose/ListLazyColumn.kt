package com.ezbuy.app.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.ezbuy.app.compose.component.GetProductList
import com.ezbuy.app.compose.ui.theme.EzbuyAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListLazyColumn : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EzbuyAppTheme {
                GetProductList()
            }
        }
    }
}
