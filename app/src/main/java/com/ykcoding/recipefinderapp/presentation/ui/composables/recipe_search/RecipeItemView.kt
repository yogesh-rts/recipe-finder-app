package com.ykcoding.recipefinderapp.presentation.ui.composables.recipe_search

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.ykcoding.recipefinderapp.data.remote.dto.RecipesDto
import com.ykcoding.recipefinderapp.presentation.ui.theme.CharcoalBlack

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
fun RecipeItem(recipeItems: List<RecipesDto.Result>) {
    LazyColumn {
        items(recipeItems) { item ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .aspectRatio(1f / 0.54f),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = CharcoalBlack)
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