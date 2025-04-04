package com.ykcoding.recipefinderapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ykcoding.recipefinderapp.presentation.ui.composables.recipe_search.ItemListView
import com.ykcoding.recipefinderapp.presentation.ui.composables.recipe_search.RecipeItem
import com.ykcoding.recipefinderapp.presentation.ui.composables.recipe_search.SearchBar
import com.ykcoding.recipefinderapp.presentation.ui.theme.CharcoalBlack
import com.ykcoding.recipefinderapp.presentation.ui.theme.RecipeFinderAppTheme
import com.ykcoding.recipefinderapp.presentation.utils.ProvideViewModel
import com.ykcoding.recipefinderapp.presentation.utils.getProvidedViewModel
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RecipeFinderAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    RecipeSearchScreenRoot(paddingValues = innerPadding)
                }
            }
        }
    }
}

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
    val recipes by viewModel.state.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .background(color = Color.White)
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
                onClick = {
                    viewModel.getRecipes(query = query, null)
                }
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Cuisine",
                textAlign = TextAlign.Start,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = CharcoalBlack,
            )
            ItemListView(viewModel.cuisine)
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Categories",
                textAlign = TextAlign.Start,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = CharcoalBlack,
            )
            ItemListView(viewModel.categories)
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(align = Alignment.Center)
            ) {
                if (recipes.isLoading) {
                    CircularProgressIndicator()
                } else if (recipes.result != null) {
                    recipes.result?.let {
                        RecipeItem(
                            recipeItems = it.results
                        )
                    }
                } else {
                    // To be implemented
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