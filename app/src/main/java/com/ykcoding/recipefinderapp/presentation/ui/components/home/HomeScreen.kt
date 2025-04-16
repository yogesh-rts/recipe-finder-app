package com.ykcoding.recipefinderapp.presentation.ui.components.home

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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Icon
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
import com.ykcoding.recipefinderapp.presentation.ui.components.config.RecipeCardConfig
import com.ykcoding.recipefinderapp.presentation.ui.components.config.RecipeCardPresets
import com.ykcoding.recipefinderapp.presentation.ui.theme.CharcoalBlack
import com.ykcoding.recipefinderapp.presentation.ui.theme.Concrete
import com.ykcoding.recipefinderapp.presentation.ui.theme.EmeraldGreen
import com.ykcoding.recipefinderapp.presentation.ui.theme.RecipeFinderAppTheme
import com.ykcoding.recipefinderapp.presentation.ui.theme.SageGreen
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
            modifier = Modifier.verticalScroll(scrollState),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Box(
                modifier = Modifier
                    .background(
                        color = SageGreen,
                        shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
                    ),
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "Welcome Yogesh,",
                        textAlign = TextAlign.Start,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Light,
                        color = CharcoalBlack,
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "Got a tasty dish in mind?",
                            textAlign = TextAlign.Start,
                            fontSize = 28.sp,
                            lineHeight = 32.sp,
                            fontWeight = FontWeight.Black,
                            color = CharcoalBlack,
                            modifier = Modifier
                                .padding(end = 32.dp)
                                .weight(1f)
                        )
                        Icon(
                            imageVector = Icons.Default.AccountCircle,
                            contentDescription = "Account Icon",
                            modifier = Modifier.size(48.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                }
            }
            RecipeSection(
                title = "Chef's Pick for you",
                sectionState = state.randomRecipes,
                cardConfig = RecipeCardPresets.LARGE
            )
            RecipeSection(
                title = "What’s Trending",
                sectionState = state.popularRecipes,
                cardConfig = RecipeCardPresets.FEATURED
            )
            RecipeSection(
                title = "Under 30 minutes",
                sectionState = state.quickRecipes,
                cardConfig = RecipeCardPresets.SMALL
            )
            RecipeSection(
                title = "Healthy Plates",
                sectionState = state.healthyRecipes,
                cardConfig = RecipeCardPresets.COMPACT
            )
        }
    }
}

@Composable
fun RecipeSection(
    title: String,
    sectionState: HomeScreenUIState.SectionState,
    cardConfig: RecipeCardConfig = RecipeCardConfig()
) {
    val isLoading = sectionState is HomeScreenUIState.SectionState.Error

    if (!isLoading) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        ) {
            Column(
                modifier = Modifier.padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = title,
                        textAlign = TextAlign.Start,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = CharcoalBlack,
                    )
                    Text(
                        text = "see all",
                        textAlign = TextAlign.End,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = EmeraldGreen,
                        modifier = Modifier.weight(1f)
                    )
                }
                RecipesHorizontalListView(
                    state = sectionState,
                    config = cardConfig
                )
            }
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