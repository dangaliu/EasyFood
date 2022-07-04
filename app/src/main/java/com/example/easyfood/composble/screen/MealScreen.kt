package com.example.easyfood.composble.screen

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.example.easyfood.R
import com.example.easyfood.db.MealDatabase
import com.example.easyfood.ui.theme.Typography
import com.example.easyfood.ui.theme.darkBlue
import com.example.easyfood.ui.theme.primary
import com.example.easyfood.viewmodel.MealScreenViewModel

@Composable
fun MealScreen(
    navController: NavHostController,
    mealScreenViewModel: MealScreenViewModel,
    mealId: Int? = null
) {
    val meal = mealScreenViewModel.meal.value
    val context = LocalContext.current
    if (mealId != null) {
        mealScreenViewModel.getMealById(mealId)
    }
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
        ) {
            val (mealImg, favBtn, content, title) = createRefs()
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .background(primary)
                    .constrainAs(mealImg) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                painter = rememberAsyncImagePainter(model = meal?.strMealThumb),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
            Text(
                text = meal?.strMeal ?: "Meal",
                style = Typography.h1.copy(color = Color.White),
                modifier = Modifier.constrainAs(title) {
                    start.linkTo(mealImg.start, 16.dp)
                    bottom.linkTo(mealImg.bottom, 16.dp)
                }
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .constrainAs(content) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(mealImg.bottom)
                    }
                    .padding(4.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Filled.ShoppingCart,
                        null,
                        modifier = Modifier.size(24.dp),
                        tint = darkBlue
                    )
                    Text(
                        text = "Category: ${meal?.strCategory ?: ""}",
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
                        text = "Area: ${meal?.strArea ?: ""}",
                        style = Typography.h3
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "-Instructions",
                    style = Typography.h2
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = meal?.strInstructions ?: "",
                    style = Typography.h3.copy(color = Color.Black)
                )
                Image(
                    painter = painterResource(R.drawable.youtube_ic),
                    contentDescription = null,
                    modifier = Modifier
                        .scale(0.5f)
                        .align(Alignment.CenterHorizontally)
                        .clickable {
                            context.startActivity(
                                Intent(
                                    Intent.ACTION_VIEW,
                                    Uri.parse(meal?.strYoutube ?: "")
                                )
                            )
                        }
                )
            }
            FloatingActionButton(
                onClick = {
                    meal?.let {
                        mealScreenViewModel.insertMeal(it)
                        Toast.makeText(context, "Meal is added", Toast.LENGTH_SHORT).show()
                    }
                },
                shape = CircleShape,
                backgroundColor = primary,
                modifier = Modifier
                    .size(50.dp)
                    .constrainAs(favBtn) {
                        end.linkTo(parent.end, 16.dp)
                        top.linkTo(mealImg.bottom)
                        bottom.linkTo(mealImg.bottom)
                    },
                contentColor = Color.White
            ) {
                Icon(
                    imageVector = Icons.Default.FavoriteBorder,
                    contentDescription = null
                )
            }
        }
    }
}
