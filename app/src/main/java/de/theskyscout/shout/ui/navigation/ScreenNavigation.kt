package de.theskyscout.shout.ui.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import de.theskyscout.shout.ui.screens.ChatScreen.ChatScreen
import de.theskyscout.shout.ui.screens.ChatScreen.ChatScreenComposable
import de.theskyscout.shout.ui.screens.MainScreen.MainScreen
import de.theskyscout.shout.ui.screens.MainScreen.MainScreenComposable
import de.theskyscout.shout.viewModel

@Composable
fun ScreenNavigation() {
    val navController = rememberNavController()
    viewModel.setNavController(navController)
    NavHost(
        navController = navController,
        startDestination = MainScreen,
        modifier = Modifier.fillMaxSize()
    ) {
        composable<MainScreen>() {
            MainScreenComposable()
        }
        composable<ChatScreen>(
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Up,
                    tween(400)
                )
            },
            popExitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Down,
                    tween(400)
                )
            }
        ) {
            val args = it.toRoute<ChatScreen>()
            ChatScreenComposable(args)
        }
    }
}