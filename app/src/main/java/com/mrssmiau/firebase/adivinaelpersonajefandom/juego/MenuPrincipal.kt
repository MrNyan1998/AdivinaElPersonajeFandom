package com.mrssmiau.firebase.adivinaelpersonajefandom.juego

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri.parse
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import com.mrssmiau.firebase.adivinaelpersonajefandom.R


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuPrincipal(navController: NavController) {
    var searchText by remember { mutableStateOf(TextFieldValue("")) }
    val context = LocalContext.current
    Scaffold(
        topBar = {
            TopAppBar(
                title = { BasicTextField(
                    value = searchText,
                    onValueChange = { searchText = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .background(Color.LightGray, shape = CircleShape)
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    singleLine = true
                )},
                actions = {
                    IconButton(onClick = { navController.navigate("Inicio") }) {
                        Icon(Icons.Default.Face, contentDescription = "")
                    }
                }
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Row {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(Icons.Default.Favorite, contentDescription = "Favoritos")
                    }
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(Icons.Filled.Refresh, contentDescription = "Historial")
                    }
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(Icons.Default.Face, contentDescription = "Siguiendo")
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Bienvenido. Gracias por jugar",
                    fontSize = 20.sp,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = "Esta app es desarrollada por mi (MrsMiau) y es de uso gratuito. Puedes apoyarme siguiendome en:",
                    fontSize = 16.sp
                )

                Row(
                    modifier = Modifier.padding(vertical = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    IconButton (onClick= {
                        openWebPage(context, "https://www.youtube.com/@MrssMiau")
                    }){
                        Image( painter = painterResource(R.drawable.youtube), contentDescription = "YouTube" )}
                    IconButton (onClick= {
                        openWebPage(context, "https://www.youtube.com/@MrssMiau")
                    }){
                        Image( painter = painterResource(R.drawable.facebook), contentDescription = "Facebook" )}
                    IconButton (onClick= {
                        openWebPage(context, "https://www.youtube.com/@MrssMiau")
                    }){
                        Image( painter = painterResource(R.drawable.github), contentDescription = "Github" )}
                }

                Text(text = "Siguiendo", fontSize = 18.sp, modifier = Modifier.padding(vertical = 8.dp))

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
        },
        bottomBar = { Button(
            onClick = { navController.navigate("Inicio") }) {
            Text("Mostrar todo")
            }
        }
    )
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
    try {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = parse(urlStr)
        if (intent.resolveActivity(context.packageManager) != null) {
            ContextCompat.startActivity(context, intent, null)
        } else {
            Toast.makeText(context, "Need a web browser app", Toast.LENGTH_SHORT).show()
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
}
