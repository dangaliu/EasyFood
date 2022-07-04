package com.example.easyfood.composble.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.easyfood.model.PopularMeal
import com.example.easyfood.ui.theme.Typography

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CategoryMealComponent(
    meal: PopularMeal,
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Card(
        onClick = onClick,
        shape = RoundedCornerShape(4.dp),
        modifier = modifier
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
            Text(
                text = meal.strMeal,
                style = Typography.h2.copy(fontSize = 14.sp),
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center
            )
        }
    }
}