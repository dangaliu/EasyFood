package com.example.easyfood.composble.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.easyfood.model.Meal
import com.example.easyfood.model.PopularMeal
import com.example.easyfood.ui.theme.Typography

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FavoriteMealComponent(
    meal: Meal,
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier,
    onCloseClick: () -> Unit = {}
) {
    Card(
        onClick = onClick,
        shape = RoundedCornerShape(4.dp),
        modifier = modifier
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AsyncImage(
                    model = meal.strMealThumb,
                    contentDescription = null,
                    modifier = Modifier.weight(9f),
                    contentScale = ContentScale.Crop
                )
                meal.strMeal?.let {
                    Text(
                        text = it,
                        style = Typography.h2.copy(fontSize = 14.sp),
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Center
                    )
                }
            }
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = null,
                modifier = Modifier
                    .padding(8.dp)
                    .size(24.dp)
                    .align(Alignment.TopEnd)
                    .clickable {
                        onCloseClick()
                    },
                tint = Color.White
            )
        }
    }
}