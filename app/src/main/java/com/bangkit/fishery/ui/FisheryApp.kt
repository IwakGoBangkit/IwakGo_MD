package com.bangkit.fishery.ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.bangkit.fishery.R
import com.bangkit.fishery.ui.navigation.Screen
import com.bangkit.fishery.ui.navigation.bottomNav.BottomNavItem
import com.bangkit.fishery.ui.screen.add_post.AddPostScreen
import com.bangkit.fishery.ui.screen.authentication.login.LoginScreen
import com.bangkit.fishery.ui.screen.authentication.register.RegisterScreen
import com.bangkit.fishery.ui.screen.boarding.OnBoardingScreen
import com.bangkit.fishery.ui.screen.change_email.ChangeEmailScreen
import com.bangkit.fishery.ui.screen.change_password.ChangePasswordScreen
import com.bangkit.fishery.ui.screen.change_profile.ChangeProfileScreen
import com.bangkit.fishery.ui.screen.detail_post.DetailPostScreen
import com.bangkit.fishery.ui.screen.feed_recommendation.FeedRecommendationScreen
import com.bangkit.fishery.ui.screen.fish_disease.FishDiseaseScreen
import com.bangkit.fishery.ui.screen.fish_item.FishItemScreen
import com.bangkit.fishery.ui.screen.fish_menu.FishMenuScreen
import com.bangkit.fishery.ui.screen.harvest.HarvestScreen
import com.bangkit.fishery.ui.screen.home.HomeScreen
import com.bangkit.fishery.ui.screen.home.model.ImageResult
import com.bangkit.fishery.ui.screen.market.MarketScreen
import com.bangkit.fishery.ui.screen.pool_selection.PoolSelectionScreen
import com.bangkit.fishery.ui.screen.preservation.PreservationScreen
import com.bangkit.fishery.ui.screen.privacy_safety.PrivacySafetyScreen
import com.bangkit.fishery.ui.screen.profile.ProfileScreen
import com.bangkit.fishery.ui.screen.scan_fish.ScanFishScreen
import com.bangkit.fishery.ui.screen.seed_selection.SeedSelectionScreen
import com.bangkit.fishery.ui.screen.splash.SplashScreen

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun FisheryApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        modifier = modifier,
        bottomBar = {
            if (currentRoute.showBottomNavigation()) {
                BottomAppBar(navController = navController)
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Splash.route,
            modifier = modifier.padding(innerPadding)
        ) {

            composable(Screen.Splash.route) {
                SplashScreen(
                    onTimeOut = { isLoggedIn ->
                        if (isLoggedIn) {
                            navController.navigate(Screen.Home.route) {
                                navController.popBackStack()
                                popUpTo(Screen.Splash.route) {
                                    inclusive = true
                                }
                            }
                        } else {
                            navController.navigate(Screen.OnBoarding.route) {
                                popUpTo(Screen.Splash.route) {
                                    inclusive = true
                                }
                            }
                        }
                    }
                )
            }

            composable(Screen.OnBoarding.route) {
                OnBoardingScreen(
                    moveToLogin = {
                        navController.navigate(Screen.Login.route)
                    },
                    moveToRegister = {
                        navController.navigate(Screen.Register.route)
                    },
                    modifier = modifier
                )
            }

            composable(Screen.Login.route) {
                LoginScreen(
                    moveToRegister = {
                        navController.navigate(Screen.Register.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    moveToHome = {
                        navController.navigate(Screen.Home.route)
                    }
                )
            }

            composable(Screen.Register.route) {
                RegisterScreen(
                    moveToLogin = {
                        navController.navigate(Screen.Login.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }

            composable(Screen.Home.route) {
                HomeScreen(
                    navigateToFishItem = { fishName ->
                        navController.navigate(Screen.FishItem.createRoute(fishName))
                    },
                    onImageSelected = {
                        navController.currentBackStackEntry?.savedStateHandle?.set(
                            "result", it
                        )
                        navController.navigate(Screen.FishScan.route)
                    }
                )
            }

            composable(Screen.FishScan.route) {
                val imageResult = navController.previousBackStackEntry?.savedStateHandle?.get<ImageResult>("result")
                ScanFishScreen(
                    image = imageResult
                )
            }


            composable(
                Screen.FishItem.route,
                arguments = listOf(
                    navArgument("fishName") { type = NavType.StringType},
                )
            ) {
                val fish = it.arguments?.getString("fishName") ?: ""
                FishItemScreen(
                    fishName = fish,
                    moveToCultivation = { fishName, idMenu ->
                        navController.navigate(Screen.FishMenu.createRoute(fishName, idMenu))
                    }
                )
            }

            composable(
                Screen.FishMenu.route,
                arguments = listOf(
                    navArgument("fishName") { type = NavType.StringType },
                    navArgument("idMenu") { type = NavType.StringType }
                )
            ) {

                val id = it.arguments?.getString("idMenu") ?: ""
                val fishName = it.arguments?.getString("fishName") ?: ""

                when (id) {
                    "cultivation" -> FishMenuScreen(
                        fish = fishName,
                        id = id,
                        moveToDetailContent = { fish, idMenu, idCultivation ->
                            navController.navigate(Screen.CultivationMenuDetail.createRoute(fish, idMenu, idCultivation))
                        }
                    )
                    "feed" -> FeedRecommendationScreen(
                        fish = fishName
                    )
                    "disease" -> FishDiseaseScreen(
                        fish = fishName
                    )
                }
            }
            
            composable(
                Screen.CultivationMenuDetail.route,
                arguments = listOf(
                    navArgument("fishName") { type = NavType.StringType },
                    navArgument("idMenu") { type = NavType.StringType },
                    navArgument("idCultivation") { type = NavType.StringType}
                ) 
            ) {
                
                val idCultivation = it.arguments?.getString("idCultivation") ?: ""
                val fish = it.arguments?.getString("fishName") ?: ""
                
                when (idCultivation) {
                    "pool" -> PoolSelectionScreen(fish = fish)
                    "seed" -> SeedSelectionScreen(fish = fish)
                    "preservation" -> PreservationScreen(fish = fish)
                    "harvest" -> HarvestScreen(fish = fish)
                }
            }

            composable(Screen.Market.route) {
                MarketScreen(
                    moveToDetailPost = { idPost ->
                        navController.navigate(Screen.DetailPost.createRoute(idPost))
                    },
                    moveAddPost = {
                        navController.navigate(Screen.AddPost.route)
                    }
                )
            }

            composable(
                Screen.DetailPost.route,
                arguments = listOf(
                    navArgument("idPost") { type = NavType.StringType}
                )
            ) {
                val idPost = it.arguments?.getString("idPost") ?: ""
                DetailPostScreen(idPost)
            }

            composable(Screen.AddPost.route) {
                AddPostScreen()
            }

            composable(Screen.Profile.route) {
                ProfileScreen(
                    moveToEdit = { title ->
                        when (title) {
                            "edit_profile" -> navController.navigate(Screen.ChangeProfile.route)
                            "edit_email" -> navController.navigate(Screen.ChangeEmail.route)
                            "edit_password" -> navController.navigate(Screen.ChangePassword.route)
                            "privacy_safety" -> navController.navigate(Screen.PrivacySafety.route)
                        }
                    }
                )
            }

            composable(Screen.ChangeProfile.route) {
                ChangeProfileScreen()
            }

            composable(Screen.ChangeEmail.route) {
                ChangeEmailScreen()
            }

            composable(Screen.ChangePassword.route) {
                ChangePasswordScreen()
            }

            composable(Screen.PrivacySafety.route) {
                PrivacySafetyScreen()
            }

        }
    }
}

@Composable
fun BottomAppBar(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavigationBar(
        modifier = modifier
    ) {

        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        val navigationItem = listOf(
            BottomNavItem(
                title = stringResource(R.string.home),
                icon = painterResource(R.drawable.ic_home),
                screen = Screen.Home
            ),
            BottomNavItem(
                title = stringResource(R.string.market),
                icon = painterResource(R.drawable.ic_market),
                screen = Screen.Market
            ),
            BottomNavItem(
                title = stringResource(R.string.profile),
                icon = painterResource(R.drawable.ic_profile),
                screen = Screen.Profile
            )
        )

        navigationItem.map { item ->
            NavigationBarItem(
                selected = currentRoute == item.screen.route,
                onClick = {
                    navController.popBackStack()
                    navController.navigate(item.screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                    }
                },
                icon = {
                    Icon(
                        painter = item.icon,
                        contentDescription = item.title
                    )
                },
                label = {
                    if (currentRoute == item.screen.route) {
                        Text(item.title)
                    }
                },
                alwaysShowLabel = false,
            )
        }
    }
}

private fun String?.showBottomNavigation(): Boolean {
    return this in setOf(
        Screen.Home.route,
        Screen.Market.route,
        Screen.Profile.route
    )
}

