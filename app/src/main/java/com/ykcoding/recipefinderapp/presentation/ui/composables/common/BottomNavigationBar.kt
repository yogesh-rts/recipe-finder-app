package com.ykcoding.recipefinderapp.presentation.ui.composables.common


import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ykcoding.recipefinderapp.R
import com.ykcoding.recipefinderapp.helper.BottomBarDestination
import com.ykcoding.recipefinderapp.presentation.ui.theme.AvocadoGreen
import com.ykcoding.recipefinderapp.presentation.ui.theme.BurntOrange
import com.ykcoding.recipefinderapp.presentation.ui.theme.CharcoalBlack
import com.ykcoding.recipefinderapp.presentation.ui.theme.CitrusOrange
import com.ykcoding.recipefinderapp.presentation.ui.theme.DeepSageGreen
import com.ykcoding.recipefinderapp.presentation.ui.theme.DustyEverGreen
import com.ykcoding.recipefinderapp.presentation.ui.theme.EmeraldGreen
import com.ykcoding.recipefinderapp.presentation.ui.theme.MediumGray
import com.ykcoding.recipefinderapp.presentation.ui.theme.MutedForestGreen
import com.ykcoding.recipefinderapp.presentation.ui.theme.OliveGreen
import com.ykcoding.recipefinderapp.presentation.ui.theme.RecipeFinderAppTheme

@Composable
fun BottomNavigationBar(
    selectedTab: BottomBarDestination = BottomBarDestination.HOME,
    onItemClicked: (BottomBarDestination) -> Unit
) {
    BottomAppBar(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 16.dp),
        windowInsets = WindowInsets(
            top = 0.dp,
            bottom = 0.dp
        ),
        containerColor = MediumGray
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            BottomBarItemView(
                text = "Home",
                drawable = R.drawable.ic_home,
                selectedTab = selectedTab == BottomBarDestination.HOME,
                onClick = { onItemClicked(BottomBarDestination.HOME) }
            )
            BottomBarItemView(
                text = "Search",
                drawable = R.drawable.ic_search,
                selectedTab = selectedTab == BottomBarDestination.SEARCH,
                onClick = { onItemClicked(BottomBarDestination.SEARCH) }
            )
            BottomBarItemView(
                text = "Favorites",
                drawable = R.drawable.ic_favorite,
                selectedTab = selectedTab == BottomBarDestination.FAVORITES,
                onClick = { onItemClicked(BottomBarDestination.FAVORITES) }
            )
        }

    }
}

@Composable
fun BottomBarItemView(
    text: String,
    drawable: Int,
    selectedTab: Boolean = false,
    onClick: () -> Unit
) {
    val selectedColor = if (selectedTab) BurntOrange else CharcoalBlack
    Column(
        modifier = Modifier
            .padding(16.dp)
            .clickable { onClick.invoke() },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(drawable),
            contentDescription = "Bottom navigation bar item icon",
            modifier = Modifier.size(16.dp),
            colorFilter = ColorFilter.tint(color = selectedColor)
        )
        Text(
            text = text,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 14.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            color = selectedColor
        )
    }
}

@Preview
@Composable
fun Preview() {
    RecipeFinderAppTheme {
        BottomNavigationBar {
            BottomBarDestination.HOME
        }
    }
}