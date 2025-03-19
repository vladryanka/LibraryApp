package com.smorzhok.libraryapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.smorzhok.libraryapp.presentation.screens.MainScreen

@Composable
fun AppNavigationGraph(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = MainScreen()
    ) {
        /*composable < MainScreen() > {
            MainScreen(
                navController.navigate(route = "SignInDestination")
            )
        }*/
        /* composable(
             route = Route.PostDetails().routeWithArgs,
             arguments = listOf(navArgument(name = Route.PostDetails.POST_ID) {
                 type = NavType.IntType
             })
         ) {
             PostDetailsScreen(
                 modifier = modifier,
                 onNavigateUp = { navController.navigateUp() },
             )
         }*/
    }
}