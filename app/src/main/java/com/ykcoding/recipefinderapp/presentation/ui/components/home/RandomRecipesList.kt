package com.ykcoding.recipefinderapp.presentation.ui.components.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.ykcoding.recipefinderapp.data.remote.dto.Result
import com.ykcoding.recipefinderapp.presentation.ui.components.common.ErrorCardView
import com.ykcoding.recipefinderapp.presentation.ui.components.config.RecipeCardConfig
import com.ykcoding.recipefinderapp.presentation.ui.theme.Concrete
import com.ykcoding.recipefinderapp.presentation.ui.theme.RecipeFinderAppTheme
import com.ykcoding.recipefinderapp.presentation.view_state.HomeScreenUIState

@Composable
fun RecipesHorizontalListView(
    state: HomeScreenUIState.SectionState,
    config: RecipeCardConfig,
) {

    val (uiState, recipeItems) = when(state) {
        is HomeScreenUIState.SectionState.IsLoading -> UiState.LOADING to emptyList()
        is HomeScreenUIState.SectionState.Success -> UiState.SUCCESS to state.body
        else -> UiState.ERROR to emptyList()
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Concrete)
    ) {
        LazyRow(modifier = Modifier.fillMaxWidth()) {
            when (uiState) {
                UiState.LOADING -> {
                    item {
                        Box(
                            modifier = Modifier
                                .fillParentMaxWidth()
                                .padding(16.dp),
                            contentAlignment = Alignment.Center
                        ) {
                           CircularProgressIndicator()
                        }
                    }
                }
                UiState.ERROR -> {
                    item {
                        Box(
                            modifier = Modifier
                                .fillParentMaxWidth()
                                .padding(16.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            ErrorCardView()
                        }
                    }
                }
                UiState.SUCCESS -> {
                    items(recipeItems) { item ->
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 8.dp),
                            verticalArrangement = Arrangement.spacedBy(4.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 0.dp),
                                shape = RoundedCornerShape(10.dp),
                                colors = CardDefaults.cardColors(containerColor = Concrete)
                            ){
                                val imagePainter = rememberAsyncImagePainter(
                                    ImageRequest.Builder(LocalContext.current)
                                        .data(item.image)
                                        .crossfade(true)
                                        .build()
                                )
                                Image(
                                    painter = imagePainter,
                                    contentDescription = "Recipes Image",
                                    modifier = Modifier
                                        .align(Alignment.CenterHorizontally)
                                        .width(config.imageWidth)
                                        .aspectRatio(config.cardAspectRatio),
                                    contentScale = ContentScale.Crop
                                )
                            }
                            Text(
                                text = item.title,
                                maxLines = 1,
                                fontSize = config.textSize,
                                overflow = TextOverflow.Ellipsis,
                                modifier = Modifier
                                    .align(Alignment.CenterHorizontally)
                                    .width(config.imageWidth)
                                    .padding(horizontal = 16.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

enum class UiState {
    LOADING,
    SUCCESS,
    ERROR
}

@Preview(showBackground = true)
@Composable
fun RandomRecipesPreview() {
    RecipeFinderAppTheme {
        RecipesHorizontalListView(
            state = HomeScreenUIState.SectionState.Success(
                listOf(
                    Result(
                        id = 756814,
                        title = "Powerhouse Almond Matcha Superfood Smoothie",
                        imageType = "jpg",
                        image = "https://img.spoonacular.com/recipes/756814-312x231.jpg",
                        readyInMinutes = 15,
                        healthScore = 40,
                        servings = 4
                    ),
                    Result(
                        id = 756814,
                        title = "Powerhouse Almond Matcha Superfood Smoothie",
                        imageType = "jpg",
                        image = "https://img.spoonacular.com/recipes/756814-312x231.jpg",
                        readyInMinutes = 15,
                        healthScore = 40,
                        servings = 4
                    ),
                    Result(
                        id = 756814,
                        title = "Powerhouse Almond Matcha Superfood Smoothie",
                        imageType = "jpg",
                        image = "https://img.spoonacular.com/recipes/756814-312x231.jpg",
                        readyInMinutes = 15,
                        healthScore = 40,
                        servings = 4
                    ),
                )
            ),
           config = RecipeCardConfig()
        )
    }
}