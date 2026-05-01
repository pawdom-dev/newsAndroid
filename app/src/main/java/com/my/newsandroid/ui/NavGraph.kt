package com.my.newsandroid.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.my.newsandroid.ui.details.DetailsRoute
import com.my.newsandroid.ui.details.DetailsScreen
import com.my.newsandroid.ui.news.NewsScreen
import kotlinx.serialization.Serializable

@Serializable
object NewsRoute

@Composable
fun NavGraph() {
    val navController = rememberNavController()
    
    NavHost(
        navController = navController,
        startDestination = NewsRoute
    ) {
        composable<NewsRoute> {
            NewsScreen(
                onArticleClick = { articleId ->
                    navController.navigate(DetailsRoute(articleId))
                }
            )
        }
        composable<DetailsRoute> {
            DetailsScreen(
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}
