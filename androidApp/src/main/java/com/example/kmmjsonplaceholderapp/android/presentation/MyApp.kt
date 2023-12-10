package com.example.kmmjsonplaceholderapp.android.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.kmmjsonplaceholderapp.android.presentation.detail.DetailPage
import com.example.kmmjsonplaceholderapp.android.presentation.detail.DetailViewModel
import com.example.kmmjsonplaceholderapp.android.presentation.home.HomePage
import com.example.kmmjsonplaceholderapp.android.presentation.home.HomeViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun MyApp() {
    val navController = rememberNavController()


    NavHost(
        navController = navController,
        startDestination = "home"
    ){
        composable("home"){
            val homeViewModel: HomeViewModel = koinViewModel()
            val state by homeViewModel.state.collectAsStateWithLifecycle()

            HomePage(
                state = state,
                onNavigateToDetail = {
                    navController.navigateToDetail(it)
                },
                onEvent = homeViewModel::onEvent
            )
        }

        composable(
            "detail/{userId}",
            arguments = listOf(
                navArgument("userId"){ type = NavType.IntType }
            )
        ){
            val detailViewModel: DetailViewModel = koinViewModel()
            val state by detailViewModel.state.collectAsStateWithLifecycle()

            DetailPage(
                state = state,
                onEvent = detailViewModel::onEvent
            )
        }

    }

}

private fun NavController.navigateToDetail(userId: Int){
    navigate("detail/$userId")
}
