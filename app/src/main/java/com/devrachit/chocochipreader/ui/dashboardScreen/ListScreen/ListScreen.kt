package com.devrachit.chocochipreader.ui.dashboardScreen.ListScreen

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
@ExperimentalMaterial3Api
@Composable
fun ListScreen(navController: NavController) {

    val viewModel = hiltViewModel<ListScreenViewModel>()
    var selected by remember { mutableStateOf(1) }
    val onItemClick: (Int) -> Unit = {
        selected = it
        viewModel.checkAttendance("day$it")
    }
    val loading = viewModel.loading.collectAsStateWithLifecycle()
    val apiCallMade = viewModel.apiCallMade.collectAsStateWithLifecycle()
    LaunchedEffect(key1 = true) {
        viewModel.checkAttendance("day1")
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        contentPadding = PaddingValues(5.dp),
    )
    {
        item { ProfileNavigation(selected = selected, onItemSelected = onItemClick) }

        when (selected) {
            1 -> {
                if (loading.value) {
                    items(1) {
                        Text(text = "Loading...")
                    }
                } else if(apiCallMade.value) {
                    items(viewModel.sharedViewModel.dataList.value!!.size) {
                        Text(text = viewModel.sharedViewModel.dataList.value!![it].name)
                    }
                }
            }

            2 -> {
                if (loading.value) {
                    items(1) {
                        Text(text = "Loading...")
                    }
                } else if(apiCallMade.value) {
                    items(viewModel.sharedViewModel.dataList.value!!.size) {
                        Text(text = viewModel.sharedViewModel.dataList.value!![it].name)
                    }
                }
            }

            3 -> {
                if (loading.value) {
                    items(1) {
                        Text(text = "Loading...")
                    }
                } else if(apiCallMade.value) {
                    items(viewModel.sharedViewModel.dataList.value!!.size) {
                        Text(text = viewModel.sharedViewModel.dataList.value!![it].name)
                    }
                }
            }

            4 -> {
                if (loading.value) {
                    items(1) {
                        Text(text = "Loading...")
                    }
                } else if(apiCallMade.value) {
                    items(viewModel.sharedViewModel.dataList.value!!.size) {
                        Text(text = viewModel.sharedViewModel.dataList.value!![it].name)
                    }
                }
            }
        }


    }
}