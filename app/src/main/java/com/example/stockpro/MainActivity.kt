package com.example.stockpro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.stockpro.ui.theme.StockProTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            StockProTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    StockProApp()
                }
            }
        }
    }
}

@Composable
fun StockProApp() {

    val navController = rememberNavController()

    val stockViewModel: StockViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = "ingreso"
    ) {
        composable("ingreso") {
            PantallaIngreso(navController = navController)
        }

        composable(
            route = "catalogo/{nombreOperario}",
            arguments = listOf(
                navArgument("nombreOperario") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->

            val nombreOperario = backStackEntry.arguments?.getString("nombreOperario") ?: ""

            PantallaCatalogoInventario(
                navController = navController,
                viewModel = stockViewModel,
                nombreOperario = nombreOperario
            )
        }

        composable(
            route = "editar/{productoId}",
            arguments = listOf(
                navArgument("productoId") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->

            val productoId = backStackEntry.arguments?.getInt("productoId") ?: 0

            PantallaEditarStock(
                navController = navController,
                viewModel = stockViewModel,
                productoId = productoId
            )
        }

        composable("reporte") {
            PantallaReporteFinanciero(
                navController = navController,
                viewModel = stockViewModel
            )
        }
    }
}

