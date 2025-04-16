package com.ykcoding.recipefinderapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ykcoding.recipefinderapp.helper.BottomBarDestination
import com.ykcoding.recipefinderapp.presentation.ui.components.common.BottomNavigationBar
import com.ykcoding.recipefinderapp.presentation.ui.components.favorite.FavoriteScreenRoot
import com.ykcoding.recipefinderapp.presentation.ui.components.home.HomeScreenRoot
import com.ykcoding.recipefinderapp.presentation.ui.components.recipe_search.RecipeSearchScreenRoot
import com.ykcoding.recipefinderapp.presentation.ui.theme.RecipeFinderAppTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RecipeFinderAppTheme {
                var selectedTab by rememberSaveable { mutableStateOf(BottomBarDestination.HOME) }
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        BottomNavigationBar(
                            selectedTab = selectedTab,
                            onItemClicked = { selectedTab = it }
                        )
                    }
                ) { innerPadding ->
                    when(selectedTab) {
                        BottomBarDestination.HOME -> HomeScreenRoot(paddingValues = innerPadding)
                        BottomBarDestination.SEARCH -> RecipeSearchScreenRoot(paddingValues = innerPadding)
                        BottomBarDestination.FAVORITES -> FavoriteScreenRoot(paddingValues = innerPadding)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    RecipeFinderAppTheme { }
}