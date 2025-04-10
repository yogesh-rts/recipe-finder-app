package com.ykcoding.recipefinderapp.presentation.ui.composables.recipe_search

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.ykcoding.recipefinderapp.R
import com.ykcoding.recipefinderapp.data.remote.dto.Result
import com.ykcoding.recipefinderapp.helper.TimeUtils
import com.ykcoding.recipefinderapp.presentation.ui.theme.CharcoalBlack
import com.ykcoding.recipefinderapp.presentation.ui.theme.RecipeFinderAppTheme
import com.ykcoding.recipefinderapp.presentation.ui.theme.SageGreen

@Composable
fun RecipeInfoItemView(value: String, drawable: Int) {
    Row(
        modifier = Modifier.wrapContentSize(),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(drawable),
            contentDescription = "Recipe Image",
            modifier = Modifier
                .size(20.dp)
        )
        Text(
            text = value,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 14.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            color = CharcoalBlack,
        )
    }
}

@Composable
fun RecipesList(recipeItems: List<Result>) {
    LazyColumn {
        items(recipeItems) { item ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = Color.Transparent)
            ) {
                Box(modifier = Modifier.fillMaxSize()) {
                    Box(
                        modifier = Modifier
                            .matchParentSize()
                            .background(
                                brush = Brush.verticalGradient(
                                    colorStops = arrayOf(
                                        0.0f to Color.White,
                                        0.3f to Color.White,
                                        0.6f to Color.White.copy(alpha = 0.4f),
                                        0.95f to SageGreen.copy(alpha = 0.8f),
                                        1.0f to SageGreen
                                    )
                                )
                            )
                    )
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Top
                    ) {
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
                                .aspectRatio(2f / 0.85f),
                            contentScale = ContentScale.Crop
                        )
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp),
                            verticalArrangement = Arrangement.Bottom
                        ) {
                            Text(
                                text = item.title,
                                textAlign = TextAlign.Start,
                                fontWeight = FontWeight.ExtraBold,
                                fontSize = 16.sp,
                                lineHeight = 24.sp,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                color = CharcoalBlack,
                                modifier = Modifier.padding(horizontal = 16.dp)
                            )
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 16.dp),
                                horizontalArrangement = Arrangement.spacedBy(space = 8.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                RecipeInfoItemView(
                                    value = item.servings.toString(),
                                    drawable = R.drawable.ic_persons
                                )
                                RecipeInfoItemView(
                                    value = TimeUtils.formatTime(item.readyInMinutes),
                                    drawable = R.drawable.ic_clock
                                )
                                RecipeInfoItemView(
                                    value = item.healthScore.toString(),
                                    drawable = R.drawable.ic_fire
                                )
                                Spacer(modifier = Modifier.weight(1f))
                                IconButton(
                                    onClick = { }
                                ) {
                                    Icon(
                                        painter = painterResource(R.drawable.ic_chevron_right),
                                        contentDescription = "Forward Chevron",
                                        modifier = Modifier.size(28.dp)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    RecipeFinderAppTheme {
        RecipesList(
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
        )
    }
}

