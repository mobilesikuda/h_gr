package ru.sikuda.mobile.h_gr

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import ru.sikuda.mobile.h_gr.AppDestinations.TASK_DETAIL_ID_KEY
import ru.sikuda.mobile.h_gr.ui.tasks.TasksList

private object AppDestinations {
    const val ROOT_ROUTE = "root"

    //tasks
    const val TASKS_ROUTE = "tasks"
    const val TASK_DETAIL_ROUTE = "task"
    const val TASK_DETAIL_ID_KEY = "taskId"

}

@Composable
fun AppNavigation(
    startDestination: String = AppDestinations.ROOT_ROUTE
) {
    val navController = rememberNavController()
    val actions = remember(navController) { AppActions(navController) }

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(
            AppDestinations.ROOT_ROUTE
        ) {
            RootView( 0, actions.listTasks )
        }
        composable(
            AppDestinations.TASKS_ROUTE
        ) {
            RootView( 1, actions.listTasks)
        }
        composable(
            "${AppDestinations.TASK_DETAIL_ROUTE}/{$TASK_DETAIL_ID_KEY}",
            arguments = listOf(
                navArgument(TASK_DETAIL_ID_KEY) {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->
            val arguments = requireNotNull(backStackEntry.arguments)
            //CatDetails(
            //    catId = arguments.getInt(CAT_DETAIL_ID_KEY),
            //    navigateUp = actions.navigateUp
            //)
        }
    }
}

private class AppActions(
    navController: NavHostController
) {
    //tasks
    val selectedTask: (Int) -> Unit = { catId: Int ->
        navController.navigate("${AppDestinations.TASK_DETAIL_ROUTE}/$catId")
    }

    val listTasks: () -> Unit = {
        navController.navigate(AppDestinations.TASKS_ROUTE)
    }

    //others
    val navigateUp: () -> Unit = {
        navController.navigateUp()
    }

}
