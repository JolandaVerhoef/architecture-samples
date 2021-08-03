package com.example.android.architecture.blueprints.todoapp

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.android.architecture.blueprints.todoapp.MainDestinations.ADD_EDIT_TASK_ROUTE
import com.example.android.architecture.blueprints.todoapp.MainDestinations.STATISTICS_ROUTE
import com.example.android.architecture.blueprints.todoapp.MainDestinations.TASKS_ROUTE
import com.example.android.architecture.blueprints.todoapp.MainDestinations.TASK_DETAIL_ROUTE
import com.example.android.architecture.blueprints.todoapp.MainDestinations.TASK_ID_KEY
import com.example.android.architecture.blueprints.todoapp.statistics.Statistics
import com.example.android.architecture.blueprints.todoapp.taskdetail.TaskDetail
import com.example.android.architecture.blueprints.todoapp.tasks.TasksScreen

object MainDestinations {
    const val TASKS_ROUTE = "tasks"
    const val TASK_DETAIL_ROUTE = "task"
    const val ADD_EDIT_TASK_ROUTE = "add"
    const val STATISTICS_ROUTE = "statistics"
    const val TASK_ID_KEY = "taskId"
}


@Composable
fun TodoNavGraph(
    navController: NavHostController = rememberNavController(),
    startDestination: String = TASKS_ROUTE
) {
    val actions = remember(navController) { MainActions(navController) }
    val coroutineScope = rememberCoroutineScope()

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(TASKS_ROUTE) {
            TasksScreen(
                actions.navigateToTaskDetail
            )
        }
        composable("$ADD_EDIT_TASK_ROUTE/{$TASK_ID_KEY}") { backStackEntry ->
            val taskId = backStackEntry.arguments?.getString(TASK_ID_KEY)
            checkNotNull(taskId) { "Cannot open Add/Edit Task screen without task id" }
            TaskDetail(taskId)
        }
        composable("$TASK_DETAIL_ROUTE/{$TASK_ID_KEY}") { backStackEntry ->
            val taskId = backStackEntry.arguments?.getString(TASK_ID_KEY)
            checkNotNull(taskId) { "Cannot open Task detail screen without task id" }
            TaskDetail(taskId)
        }
        composable(STATISTICS_ROUTE) {
            Statistics()
        }
    }
}

/**
 * Models the navigation actions in the app.
 */
class MainActions(navController: NavHostController) {
    val navigateToTaskDetail: (String) -> Unit = { taskId: String ->
        navController.navigate("${TASK_DETAIL_ROUTE}/$taskId")
    }
    val upPress: () -> Unit = {
        navController.navigateUp()
    }
}
