package com.ykcoding.recipefinderapp.presentation.ui.composables.recipe_search

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.ykcoding.recipefinderapp.R
import com.ykcoding.recipefinderapp.data.remote.dto.Result
import com.ykcoding.recipefinderapp.helper.TimeUtils
import com.ykcoding.recipefinderapp.presentation.ui.theme.CharcoalBlack
import com.ykcoding.recipefinderapp.presentation.ui.theme.Concrete
import com.ykcoding.recipefinderapp.presentation.ui.theme.RecipeFinderAppTheme
import com.ykcoding.recipefinderapp.presentation.ui.theme.SageGreenMuted

@Composable
fun RecipeItemView(value: String, drawable: Int) {
    Row(
        modifier = Modifier.wrapContentSize(),
        horizontalArrangement = Arrangement.spacedBy(
           space =  4.dp,
           alignment = Alignment.Start
        ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(drawable),
            contentDescription = "Recipe Image",
            modifier = Modifier.size(16.dp)
        )
        Text(
            text = value,
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp,
            maxLines = 1,
            color = Color.DarkGray
        )
    }
}

@Composable
fun RecipesRootView(recipeItems: List<Result>) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        RecipeListView(recipeItems = recipeItems)
    }
}

@Composable
fun RecipeListView(recipeItems: List<Result>) {
    LazyVerticalGrid(columns = GridCells.Adaptive(160.dp)) {

        item(span = { GridItemSpan(maxLineSpan) }) {
            Text(
                text = "Delicious Recipes Found",
                textAlign = TextAlign.Start,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 24.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = CharcoalBlack,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 16.dp)
            )
        }
        items(recipeItems) { item ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(3f / 6f)
                        .padding(
                            top = 64.dp,
                            start = 4.dp,
                            end = 4.dp,
                            bottom = 8.dp
                        ),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = SageGreenMuted),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
                        val offsetY = this.maxHeight * 0.325f

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .offset(y = offsetY)
                                .padding(horizontal = 8.dp, vertical = 8.dp)
                        ) {
                            Column(
                                modifier = Modifier.fillMaxSize()
                            ) {
                                Row(
                                    modifier = Modifier.wrapContentSize(),
                                    horizontalArrangement = Arrangement.spacedBy(
                                        space =  2.dp,
                                        alignment = Alignment.Start
                                    ),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = "How to make",
                                        textAlign = TextAlign.Start,
                                        fontWeight = FontWeight.SemiBold,
                                        fontSize = 10.sp,
                                        lineHeight = 16.sp,
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis,
                                        color = CharcoalBlack,
                                    )
                                    Icon(
                                        painter = painterResource(R.drawable.ic_right_arrow),
                                        contentDescription = "Forward Chevron",
                                        modifier = Modifier.size(10.dp)
                                    )
                                }
                                Text(
                                    text = item.title,
                                    textAlign = TextAlign.Start,
                                    fontWeight = FontWeight.ExtraBold,
                                    fontSize = 18.sp,
                                    lineHeight = 24.sp,
                                    maxLines = 2,
                                    overflow = TextOverflow.Ellipsis,
                                    color = CharcoalBlack,
                                )
                                Spacer(Modifier.height(8.dp))
                                RecipeItemView(
                                    value = item.healthScore.toString(),
                                    drawable = R.drawable.ic_fire
                                )
                                RecipeItemView(
                                    value = TimeUtils.formatTime(item.readyInMinutes),
                                    drawable = R.drawable.ic_timer
                                )
                                RecipeItemView(
                                    value = String.format(
                                        stringResource(R.string.searchRecipes_recipesList_servingsSuffix),
                                        item.servings),
                                    drawable = R.drawable.ic_servings
                                )
                            }
                        }
                    }
                }
                Image(
                    painter = rememberAsyncImagePainter(item.image),
                    contentDescription = "Recipe Image",
                    modifier = Modifier
                        .size(130.dp)
                        .align(Alignment.TopEnd)
                        .offset(x = (-4).dp, y = 10.dp)
                        .clip(CircleShape)
                        .background(Concrete, CircleShape),
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun Preview() {
    RecipeFinderAppTheme {
        RecipesRootView(
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

