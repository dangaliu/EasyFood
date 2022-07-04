package com.example.easyfood.composble.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.easyfood.model.Meal
import com.example.easyfood.ui.theme.Typography
import com.example.easyfood.ui.theme.darkBlue

@Composable
fun BottomSheetContent(
    meal: Meal,
    onClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .padding(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = meal.strMealThumb,
            contentDescription = meal.strMeal,
            modifier = Modifier
                .width(70.dp)
        )
        Spacer(Modifier.width(4.dp))
        Column() {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Filled.ShoppingCart,
                    null,
                    modifier = Modifier.size(24.dp),
                    tint = darkBlue
                )
                Text(
                    text = "Category: ${meal.strCategory ?: ""}",
                    style = Typography.h3
                )
                Spacer(modifier = Modifier.width(40.dp))
                Icon(
                    imageVector = Icons.Filled.Home,
                    null,
                    modifier = Modifier.size(24.dp),
                    tint = darkBlue
                )
                Text(
                    text = "Area: ${meal.strArea ?: ""}",
                    style = Typography.h3
                )
            }
            Spacer(Modifier.height(4.dp))
            meal.strMeal?.let {
                Text(
                    text = it,
                    style = Typography.h3.copy(Color.Black)
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Read more...",
                style = Typography.h3.copy(Color.Black),
                modifier = Modifier.clickable {  onClick() }
            )
        }
    }
}