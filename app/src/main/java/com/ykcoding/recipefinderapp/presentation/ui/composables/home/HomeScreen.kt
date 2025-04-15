package com.ykcoding.recipefinderapp.presentation.ui.composables.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ykcoding.recipefinderapp.presentation.ui.theme.CharcoalBlack
import com.ykcoding.recipefinderapp.presentation.ui.theme.Concrete
import com.ykcoding.recipefinderapp.presentation.ui.theme.EmeraldGreen
import com.ykcoding.recipefinderapp.presentation.ui.theme.RecipeFinderAppTheme
import com.ykcoding.recipefinderapp.presentation.ui.view_models.HomeScreenViewModel
import com.ykcoding.recipefinderapp.presentation.utils.ProvideViewModel
import com.ykcoding.recipefinderapp.presentation.utils.getProvidedViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreenRoot(paddingValues: PaddingValues) {
    val viewModel: HomeScreenViewModel = koinViewModel()

    ProvideViewModel(viewModel = viewModel) {
        HomeScreen(paddingValues)
    }
}

@Composable
fun HomeScreen(paddingValues: PaddingValues) {
    val viewModel = getProvidedViewModel<HomeScreenViewModel>()
    val scrollState = rememberScrollState()
    val state by viewModel.homeScreenUiState.collectAsState()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .background(color = Concrete)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Chef's Pick for you",
                    textAlign = TextAlign.Start,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = CharcoalBlack,
                )
                Text(
                    text = "see all",
                    textAlign = TextAlign.End,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal,
                    color = EmeraldGreen,
                    modifier = Modifier.weight(1f)
                )

            }
            RandomRecipesList(recipesList = state.randomRecipes)
            RandomRecipesList(recipesList = state.popularRecipes)
            RandomRecipesList(recipesList = state.quickRecipes)
            RandomRecipesList(recipesList = state.healthyRecipes)
        }
    }

}

@Preview(showBackground = true)
@Composable
fun Preview() {
    RecipeFinderAppTheme {
        HomeScreenRoot(paddingValues = PaddingValues(16.dp))
    }
}