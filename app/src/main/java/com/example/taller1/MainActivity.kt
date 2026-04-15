package com.example.taller1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.taller1.ViewModel.PerfilViewModel
import com.example.taller1.ui.theme.InterestScreen
import com.example.taller1.ui.theme.PerfilScreen
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                val viewModel: PerfilViewModel = viewModel()
                val tabs = listOf("Perfil", "Hobbies", "Deportes","Intereses")
                val pagerState = rememberPagerState(pageCount = {tabs.size})
                val scope = rememberCoroutineScope()

                Column(modifier = Modifier.fillMaxSize()) {
                    TabRow(selectedTabIndex = pagerState.currentPage) {
                        tabs.forEachIndexed { index, title ->
                            Tab(
                                selected = pagerState.currentPage == index,
                                onClick = {
                                    scope.launch { pagerState.animateScrollToPage(index) }
                                    viewModel.onTabSelected(index)
                                },
                                text = {Text(title)}

                            )
                        }
                    }
                    HorizontalPager(
                        state = pagerState,
                        modifier = Modifier.fillMaxSize()
                    ) {
                            page ->
                        when (page) {
                            0 -> PerfilScreen(viewModel)
                            else -> InterestScreen(
                                title = tabs[page],
                                items = when (page) {
                                    1 -> viewModel.interests.hobbies + viewModel.interests.pastimes
                                    2 -> viewModel.interests.sports
                                    3 -> viewModel.interests.interest
                                    else -> emptyList()
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}
