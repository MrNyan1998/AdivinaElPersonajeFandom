package com.mrssmiau.firebase.adivinaelpersonajefandom.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mrssmiau.firebase.adivinaelpersonajefandom.Blank
import com.mrssmiau.firebase.adivinaelpersonajefandom.juego.Juego
import com.mrssmiau.firebase.adivinaelpersonajefandom.juego.Menu
import com.mrssmiau.firebase.adivinaelpersonajefandom.juego.MenuAdmin
import com.mrssmiau.firebase.adivinaelpersonajefandom.juego.MenuPrincipal
import com.mrssmiau.firebase.adivinaelpersonajefandom.sesion.Inicio
import com.mrssmiau.firebase.adivinaelpersonajefandom.sesion.Registro

@Composable
fun NavManager(ruta: String) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "Blank"){
        composable("Blank"){
            Blank(navController)
        }
        composable("MenuPrincipal"){
            MenuPrincipal(navController)
        }
        composable("Inicio"){
            Inicio(navController)
        }
        composable("Menu"){
            Menu(navController)
        }
        composable("MenuAdmin"){
            MenuAdmin(navController)
        }
        composable("Juego"){
            Juego(navController)
        }
        composable("Registro"){
            Registro(navController)
        }
    }
}