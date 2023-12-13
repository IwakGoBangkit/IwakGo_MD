package com.bangkit.fishery_app.ui.navigation

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object OnBoarding : Screen("onBoarding")
    object Login : Screen("login")
    object Register : Screen("register")
    object ForgotPassword : Screen("forgotPassword")
    object Home : Screen("home")
    object FishItem : Screen("home/{fishName}") {
        fun createRoute(fishName: String) = "home/$fishName"
    }

    object FishScan: Screen("home/scan")

    object FishMenu : Screen("home/{fishName}/{idMenu}") {
        fun createRoute(fishName: String, idMenu: String) = "home/$fishName/$idMenu"
    }

    object CultivationMenuDetail : Screen("home/{fishName}/{idMenu}/{idCultivation}") {
        fun createRoute(fishName: String, idMenu: String, idCultivation: String) =
            "home/$fishName/$idMenu/$idCultivation"
    }

    object ScanResult : Screen("home/scan/result")

    object Market : Screen("market")

    object DetailPost : Screen("market/{idPost}/detail") {
        fun createRoute(idPost: Int) = "market/$idPost/detail"
    }

    object AddPost : Screen("market/addPost")

    object Profile : Screen("profile")

    object ChangeProfile : Screen("profile/edit_profile")

    object ChangeEmail : Screen("profile/edit_email")

    object ChangePassword : Screen("profile/edit_password")

    object PrivacySafety : Screen("profile/privacy_safety")
}