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
import com.ykcoding.recipefinderapp.data.remote.dto.Result
import com.ykcoding.recipefinderapp.presentation.ui.theme.CharcoalBlack
import com.ykcoding.recipefinderapp.presentation.ui.theme.Concrete
import com.ykcoding.recipefinderapp.presentation.ui.theme.EmeraldGreen
import com.ykcoding.recipefinderapp.presentation.ui.theme.RecipeFinderAppTheme
import com.ykcoding.recipefinderapp.presentation.ui.view_models.HomeScreenViewModel
import com.ykcoding.recipefinderapp.presentation.utils.ProvideViewModel
import com.ykcoding.recipefinderapp.presentation.utils.getProvidedViewModel
import com.ykcoding.recipefinderapp.presentation.view_state.HomeScreenUIState
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
            RecipeSection("Chef's Pick for you")
            RecipesHorizontalListView(
                state = state.randomRecipes,
                cardAspectRatio = 3f / 3.5f,
                textSize = 14.sp,
                imageWidth = 175.dp,
            )
            Spacer(modifier = Modifier.height(16.dp))
            RecipeSection("What’s Trending")
            RecipesHorizontalListView(
                state = state.popularRecipes,
                cardAspectRatio = 4f / 2f,
                textSize = 16.sp,
                imageWidth = 300.dp
            )
            Spacer(modifier = Modifier.height(16.dp))
            RecipeSection("Under 30 Minutes")
            RecipesHorizontalListView(
                state = state.quickRecipes,
                cardAspectRatio = 4f / 2.75f,
                textSize = 12.sp,
                imageWidth = 130.dp
            )
            Spacer(modifier = Modifier.height(16.dp))
            RecipeSection("Healthy Plates")
            RecipesHorizontalListView(
                state = state.healthyRecipes,
                cardAspectRatio = 4f / 2f,
                textSize = 14.sp,
                imageWidth = 225.dp
            )
        }
    }
}

@Composable
fun RecipeSection(title: String) {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = title,
            textAlign = TextAlign.Start,
            fontSize = 22.sp,
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
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    RecipeFinderAppTheme {
        HomeScreenRoot(paddingValues = PaddingValues(16.dp))
    }
}