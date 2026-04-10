package com.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.serah.sokohub.ui.screens.about.AboutScreen
import com.serah.sokohub.ui.screens.home.HomeScreen
import com.serah.sokohub.ui.screens.auth.LoginScreen
import com.serah.sokohub.ui.screens.auth.RegisterScreen
import com.serah.sokohub.ui.screens.intent.IntentScreen
import com.serah.sokohub.ui.screens.onboarding.OnBoardingScreen
import com.serah.sokohub.ui.screens.service.ServiceScreen

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = ROUT_ONBOARDING
) {

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(ROUT_HOME) {
            HomeScreen(navController)
        }
        composable(ROUT_ABOUT) {
            AboutScreen(navController)
        }
        composable(ROUT_LOGIN) {
            LoginScreen(navController)
        }
        composable(ROUT_REGISTER) {
            RegisterScreen(navController)
        }
        composable(ROUT_ONBOARDING) {
            OnBoardingScreen(navController)
        }
        composable(ROUT_SERVICE) {
            ServiceScreen(navController)
        }
        composable(ROUT_INTENT) {
            IntentScreen(navController)
        }
    }

}