package com.bangkit.fishery.ui.navigation

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object OnBoarding : Screen("onBoarding")
    object Login : Screen("login")
    object Register : Screen("register")
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

    object ScanPreview : Screen("home/camera/preview")

    object Market : Screen("market")

    object DetailPost : Screen("market/{idPost}/detail") {
        fun createRoute(idPost: String) = "market/$idPost/detail"
    }

    object AddPost : Screen("market/addPost")

    object Profile : Screen("profile")

    object ChangeProfile : Screen("profile/edit_profile") {
        fun createRoute(title: String) = "profile/$title"
    }

    object ChangeEmail : Screen("profile/edit_email") {
        fun createRoute(title: String) = "profile/$title"
    }

    object ChangePassword : Screen("profile/edit_password") {
        fun createRoute(title: String) = "profile/$title"
    }

    object PrivacySafety : Screen("profile/privacy_safety") {
        fun createRoute(title: String) = "profile/$title"
    }
}