package kd.dhyani.quotesapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kd.dhyani.quotesapp.screens.FavScreen
import kd.dhyani.quotesapp.screens.HomePage
import kd.dhyani.quotesapp.screens.MaxCard

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.Home.route
    ) {
        composable(Routes.Home.route) {
            HomePage(
                onNavigateToMainCard = { title, writer ->
                    navController.navigate(Routes.MainCard.createRoute(title, writer))
                },
                onNavigateToFavorites = {
                    navController.navigate(Routes.Favorite.route)
                }
            )
        }

        composable(
            route = "mainCard/{title}/{writer}"
        ) { backStackEntry ->
            val title = backStackEntry.arguments?.getString("title") ?: ""
            val writer = backStackEntry.arguments?.getString("writer") ?: ""
            MaxCard(
                title = title,
                writer = writer,
                onNavigateBack = { navController.popBackStack() }
            )
        }

        // ✅ Define Favorite route only ONCE with both callbacks
        composable(Routes.Favorite.route) {
            FavScreen(
                onNavigateBack = { navController.popBackStack() },
                onNavigateToMainCard = { title, writer ->
                    navController.navigate(Routes.MainCard.createRoute(title, writer))
                }
            )
        }
    }
}
