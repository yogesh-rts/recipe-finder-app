package com.ykcoding.recipefinderapp.presentation.ui.composables.recipe_search

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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ykcoding.recipefinderapp.presentation.ui.theme.CharcoalBlack
import com.ykcoding.recipefinderapp.presentation.ui.theme.Concrete
import com.ykcoding.recipefinderapp.presentation.ui.theme.RecipeFinderAppTheme
import com.ykcoding.recipefinderapp.presentation.ui.theme.SageGreen
import com.ykcoding.recipefinderapp.presentation.ui.view_models.RecipesViewModel
import com.ykcoding.recipefinderapp.presentation.utils.ProvideViewModel
import com.ykcoding.recipefinderapp.presentation.utils.getProvidedViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun RecipeSearchScreenRoot(paddingValues: PaddingValues) {
    val viewModel: RecipesViewModel = koinViewModel()

    ProvideViewModel(viewModel = viewModel) {
        RecipeSearchScreen(paddingValues)
    }
}

@Composable
fun RecipeSearchScreen(paddingValues: PaddingValues) {
    val viewModel = getProvidedViewModel<RecipesViewModel>()
    var query by remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) }
    val recipes by viewModel.recipeListState.collectAsState()
    val filterState by viewModel.filterState.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .background(color = Concrete)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Box(
                modifier = Modifier
                    .background(
                        color = SageGreen,
                        shape = RoundedCornerShape(20.dp)
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
                            text = "What would you like to cook today?",
                            textAlign = TextAlign.Start,
                            fontSize = 20.sp,
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
                    SearchBar(
                        query = query,
                        onQueryChange = { query = it },
                        onSearchClick = {
                            viewModel.getRecipes(
                                query = query,
                                cuisine =  filterState.selectedCuisine,
                                category = filterState.selectedCategory
                            )
                        },
                        onFilterClick = { showDialog = true }
                    )
                    FilterDialog(
                        showDialog = showDialog,
                        cuisineOptions = viewModel.cuisine,
                        categoryOptions = viewModel.categories,
                        selectedCuisine = filterState.selectedCuisine,
                        selectedCategory = filterState.selectedCategory,
                        onCuisineSelected =  viewModel::selectCuisine,
                        onCategorySelected = viewModel::selectCategory,
                        onApply = { showDialog = false },
                        onDismiss = { showDialog = false },
                        onClear = viewModel::clearFilter
                    )
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 8.dp)
                    .wrapContentSize(align = Alignment.Center)
            ) {
                if (recipes.isLoading) {
                    CircularProgressIndicator()
                } else if (recipes.result != null) {
                    recipes.result?.let {
                        RecipesRootView(it.results)
                    }
                } else {
                    recipes.error?.getContentIfNotHandled()?.handleErrorMessage()?.let { errorMessage ->
                        Text(
                            text = errorMessage,
                            textAlign = TextAlign.Start,
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 16.sp,
                            lineHeight = 24.sp,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            color = CharcoalBlack,
                            modifier = Modifier.padding(horizontal = 16.dp)
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun RecipeSearchPreview() {
    RecipeFinderAppTheme {
        RecipeSearchScreenRoot(PaddingValues(16.dp))
    }
}