package com.example.android.architecture.blueprints.todoapp

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.jetnews.ui.theme.TodoTheme
import kotlinx.coroutines.launch

@Composable
fun TodoApp() {
    TodoTheme {

        val navController = rememberNavController()
        val coroutineScope = rememberCoroutineScope()
        val scaffoldState = rememberScaffoldState()

        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route ?: MainDestinations.TASKS_ROUTE
        Scaffold(
            scaffoldState = scaffoldState,
            drawerContent = {
                // TODO: Add Drawer Content
                Text("Drawer content - to be filled")
            },
            topBar = {
                TopAppBar(
                    title = { Text(stringResource(id = R.string.app_name)) },
                    navigationIcon = {
                        IconButton(onClick = { coroutineScope.launch { scaffoldState.drawerState.open() } }) {
                            Icon(
                                Icons.Default.Menu,
                                contentDescription = stringResource(R.string.nav_app_bar_open_drawer_description)
                            )
                        }
                    }
                )
            }
        ) { innerPadding ->
            TodoNavGraph()
            Text(modifier = Modifier.padding(innerPadding), text = "Hello world!")
        }
    }
}