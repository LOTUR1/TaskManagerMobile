package com.example.taskmanager

import androidx.compose.foundation.Image
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class Task(
    val title: String,
    val description: String
) {
    @Composable
    fun AddTask(){
        var containerColor by remember { mutableStateOf(Color.Black) }

        Button(onClick = {
            containerColor = if (containerColor == Color.Black) Color.Red else Color.Black
        },
            shape = CircleShape,
            modifier = Modifier.size(100.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black
            )
        ) {
            Image(
                painter = painterResource(id = R.drawable.add),
                contentDescription = "Add task",
                colorFilter = ColorFilter.tint(Color.White),
                modifier = Modifier.size(80.dp),
                contentScale = ContentScale.Crop
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Show(){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Task(rememberNavController())
    }
}

@Composable
fun Task1(){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "screen_a") {
        composable("screen_a") { AddTask(navController) }
        composable("screen_b") { Task(navController) }
    }
}

@Composable
fun Task(navController: NavHostController){
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.align(Alignment.TopCenter)
        ) {
            val scrollState = rememberScrollState()

            BasicTextField(
                value = title,
                onValueChange = { title = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(scrollState),
                maxLines = 1,
                textStyle = TextStyle(
                    color = Color.Black,
                    fontSize = 40.sp,
                    textAlign = TextAlign.Center,
                ),
                decorationBox = { innerTextField ->
                    if (title.isEmpty()) {
                        Text(
                            text = "Title",
                            style = TextStyle(
                                color = Color.Gray,
                                fontSize = 40.sp,
                                textAlign = TextAlign.Center
                            )
                        )
                    }
                    innerTextField()
                }
            )

            LaunchedEffect(title) {
                scrollState.scrollTo(scrollState.maxValue)
            }

            HorizontalDivider(
                thickness = 1.dp,
                color = Color.Gray,
                modifier = Modifier.padding(8.dp)
            )

            BasicTextField(
                value = description,
                onValueChange = { description = it },
                modifier = Modifier.fillMaxWidth(),
                textStyle = TextStyle(
                    color = Color.Black,
                    fontSize = 30.sp
                ),
                decorationBox = { innerTextField ->
                    if (description.isEmpty()) {
                        Text(
                            text = "Description",
                            style = TextStyle(
                                color = Color.Gray,
                                fontSize = 30.sp
                            )
                        )
                    }
                    innerTextField()
                }
            )
        }

        Button(
            onClick = {
                navController.navigate("screen_a")
            },
            shape = CircleShape,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(20.dp)
                .size(100.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Green
            )
        ) {
            Image(
                painter = painterResource(id = R.drawable.check),
                contentDescription = "Add task",
                colorFilter = ColorFilter.tint(Color.White),
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Fit
            )
        }
    }
}

@Composable
fun AddTask(navController: NavHostController){
    Button(onClick = {
        navController.navigate("screen_b")
    },
        shape = CircleShape,
        modifier = Modifier.size(100.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Black
        )
    ) {
        Image(
            painter = painterResource(id = R.drawable.add),
            contentDescription = "Add task",
            colorFilter = ColorFilter.tint(Color.White),
            modifier = Modifier.size(80.dp),
            contentScale = ContentScale.Crop
        )
    }
}