package ru.sikuda.mobile.h_gr

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.sikuda.mobile.h_gr.ui.tasks.TasksList
import ru.sikuda.mobile.h_gr.ui.theme.H_grTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            H_grTheme {
                AppNavigation()
            }
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun RootView( idScreen: Int = 0, listTasks: () -> Unit  ) {

    val result = remember { mutableStateOf("") }
    val expanded = remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Top app bar")
                },

                actions = {

                    Box(
                        Modifier
                            .wrapContentSize(Alignment.TopCenter)
                    ) {
                        IconButton(onClick = {
                            expanded.value = true
                            result.value = "More icon clicked"
                        }) {
                            Icon(
                                Icons.Filled.Menu,
                                contentDescription
                                = "Localized description"
                            )
                        }

                        DropdownMenu(
                            expanded = expanded.value,
                            onDismissRequest = { expanded.value = false },
                        ) {
                            DropdownMenuItem(onClick = {
                                expanded.value = false
                                result.value = "First item clicked"
                            }) {
                                Text("First Item")
                            }

                            Divider()

                            DropdownMenuItem(onClick = {
                                expanded.value = false
                                result.value = "Second item clicked"
                            }) {
                                Text("Second item")
                            }

                        }
                    }
                },

                //backgroundColor = Color(0xFDCD7F32),
                elevation = AppBarDefaults.TopAppBarElevation
            )
        },

        content = { it ->
            when(idScreen){
                1 -> TasksList(listTasks )
                else -> MainView( listTasks )

            }
        }
    )
}

@Composable
fun MainView(
    listTasks: () -> Unit
){
    val buttonsList = arrayOf("Задания", "Разгрузки", "Навалы")

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background,
    ) {

        LazyVerticalGrid(
            GridCells.Fixed(2),
            horizontalArrangement = Arrangement.Center,
            verticalArrangement = Arrangement.Center

        ) {
            items(buttonsList.size) { item ->

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(20.dp)
                        .selectable(true, onClick = { listTasks() })
                ) {

                    Image(
                        ImageVector.vectorResource(R.drawable.ic_launcher_background),
                        contentDescription = buttonsList[item],
                        alignment = Alignment.TopCenter
                    )

                    Text(
                        text = buttonsList[item],
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center,
                    )
                }
            }
        }
    }
}



