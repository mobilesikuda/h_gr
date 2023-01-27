package ru.sikuda.mobile.h_gr

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import ru.sikuda.mobile.h_gr.ui.tasks.TasksList
import ru.sikuda.mobile.h_gr.ui.theme.H_grTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            H_grTheme {
                val scaffoldState = rememberScaffoldState()
                val scope = rememberCoroutineScope()

                var winState by remember { mutableStateOf( AppDestination.HOME) }
                val buttonsList = arrayOf("Задания", "Разгрузки", "Навалы")

                Scaffold(
                    scaffoldState = scaffoldState,
                    topBar = {
                        AppBar(
                            onNavigationIconClick = {
                                scope.launch {
                                    scaffoldState.drawerState.open()
                                }
                            }
                        )
                    },
                    drawerGesturesEnabled = scaffoldState.drawerState.isOpen,
                    drawerContent = {
                        DrawerHeader()
                        DrawerBody(
                            items = listOf(
                                MenuItem(
                                    id = "home",
                                    title = "Начало",
                                    contentDescription = "Go to home screen",
                                    icon = Icons.Default.Home,
                                ),
                                MenuItem(
                                    id = "tasks",
                                    title = "List of tasks",
                                    contentDescription = "Go to list of tasks",
                                    icon = Icons.Default.Settings
                                ),
                                MenuItem(
                                    id = "help",
                                    title = "Help",
                                    contentDescription = "Get help",
                                    icon = Icons.Default.Info
                                ),
                            ),
                            onItemClick = {
                                scope.launch {
                                    scaffoldState.drawerState.close()
                                    when(it.id){
                                        "settings" -> winState = AppDestination.TASKS
                                    }
                                }
                            }
                        )
                    }
                ) {
                    it
                    when(winState){
                        AppDestination.TASKS -> TasksList()
                        else -> {
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
                                                .selectable(true, onClick = {
                                                    when (buttonsList[item]){
                                                        "Задания" -> winState = AppDestination.TASKS
                                                    }
                                                })
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
                    }
                }
            }
        }
    }
}

enum class AppDestination(name: String) {

    HOME("Home"),
    TASKS("Tasks"),
    TASK_DETAIL_ROUTE("Task"),

}

@Composable
fun MainView( ){
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
                        .selectable(true, onClick = {

                        })
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




