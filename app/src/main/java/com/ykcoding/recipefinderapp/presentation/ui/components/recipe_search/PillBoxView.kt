package com.ykcoding.recipefinderapp.presentation.ui.components.recipe_search

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ykcoding.recipefinderapp.presentation.ui.theme.CharcoalBlack

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
                color = CharcoalBlack,
                shape = RoundedCornerShape(30.dp)
            )
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Text(
            text = item,
            textAlign = TextAlign.Start,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            color = CharcoalBlack,
        )
    }
}