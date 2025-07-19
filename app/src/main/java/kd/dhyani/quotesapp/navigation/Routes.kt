package kd.dhyani.quotesapp.navigation

sealed class Routes(val route: String) {

    object Home : Routes("home")

    data class MainCard(val title: String, val writer: String) :
        Routes("mainCard/{$title}/{$writer}") {
        companion object {
            fun createRoute(title: String, writer: String): String =
                "mainCard/${title}/${writer}"
        }
    }

    object Favorite : Routes("favorite")
}
