package com.mrssmiau.firebase.adivinaelpersonajefandom

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth

@Composable
fun Blank(navController: NavController) {
    LaunchedEffect(Unit){
        //Revisar si hay una sesion guardada
        if (!FirebaseAuth.getInstance().currentUser?.email.isNullOrEmpty()) {
            navController.navigate("MenuPrincipal")
        }else{
            navController.navigate("MenuPrincipal")
        }
    }
}
