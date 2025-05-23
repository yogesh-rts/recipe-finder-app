package com.ykcoding.recipefinderapp.presentation.ui.components.favorite

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ykcoding.recipefinderapp.presentation.ui.view_models.RecipesViewModel
import com.ykcoding.recipefinderapp.presentation.ui.theme.CharcoalBlack
import com.ykcoding.recipefinderapp.presentation.ui.theme.Concrete
import com.ykcoding.recipefinderapp.presentation.utils.ProvideViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun FavoriteScreenRoot(paddingValues: PaddingValues) {
    val viewModel: RecipesViewModel = koinViewModel()

    ProvideViewModel(viewModel = viewModel) {
        FavoriteScreen(paddingValues)
    }
}

@Composable
fun FavoriteScreen(paddingValues: PaddingValues) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .background(color = Concrete)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "Welcome to Favorites Screen,",
                textAlign = TextAlign.Start,
                fontSize = 28.sp,
                fontWeight = FontWeight.Light,
                color = CharcoalBlack,
            )
        }
    }

}