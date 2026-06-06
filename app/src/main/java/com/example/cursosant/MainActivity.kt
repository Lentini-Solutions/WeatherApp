package com.example.cursosant

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.rememberNavController
import com.example.cursosant.navigation.AppNavHost
import com.example.cursosant.navigation.Destination
import com.example.cursosant.ui.theme.CursosANTTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CursosANTTheme {

                val navController = rememberNavController()
                val defaultRoute = Destination.WEATHER
                var selectedDestination by rememberSaveable {
                    mutableIntStateOf(defaultRoute.ordinal)
                }

                val navOptionsBuilder: NavOptionsBuilder.() -> Unit = {
                    popUpTo(navController.graph.id){
                        inclusive = true
                        saveState = true //save old state to not rebuilder
                    }
                    launchSingleTop = true // prevent destination repeat -replace route and restore old route
                    restoreState = true // restore de state
                }

                Scaffold(modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(
                            title = {
                                Text(
                                    text =  stringResource(R.string.app_name),
                                    modifier = Modifier.fillMaxWidth(),
                                    textAlign = TextAlign.Center
                                )
                            },
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = Color.Cyan,
                                titleContentColor = Color.Black
                            ),
                        )
                    },
                    bottomBar = {
                        NavigationBar(windowInsets = NavigationBarDefaults.windowInsets) {
                            Destination.entries.forEachIndexed { index, destination ->
                                NavigationBarItem(
                                    selected = selectedDestination == index,
                                    onClick = {
                                        navController.navigate(route = destination.route,
                                            navOptionsBuilder)
                                        selectedDestination = index
                                    },
                                    icon = {
                                        Icon(
                                            destination.icon,
                                            destination.contentDescription
                                        )
                                    },
                                    label = {
                                        Text(text = stringResource(destination.labelRes))
                                    }
                                )
                            }
                        }
                    }
                ) { innerPadding ->
                    AppNavHost(
                        modifier = Modifier.padding(innerPadding),
                        navController = navController,
                        destination = defaultRoute
                    )
                }
            }
        }
    }
}