package com.example.android.architecture.blueprints.todoapp.tasks

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.android.architecture.blueprints.todoapp.tasks.TasksViewModel.TasksViewState

@Composable
fun TasksScreen(
    navigateToTaskDetail: (String) -> Unit
) {
    val viewModel: TasksViewModel = viewModel()
    val viewState: State<TasksViewState> = viewModel.viewState.collectAsState()

    TasksScreen(
        viewState = viewState.value,
        navigateToTaskDetail = navigateToTaskDetail
    )
}

@Composable
fun TasksScreen(
    viewState: TasksViewState,
    navigateToTaskDetail: (String) -> Unit
) {

    Text("Tasks overview")

}