package com.ykcoding.recipefinderapp.presentation.ui.composables.recipe_search

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ykcoding.recipefinderapp.presentation.ui.theme.CharcoalBlack
import com.ykcoding.recipefinderapp.presentation.ui.theme.EmeraldGreen

@Composable
fun SearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    onClick: () -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val interactionSource = remember { MutableInteractionSource() }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier
                    .heightIn(40.dp)
                    .fillMaxWidth()
                    .weight(1f)
                    .border(
                    width = 2.dp,
                    shape = RoundedCornerShape(100),
                    color = CharcoalBlack
                ),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Spacer(modifier = Modifier.width(16.dp))
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search icon",
                    modifier = Modifier.size(20.dp),
                    tint = CharcoalBlack
                )
                Spacer(modifier = Modifier.width(8.dp))
                BasicTextField(
                    value = query,
                    onValueChange = onQueryChange,
                    singleLine = true,
                    textStyle = LocalTextStyle.current.copy(
                        color = CharcoalBlack,
                        fontSize = 14.sp
                    ),
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Search
                    ),
                    keyboardActions = KeyboardActions(
                        onSend = {
                            onClick()
                            keyboardController?.hide()
                        }
                    ),
                    interactionSource = interactionSource,
                    decorationBox = { innerTextField ->
                        if (query.isEmpty()) {
                            Text(
                                text = "Search recipes",
                                color = Color.Gray,
                                fontSize = 14.sp
                            )
                        }
                        innerTextField()
                    }
                )
            }
            Button(
                shape = RoundedCornerShape(20.dp),
                border = BorderStroke(2.dp, EmeraldGreen),
                onClick = { onClick.invoke() },
                elevation = ButtonDefaults.elevatedButtonElevation(defaultElevation = 2.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = EmeraldGreen,
                    contentColor = Color.White,
                ),
            ) {
                Text(
                    text = "Search",
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    maxLines = 1,
                    color = Color.White
                )
            }
        }
    }
}
