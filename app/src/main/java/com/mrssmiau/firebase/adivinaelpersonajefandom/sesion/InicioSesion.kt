package com.mrssmiau.firebase.adivinaelpersonajefandom.sesion

import android.content.Context
import android.icu.number.Scale
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.mrssmiau.firebase.adivinaelpersonajefandom.R
import java.nio.file.WatchEvent
import androidx.compose.foundation.Canvas
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.contentColorFor
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import com.google.firebase.annotations.concurrent.Background


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InicioSesion(navController: NavController) {
    val context = LocalContext.current
    val auth = Firebase.auth
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val grisclaro = colorResource(id=R.color.lightGray)
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.4f)
            .padding(16.dp)
    ) {
    Row (modifier = Modifier.fillMaxWidth().fillMaxHeight()) {
        Image(
            painter = painterResource(id = R.drawable.logoadivina),
            contentDescription = "Siguiendo",
            modifier = Modifier
                .scale(3f)
                .fillMaxWidth(0.9f)
                .align(Alignment.CenterVertically)
        )
    }
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(4.dp)
    ) {
        Text(
            text = "Inicia sesion",
            modifier = Modifier.align(Alignment.CenterHorizontally),
            fontWeight = FontWeight.Bold,
            fontSize = 26.sp
        )
        Text(
            text = "Ingresa los datos para continuar",
            modifier = Modifier.align(Alignment.CenterHorizontally)
                .padding(start = 36.dp, end = 36.dp),
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text(text = "Usuario") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 26.dp, end = 26.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                containerColor = Color.Transparent,
                focusedBorderColor = Color.LightGray,
                unfocusedBorderColor = grisclaro
            ),
            shape = RoundedCornerShape(20)
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text(text = "Contraseña") },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 26.dp, end = 26.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                containerColor = Color.Transparent,
                focusedBorderColor = Color.LightGray,
                unfocusedBorderColor = grisclaro,
            ),
            shape = RoundedCornerShape(20)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = {
                auth.signInWithEmailAndPassword(email, password).addOnSuccessListener {
                    Toast.makeText(context, "Bienvenido", Toast.LENGTH_LONG).show()
                    navController.navigate("Blank")
                }.addOnFailureListener {
                    Toast.makeText(context, "Correo o contraseña incorrecto", Toast.LENGTH_LONG)
                        .show()
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 26.dp, end = 26.dp),
            shape = RoundedCornerShape(20),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black
            )
        ) {
            Text(text = "Continuar")
        }
        Spacer(modifier = Modifier.padding(4.dp))
        Text(
            text = "¿No tienes cuenta? Crear una",
            modifier = Modifier.align(Alignment.CenterHorizontally)
                .padding(start = 36.dp, end = 36.dp)
                .clickable{
                    navController.navigate("Registro")
                },
            fontSize = 18.sp,
        )
        Row( modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 4.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Spacer(Modifier.fillMaxWidth(0.3f)
                .padding(start = 8.dp, end = 8.dp)
                .weight(1f)
                .align(Alignment.CenterVertically)
                .height(3.dp)
                .background(grisclaro, RectangleShape))
            Text(
                text = "O",
                modifier = Modifier.align(Alignment.CenterVertically)
                    .padding(0.dp),
                style = TextStyle(fontSize = 18.sp, lineHeight = 12.sp, color = Color.LightGray),
                fontSize = 18.sp,
            )
            Spacer(Modifier.fillMaxWidth(0.3f)
                .padding(start = 8.dp, end = 8.dp)
                .weight(1f)
                .align(Alignment.CenterVertically)
                .height(3.dp)
                .background(grisclaro, RectangleShape))
        }
        Button(
            onClick = {
                auth.signInWithEmailAndPassword(email, password).addOnSuccessListener {
                    Toast.makeText(context, "Bienvenido", Toast.LENGTH_LONG).show()
                    navController.navigate("Blank")
                }.addOnFailureListener {
                    Toast.makeText(context, "Correo o contraseña incorrecto", Toast.LENGTH_LONG)
                        .show()
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 26.dp, end = 26.dp),
            shape = RoundedCornerShape(20),
        ) {
            Text(text = "Continuar con google")
        }
        Spacer(modifier = Modifier.padding(8.dp))
        Text(
            text = "Al clickar en continuar, estas aceptando nuestros terminos de servicio y politicas de privacidad.",
            modifier = Modifier.align(Alignment.CenterHorizontally)
                .padding(start = 36.dp, end = 36.dp),
            fontSize = 12.sp,
            style = MaterialTheme.typography.bodyLarge.copy( textAlign = TextAlign.Center, fontSize = 12.sp, color = Color.LightGray),

        )
    }
}
