package com.mrssmiau.firebase.adivinaelpersonajefandom.juego

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import com.mrssmiau.firebase.adivinaelpersonajefandom.R

var pantalla = ""


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuPrincipal(navController: NavController) {
    var searchText by remember { mutableStateOf(TextFieldValue("")) }
    val context = LocalContext.current
    val grisclaro = colorResource(id=R.color.lightGray)         //Variable iniciales
    val rosaclaro = colorResource(id = R.color.lightPink)
    var pantallaseleccionada by remember { mutableIntStateOf(0) }
    val tabs = listOf("pantallaInicio", "pantallaFavoritos", "pantallaHistorial", "pantallaSiguiendo")

    Column (        //layout principal
        modifier = Modifier.fillMaxSize(
        )
    ) {
        Row (modifier = Modifier        //Barra de busqueda y foto de perfil
            .fillMaxWidth()
            .fillMaxHeight(0.10f)){
            BasicTextField(             //Barra de busqueda
                value = searchText,
                onValueChange = { searchText = it },
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(0.8f)
                    .align(Alignment.CenterVertically)
                    .padding(8.dp)
                    .background(grisclaro, shape = RoundedCornerShape(20))
                    .padding(horizontal = 8.dp, vertical = 8.dp),
                singleLine = true
                ,decorationBox = { innerTextField ->
                    Row( modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 4.dp)
                        ,verticalAlignment = Alignment.CenterVertically ) {
                        Icon( imageVector = Icons.Default.Search
                            ,contentDescription = "Search icon"
                            ,tint = Color.Gray
                            ,modifier = Modifier.scale(1.5f) )
                        Spacer(modifier = Modifier.width(8.dp))
                        if (searchText.text.isEmpty()) {
                            Text( text = "Buscar"
                                ,color = Color.Gray
                                ,modifier = Modifier.align(Alignment.CenterVertically) ) }
                        innerTextField() } }
            )
            IconButton(modifier = Modifier.fillMaxWidth(0.15f)      //Foto de perfil
                .weight(0.3f)
                .padding(8.dp)
                .padding(horizontal = 8.dp, vertical = 8.dp)
                .fillMaxHeight()
                .align(Alignment.CenterVertically)
                ,onClick = { navController.navigate("Inicio") }) {
                Icon(Icons.Default.Face, contentDescription = "", modifier = Modifier.scale(3f))
            }
        }
        Row (modifier = Modifier //Botones de favorito. historial y seguiendo
            .fillMaxWidth()
            .padding(vertical = 0.dp, horizontal = 8.dp)) {
            OutlinedButton(modifier = Modifier          //Boton favoritos
                .padding(4.dp)
                .weight(1f),
                onClick = { pantallaseleccionada=1 },
                shape = RoundedCornerShape(20),
                contentPadding = PaddingValues(0.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                    contentColor = Color.Black
                ),
                border = BorderStroke(2.dp, grisclaro)) {
                Icon(Icons.Default.Favorite, contentDescription = "Favoritos")
                Text("Favoritos")
            }
            OutlinedButton(modifier = Modifier          //Boton Historial
                .padding(4.dp)
                .weight(1f),
                onClick = { pantallaseleccionada=2 },
                shape = RoundedCornerShape(20),
                contentPadding = PaddingValues(0.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                    contentColor = Color.Black
                ),
                border = BorderStroke(2.dp, grisclaro)) {
                Icon(Icons.Filled.Refresh, contentDescription = "Historial")
                Text("Historial")
            }
            OutlinedButton(modifier = Modifier          //Boton Siguiendo
                .padding(4.dp)
                .weight(1f),
                onClick = { pantallaseleccionada=3 },
                shape = RoundedCornerShape(20),
                contentPadding = PaddingValues(0.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                    contentColor = Color.Black
                ),
                border = BorderStroke(2.dp, grisclaro)) {
                Icon(Icons.Default.Face, contentDescription = "Siguiendo")
                Text("Siguiendo")
            }
        }
        Spacer(modifier = Modifier.padding(4.dp))
        when(pantallaseleccionada){
            0 -> pantallaInicial(context,navController)
            1 -> pantallaFavoritos(context,navController)
            2 -> pantallaHistorial(context,navController)
            3 -> pantallaSiguiendo(context,navController)
        }
    }
}


@Composable
fun FollowingItem(imageRes: ImageVector, text: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Image for Following Item
        // You can use any image loading library to load the images here
        // For example, Coil or Picasso
        Text(text = text, fontSize = 16.sp)
    }
}

@Composable
fun NewMapItem(imageRes: Int, text: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Image for New Map Item
        // Use any image loading library to load images here
        // For example, Coil or Picasso
        Text(text = text, fontSize = 16.sp)
    }
}

fun openWebPage(context: Context, urlStr: String?) {
    if (urlStr.isNullOrEmpty()) {
        Toast.makeText(context, "URL is null or empty", Toast.LENGTH_SHORT).show()
        return
    }
    try {
        val uri = Uri.parse(urlStr)
        val intent = Intent(Intent.ACTION_VIEW, uri)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        val chooser = Intent.createChooser(intent, "Open with")
            ContextCompat.startActivity(context, chooser, null)
    } catch (e: Exception) {
        Toast.makeText(context, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
        e.printStackTrace()
    }
}




@Composable
fun pantallaInicial(context: Context, navController: NavController){
    val grisclaro = colorResource(id=R.color.lightGray)         //Variable iniciales
    val rosaclaro = colorResource(id = R.color.lightPink)
    Column (verticalArrangement = Arrangement.Center        //Columna de mensaje de bienvenida
        ,horizontalAlignment = Alignment.CenterHorizontally){
        Column(                                         //Columna del cuerpo
            modifier = Modifier
                .fillMaxWidth(0.95f)
                .fillMaxHeight(0.3f)
                .background(color = rosaclaro, shape = RoundedCornerShape(20))
                .padding(4.dp)
        ) {
            Row {
                Image(
                    painter = painterResource(id = R.drawable.logoadivina),
                    contentDescription = "Siguiendo",
                    modifier = Modifier
                        .scale(4f)
                        .fillMaxWidth(0.6f)
                        .align(Alignment.CenterVertically)
                        .weight(0.6f)
                )
                Spacer(modifier = Modifier.padding(8.dp))
                Column(modifier = Modifier.fillMaxWidth(0.7f).align(Alignment.CenterVertically)) {
                    Text(
                        text = "Bienvenido.",
                        fontSize = 20.sp,
                        modifier = Modifier.padding(top = 8.dp),
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Esta app es desarrollada por mi (MrsMiau) y es de uso gratuito. Sigueme en:",
                        fontSize = 16.sp,
                        modifier = Modifier.padding(end = 4.dp, top = 4.dp)
                    )

                    Row(
                        modifier = Modifier.padding(vertical = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        IconButton(onClick = {
                            openWebPage(context, "https://www.youtube.com/@MrssMiau")
                        }) {
                            Image(
                                painter = painterResource(R.drawable.youtube),
                                contentDescription = "YouTube"
                            )
                        }
                        IconButton(onClick = {
                            openWebPage(context, "https://www.facebook.com/")
                        }) {
                            Image(
                                painter = painterResource(R.drawable.facebook),
                                contentDescription = "Facebook"
                            )
                        }
                        IconButton(onClick = {
                            openWebPage(context, "https://github.com/MrNyan1998/AdivinaElPersonajeFandom")
                        }) {
                            Image(
                                painter = painterResource(R.drawable.github),
                                contentDescription = "Github"
                            )
                        }
                    }
                }
            }
        }
        Spacer(modifier = Modifier.padding(8.dp))

        Text(text = "Mapas en tendencia", fontSize = 18.sp, modifier = Modifier.padding(start = 16.dp).align(Alignment.Start), fontWeight = FontWeight.Black)

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            FollowingItem(Icons.Default.AccountCircle, "Peras 117")
            FollowingItem(Icons.Default.AccountCircle, "Rebanada de sandia")
            FollowingItem(Icons.Default.AccountCircle, "Alex L. Leon")
            FollowingItem(Icons.Default.AccountCircle, "Fungi Master")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Mapas nuevos", fontSize = 18.sp, modifier = Modifier.padding(vertical = 8.dp))

        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            NewMapItem(R.drawable.mapa, "Gemas de cristal")
            NewMapItem(R.drawable.mapa, "MLP G4 personajes")
            NewMapItem(R.drawable.mapa, "MLP G5")
            NewMapItem(R.drawable.mapa, "Genshin impact")
            NewMapItem(R.drawable.mapa, "Como entrenar a tu dragon")
            NewMapItem(R.drawable.mapa, "GG no JG el videojuego")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(text = "Mostrar todos")
        }
    }
}

@Composable
fun pantallaFavoritos(context: Context,navController: NavController){

}


@Composable
fun pantallaHistorial(context: Context,navController: NavController){

}


@Composable
fun pantallaSiguiendo(context: Context,navController: NavController){

}