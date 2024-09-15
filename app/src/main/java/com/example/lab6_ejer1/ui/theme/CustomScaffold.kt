package com.example.lab6_ejer1.ui.theme

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.google.rpc.Help

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopBar(navController: NavHostController) {
    TopAppBar(
        navigationIcon = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Rounded.Menu, contentDescription = null)
            }
        },
        title = { Text(text = "Sample Title", color = Color.White) },
        actions = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Rounded.Search, contentDescription = null)
            }
            IconButton(onClick = {
                Log.d("Navigation", "Navigating to profile")
                navController.navigate("profile")
            }) {
                Icon(imageVector = Icons.Outlined.AccountCircle, contentDescription = "Perfil")
            }
        },
    )
}

@Composable
fun CustomScaffold(navController: NavHostController) {
    var clickCount by remember { mutableStateOf(0) }

    Scaffold(
        topBar = { CustomTopBar(navController) },
        bottomBar = { CustomBottomBar(navController) },
        floatingActionButton = { CustomFAB { clickCount++ } },
        content = { padding ->
            NavHost(
                navController = navController,
                startDestination = "home"
            ) {
                composable("home") { CustomContent(padding, clickCount) }
                composable("profile") { UserProfileScreen() }
                composable("settings") { SettingsScreen() }
                composable("notifications") { NotificationsScreen() }
                composable("help") { HelpScreen() }
            }
        }
    )
}

@Composable
fun CustomBottomBar(navController: NavHostController) {
    BottomAppBar {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            IconButton(onClick = { navController.navigate("home") }) {
                Icon(Icons.Filled.Home, contentDescription = "Home description")
            }
            IconButton(onClick = { navController.navigate("settings") }) {
                Icon(
                    Icons.Filled.Settings,
                    contentDescription = "Settings description",
                )
            }
            IconButton(onClick = { navController.navigate("notifications") }) {
                Icon(
                    Icons.Filled.Notifications,
                    contentDescription = "Notifications description",
                )
            }
            IconButton(onClick = { navController.navigate("help") }) {
                Icon(
                    Icons.Filled.Info,
                    contentDescription = "Help description",
                )
            }
        }
    }
}

@Composable
fun CustomFAB(onClick: () -> Unit) {
    FloatingActionButton(
        onClick = onClick
    ) {
        Text(
            fontSize = 24.sp,
            text = "+"
        )
    }
}

@Composable
fun CustomContent(padding: PaddingValues, clickCount: Int) {
    Column(
        // Modificadores de estilo de la columna
        modifier = Modifier
            // Ocupar todo el espacio disponible
            .fillMaxSize()
            .padding(padding),

        // Contenido de la aplicación
        content = {
            Text(text = "My app content")
            Text("Button clicked $clickCount times")
        }
    )
}

