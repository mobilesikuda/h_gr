package ru.sikuda.mobile.h_gr

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import kotlinx.coroutines.launch
import ru.sikuda.mobile.h_gr.ui.tasks.TasksDetail
import ru.sikuda.mobile.h_gr.ui.tasks.TasksList
import ru.sikuda.mobile.h_gr.ui.theme.H_grTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            H_grTheme {
                val navController = rememberNavController()

                val scaffoldState = rememberScaffoldState()
                val scope = rememberCoroutineScope()

                //var winState by remember { mutableStateOf( AppDestination.HOME) }
                val buttonsList = arrayOf("Задания", "Разгрузки", "Навалы")

                Scaffold(scaffoldState = scaffoldState, topBar = {
                    AppBar(onNavigationIconClick = {
                        scope.launch {
                            scaffoldState.drawerState.open()
                        }
                    })
                }, drawerGesturesEnabled = scaffoldState.drawerState.isOpen, drawerContent = {
                    DrawerHeader()
                    DrawerBody(items = listOf(
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
                    ), onItemClick = {
                        scope.launch {
                            scaffoldState.drawerState.close()
                            when (it.id) {
                                "tasks" -> navController.navigate("${NavRoute.TASKS}/Common tasks")
                                else -> navController.navigate(NavRoute.HOME) {
                                    popUpTo(NavRoute.HOME) { inclusive = true }
                                }
                            }
                        }
                    })
                }) { paddingValues ->
                    MyNavHost(navController, paddingValues)
                }
            }
        }
    }
}


object NavRoute {
    const val HOME = "Home"
    const val TASKS = "Tasks"
    const val TASK_DETAIL = "Task Detail"
}

@Composable
fun MyNavHost(navHostController: NavHostController, paddingValues: PaddingValues) {
    NavHost(
        navController = navHostController, startDestination = NavRoute.HOME
    ) {
        val routeTasks = "${NavRoute.TASKS}/{argument}"
        val routeTaskDetail = "${NavRoute.TASK_DETAIL}/{visible}"
        composable(NavRoute.HOME) {
            MainView {
                navHostController.navigate("${NavRoute.TASKS}/Common tasks")
            }
        }
        composable(routeTasks, arguments = listOf(navArgument(name = "argument") {})
        ) { navEntry ->
            TasksList(navEntry.arguments?.getString("argument")) {
                navHostController.navigate("${NavRoute.TASK_DETAIL}/false")
            }
        }
        composable(routeTaskDetail,
            arguments = listOf(navArgument("visible"){
                type = NavType.BoolType
            }),
        ){
            TasksDetail(name = "test") {

                    navHostController.navigate(NavRoute.HOME){
                    popUpTo(NavRoute.HOME){inclusive = true}
                }
            }
        }

    }
}

@Composable
fun MainView(
    onNavigationTasks: () -> Unit
) {
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
                            when (item) {
                                0 -> onNavigationTasks()
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




