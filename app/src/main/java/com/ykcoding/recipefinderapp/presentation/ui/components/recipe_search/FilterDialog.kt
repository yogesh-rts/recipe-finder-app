package com.ykcoding.recipefinderapp.presentation.ui.components.recipe_search

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Surface
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
import com.ykcoding.recipefinderapp.presentation.ui.theme.EmeraldGreen
import com.ykcoding.recipefinderapp.presentation.ui.theme.FreshRed
import com.ykcoding.recipefinderapp.presentation.ui.theme.OnionPink
import com.ykcoding.recipefinderapp.presentation.ui.theme.SageGreen


@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun FilterDialog(
    showDialog: Boolean = false,
    cuisineOptions: List<String>,
    categoryOptions: List<String>,
    selectedCuisine: String?,
    selectedCategory: String?,
    onCuisineSelected: (String) -> Unit,
    onCategorySelected: (String) -> Unit,
    onApply: () -> Unit,
    onDismiss: () -> Unit,
    onClear: () -> Unit
) {
    if (showDialog) {
        BasicAlertDialog(
            onDismissRequest = onDismiss
        ) {
            Surface(
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                ) {
                    Text(
                        text = "clear all",
                        textAlign = TextAlign.End,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = CharcoalBlack,
                        modifier = Modifier
                            .align(Alignment.End)
                            .clickable { onClear.invoke() }
                    )
                    Text(
                        text = "Cuisine",
                        textAlign = TextAlign.Start,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = CharcoalBlack
                    )
                    FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        cuisineOptions.forEach { cuisine ->
                            val isSelected = selectedCuisine == cuisine
                            FilterChip(
                                selected = isSelected,
                                shape = RoundedCornerShape(30.dp),
                                label = { Text(text = cuisine) },
                                onClick = { onCuisineSelected.invoke(cuisine) },
                                colors = FilterChipDefaults.filterChipColors(
                                    selectedContainerColor = if (isSelected) SageGreen else Color.Transparent
                                ),
                                border = BorderStroke(
                                    width = 1.dp,
                                    color = SageGreen
                                ),
                            )
                        }

                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Categories",
                        textAlign = TextAlign.Start,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = CharcoalBlack
                    )
                    FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        categoryOptions.forEach { category ->
                            val isSelected = selectedCategory == category
                            FilterChip(
                                selected = isSelected ,
                                shape = RoundedCornerShape(30.dp),
                                label = { Text(text = category) },
                                onClick = { onCategorySelected.invoke(category) },
                                colors = FilterChipDefaults.filterChipColors(
                                    selectedContainerColor = if (isSelected) OnionPink else Color.Transparent
                                ),
                                border = BorderStroke(
                                    width = 1.dp,
                                    color = OnionPink
                                ),
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(
                        onClick = { onApply.invoke() },
                        modifier = Modifier
                            .fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = EmeraldGreen,
                            contentColor = Color.White
                        ),
                        elevation = ButtonDefaults.elevatedButtonElevation(defaultElevation = 2.dp)
                    ) {
                        Text(
                            text = "Apply",
                            textAlign = TextAlign.Center,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.SemiBold,
                        )
                    }
                    Button(
                        onClick = { onDismiss.invoke() },
                        modifier = Modifier
                            .fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = FreshRed,
                            contentColor = Color.White
                        ),
                        elevation = ButtonDefaults.elevatedButtonElevation(defaultElevation = 2.dp)
                    ) {
                        Text(
                            text = "Cancel",
                            textAlign = TextAlign.Center,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.SemiBold,
                        )
                    }
                }
            }
        }
    }

}

