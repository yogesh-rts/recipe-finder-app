package com.ykcoding.recipefinderapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.ykcoding.recipefinderapp.data.remote.dto.RecipesDto
import com.ykcoding.recipefinderapp.presentation.ui.theme.RecipeFinderAppTheme
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RecipeFinderAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    RecipeSearchScreen(paddingValues = innerPadding)
                }
            }
        }
    }
}

@Composable
fun RecipeSearchScreen(
    viewModel: RecipesViewModel = koinViewModel(),
    paddingValues: PaddingValues
) {
    val recipes by viewModel.state.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .background(color = Color.White)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Welcome Yogesh,",
                textAlign = TextAlign.Start,
                fontSize = 12.sp,
                fontWeight = FontWeight.Light,
                color = Color.DarkGray,
            )
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "What would you like to cook today?",
                    textAlign = TextAlign.Start,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Black,
                    color = Color.Black,
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
            SearchBar(viewModel = viewModel)
            Text(
                text = "Cuisine",
                textAlign = TextAlign.Start,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black,
            )
            ItemListView(viewModel.cuisine)
            Text(
                text = "Categories",
                textAlign = TextAlign.Start,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black,
            )
            ItemListView(viewModel.categories)
            Box(
                modifier = Modifier.fillMaxSize()
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

@Composable
fun ItemListView(items: List<String>) {
    LazyRow {
        items(items) { item ->
            PillBoxView(item)
            Spacer(Modifier.width(4.dp))
        }
    }
}

@Composable
fun PillBoxView(item: String) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .wrapContentSize()
            .background(
                color = Color.White,
                shape = RoundedCornerShape(30.dp)
            )
            .border(
                width = 1.dp,
                color = Color.DarkGray,
                shape = RoundedCornerShape(30.dp)
            )
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Text(
            text = item,
            textAlign = TextAlign.Start,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Black,
        )
    }
}

@Composable
fun RecipeItem(recipeItems: List<RecipesDto.Result>) {
    LazyColumn {
        items(recipeItems) { item ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .aspectRatio(1f / 0.54f),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = Color.Black)
            ) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    val imagePainter = rememberAsyncImagePainter(
                        ImageRequest.Builder(LocalContext.current)
                            .data(item.image)
                            .crossfade(true)
                            .build()
                    )
                    Image(
                        painter = imagePainter,
                        contentDescription = "Recipe Image",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(130.dp)
                            .clip(MaterialTheme.shapes.medium),
                        contentScale = ContentScale.Crop
                    )
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = item.title,
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                            color = Color.White
                        )
                    }
                }

            }
        }
    }
}

@Composable
fun SearchBar(viewModel: RecipesViewModel) {
    var query by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
    ) {
        OutlinedTextField(
            value = query,
            onValueChange = { query = it },
            shape = RoundedCornerShape(100),
            modifier = Modifier.fillMaxWidth(),
            label = { Text(text = "Search recipes") },
            trailingIcon = {
                IconButton(
                    onClick = { viewModel.getRecipes(query = query, null) }
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search bar icon",
                        modifier = Modifier.size(32.dp)
                    )
                }
            }
        )
    }
}


@Preview(showBackground = true)
@Composable
fun Preview() {
    RecipeFinderAppTheme {
        //SearchBar()
    }
}