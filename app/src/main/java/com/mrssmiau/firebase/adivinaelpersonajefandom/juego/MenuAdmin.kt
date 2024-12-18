package com.mrssmiau.firebase.adivinaelpersonajefandom.juego

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuAdmin(navController: NavController) {
    val context = LocalContext.current  //Contexto para los mensajes
    val db = Firebase.firestore //Declaramos la conexion a firebase
    val personajeRef = db.collection("Peronajes")    //Referenciamos lc coleccion y el documento
    var nombre by remember { mutableStateOf("") }    //Nombre del documento
    var contenido: String? by remember { mutableStateOf("") }
    var exito: String? by remember { mutableStateOf("")}
    val distanciaAtaque by remember { mutableStateOf(mutableStateMapOf (
        "Mele" to false,
        "Distancia" to false,
        "Global" to false
    ))}
    val tipoAtaque by remember { mutableStateOf(mutableStateMapOf (
        "Fijo" to false,
        "Recto" to false,
        "Guiado" to false
    ))}
    val genero by remember { mutableStateOf(mutableStateMapOf (
        "Hombre" to false,
        "Mujer" to false,
        "Otro" to false
    ))}
    val familia by remember { mutableStateOf(mutableStateMapOf (
        "Primario" to false,
        "Militar" to false,
        "Magia" to false,
        "Apoyo"  to false,
        "Heroe" to false
    ))}
    var descripcion by remember { mutableStateOf("") }   //Valor campo Descripcion
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(0.dp)
            .background(color = Color.Cyan)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(18.dp),
            style = TextStyle(fontSize = 32.sp),
            textAlign = TextAlign.Center,
            text = "Personajes"
        )
        OutlinedTextField(
            modifier = Modifier.padding(9.dp),
            label = { Text(text = "Nombre: ") },
            value = nombre,
            onValueChange = { nombre = it })
        Spacer(modifier = Modifier.padding(9.dp))
        Text(
            modifier = Modifier.padding(horizontal = 16.dp),
            style = TextStyle(fontSize = 24.sp),
            text = "Distancia"
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 9.dp)
        ) {
            distanciaAtaque.forEach() { task ->
                Text(task.key, fontSize = 16.sp)
                Checkbox(
                    checked = task.value,
                    onCheckedChange = { distanciaAtaque[task.key] = it }
                )
            }
        }
        Spacer(modifier = Modifier.padding(9.dp))
        Text(
            modifier = Modifier.padding(horizontal = 16.dp),
            style = TextStyle(fontSize = 24.sp),
            text = "Tipo de Ataque"
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 9.dp)
        ) {
            tipoAtaque.forEach() { task ->
                Text(task.key, fontSize = 16.sp)
                Checkbox(
                    checked = task.value,
                    onCheckedChange = { tipoAtaque[task.key] = it }
                )
            }
        }
        Spacer(modifier = Modifier.padding(9.dp))
        Text(
            modifier = Modifier.padding(horizontal = 16.dp),
            style = TextStyle(fontSize = 24.sp),
            text = "Genero"
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 9.dp)
        ) {
            genero.forEach() { task ->
                Text(task.key, fontSize = 16.sp)
                Checkbox(
                    checked = task.value,
                    onCheckedChange = { genero[task.key] = it }
                )
            }
        }
        Spacer(modifier = Modifier.padding(9.dp))
        Text(
            modifier = Modifier.padding(horizontal = 16.dp),
            style = TextStyle(fontSize = 24.sp),
            text = "Familia"
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(horizontal = 9.dp)
                .horizontalScroll(rememberScrollState()),
        ) {
            familia.forEach() { task ->
                Text(task.key, fontSize = 16.sp)
                Checkbox(
                    checked = task.value,
                    onCheckedChange = { familia[task.key] = it }
                )
            }
        }
        Spacer(modifier = Modifier.padding(9.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(horizontal = 9.dp)
                .horizontalScroll(rememberScrollState()),
        ) {
            Button(modifier = Modifier
                .padding(5.dp),
                colors = ButtonDefaults.buttonColors(Color.LightGray),
                onClick = {
                    if(nombre!="") {
                        personajeRef.document(nombre).collection("atributos").document("distanciaAtaque").get().addOnSuccessListener { documento ->
                            val campos = documento?.data
                            contenido = campos?.get("Mele").toString()
                            if (contenido=="null") {
                                exito = "Mono registrado exitosamente"
                                personajeRef.document(nombre).collection("atributos")
                                    .document("distanciaAtaque").set(distanciaAtaque)
                                    .addOnFailureListener {
                                        exito = "Fallo en registrar distancia de ataque"
                                    }
                                personajeRef.document(nombre).collection("atributos")
                                    .document("tipoAtaque").set(tipoAtaque)
                                    .addOnFailureListener {
                                        exito = "Fallo en registrar tipo de ataque"
                                    }
                                personajeRef.document(nombre).collection("atributos")
                                    .document("genero").set(genero)
                                    .addOnFailureListener {
                                        exito = "Fallo en registrar genero"
                                    }
                                personajeRef.document(nombre).collection("atributos")
                                    .document("familia").set(familia)
                                    .addOnFailureListener {
                                        exito = "Fallo en registrar familia"
                                    }
                                mensaje(context,exito.toString())
                                nombre = ""
                                distanciaAtaque.forEach(){ campo->
                                    distanciaAtaque[campo.key] = false
                                }
                                tipoAtaque.forEach(){ campo->
                                    tipoAtaque[campo.key] = false
                                }
                                familia.forEach(){ campo->
                                    familia[campo.key] = false
                                }
                                genero.forEach(){ campo->
                                    genero[campo.key] = false
                                }
                            }else{
                                mensaje(context,"El mono ya existe")
                            }
                        }
                    }else{
                        mensaje(context,"Falta el nombre del mono")
                    }
                }) {
                    Text(text = "Nuevo")
            }
            Button(modifier = Modifier
                .padding(5.dp),
                colors = ButtonDefaults.buttonColors(Color.LightGray),
                onClick = {
                    if(nombre!="") {
                        personajeRef.document(nombre).collection("atributos").document("distanciaAtaque").get().addOnSuccessListener { documento ->
                            val campos = documento?.data
                            val tarea = campos?.get("Mele").toString()
                            contenido = tarea
                            if (contenido!="null") {
                                exito = "Mono consultado exitosamente"
                                personajeRef.document(nombre).collection("atributos").document("distanciaAtaque").get().addOnSuccessListener { atribs ->
                                    for ((key, value) in distanciaAtaque) {
                                        distanciaAtaque[key] = atribs.getBoolean(key) ?: false
                                    }
                                }
                                personajeRef.document(nombre).collection("atributos").document("familia").get().addOnSuccessListener { atribs ->
                                    for ((key, value) in familia) {
                                        familia[key] = atribs.getBoolean(key) ?: false
                                    }
                                }
                                personajeRef.document(nombre).collection("atributos").document("genero").get().addOnSuccessListener { atribs ->
                                    for ((key, value) in genero) {
                                        genero[key] = atribs.getBoolean(key) ?: false
                                    }
                                }
                                personajeRef.document(nombre).collection("atributos").document("tipoAtaque").get().addOnSuccessListener { atribs ->
                                    for ((key, value) in tipoAtaque) {
                                        tipoAtaque[key] = atribs.getBoolean(key) ?: false
                                    }
                                }
                                mensaje(context,exito.toString())
                            }else{
                                mensaje(context,"El mono no existe")
                            }
                        }
                    }else{
                        mensaje(context,"Falta el nombre del mono")
                    }
                }) {
                Text(text = "Consultar")
            }
            Button(modifier = Modifier
                .padding(5.dp),
                colors = ButtonDefaults.buttonColors(Color.LightGray),
                onClick = {
                    if(nombre!="") {
                        personajeRef.document(nombre).collection("atributos").document("distanciaAtaque").get().addOnSuccessListener { documento ->
                            val campos = documento?.data
                            contenido = campos?.get("Mele").toString()
                            if (contenido!="null") {
                                exito = "Mono actualizado exitosamente"
                                personajeRef.document(nombre).collection("atributos")
                                    .document("distanciaAtaque").set(distanciaAtaque)
                                    .addOnFailureListener {
                                        exito = "Fallo en actualizar distancia de ataque"
                                    }
                                personajeRef.document(nombre).collection("atributos")
                                    .document("tipoAtaque").set(tipoAtaque)
                                    .addOnFailureListener {
                                        exito = "Fallo en actualizar tipo de ataque"
                                    }
                                personajeRef.document(nombre).collection("atributos")
                                    .document("genero").set(genero)
                                    .addOnFailureListener {
                                        exito = "Fallo en actualizar genero"
                                    }
                                personajeRef.document(nombre).collection("atributos")
                                    .document("familia").set(familia)
                                    .addOnFailureListener {
                                        exito = "Fallo en actualizar familia"
                                    }
                                mensaje(context,exito.toString())
                                nombre = ""
                                distanciaAtaque.forEach(){ campo->
                                    distanciaAtaque[campo.key] = false
                                }
                                tipoAtaque.forEach(){ campo->
                                    tipoAtaque[campo.key] = false
                                }
                                familia.forEach(){ campo->
                                    familia[campo.key] = false
                                }
                                genero.forEach(){ campo->
                                    genero[campo.key] = false
                                }
                            }else{
                                mensaje(context,"El mono no existe")
                            }
                        }
                    }else{
                        mensaje(context,"Falta el nombre del mono")
                    }
                }) {
                Text(text = "Actualizar")
            }
            Button(modifier = Modifier
                .padding(5.dp),
                colors = ButtonDefaults.buttonColors(Color.LightGray),
                onClick = {
                    if(nombre!="") {
                        personajeRef.document(nombre).collection("atributos").document("distanciaAtaque").get().addOnSuccessListener { documento ->
                            val campos = documento?.data
                            contenido = campos?.get("Mele").toString()
                            if (contenido!="null") {
                                val atribRef = personajeRef.document(nombre).collection("atributos")
                                atribRef.get().addOnSuccessListener { documentos ->
                                    for (documento in documentos) {
                                        documento.reference.delete()
                                    }
                                    personajeRef.document(nombre).delete().addOnSuccessListener {
                                        mensaje(context,"Mono borrado exitosamente")
                                        nombre = ""
                                        distanciaAtaque.forEach(){ campo->
                                            distanciaAtaque[campo.key] = false
                                        }
                                        tipoAtaque.forEach(){ campo->
                                            tipoAtaque[campo.key] = false
                                        }
                                        familia.forEach(){ campo->
                                            familia[campo.key] = false
                                        }
                                        genero.forEach(){ campo->
                                            genero[campo.key] = false
                                        }
                                    }.addOnFailureListener{
                                        mensaje(context,"Error al borrar al mono")
                                    }
                                }
                            }else{
                                mensaje(context,"El mono no existe")
                            }
                        }
                    }else{
                        mensaje(context,"Falta el nombre del mono")
                    }
                }) {
                Text(text = "Borrar")
            }
            Button(modifier = Modifier
                .padding(9.dp),
                colors = ButtonDefaults.buttonColors(Color.LightGray),
                onClick = {
                    Firebase.auth.signOut()
                    navController.navigate("Blank")
                }) {
                Text(text = "Cerrar sesion")
            }
            Text(text = distanciaAtaque["Mele"].toString())
            OutlinedTextField(value = distanciaAtaque["Mele"].toString(), onValueChange = {})
        }
    }
}

    /*
        LazyColumn{
            items(atribDist){ Atrib->
                Checkbox(
                    checked = Atrib.,
                    onCheckedChange = { Atrib.cumple = it }
                )
            }
        }
        Text(modifier = Modifier.padding(9.dp),
            text = "Tipo de disparo")
        LazyColumn{
            items(atribTipoDisp){ Atrib->
                Checkbox(
                    checked = Atrib.cumple,
                    onCheckedChange = { Atrib.cumple = it }
                )
            }
        }*/
        /*
        OutlinedTextField(
            modifier = Modifier.padding(9.dp),
            label = { Text(text = "ID: ") },
            value = id,
            onValueChange = { id = it })
        OutlinedTextField(
            modifier = Modifier.padding(9.dp),
            label = { Text(text = "Tarea: ") },
            value = tarea,
            onValueChange = { tarea = it })
        OutlinedTextField(
            modifier = Modifier.padding(9.dp),
            label = { Text(text = "Descripcion: ") },
            value = descripcion,
            onValueChange = { descripcion = it })
        Spacer(modifier = Modifier.padding(9.dp))
        Button(modifier = Modifier
            .padding(9.dp)
            .align(Alignment.CenterHorizontally),
            colors = ButtonDefaults.buttonColors(Color.LightGray),
            onClick = {
                tareaRef.document(id).get().addOnSuccessListener { info ->
                    if (!info.exists()) {
                        val newTarea = hashMapOf(
                            "ID" to id,
                            "Nombre" to tarea,
                            "Descripcion" to descripcion,
                            "Fecha" to LocalDateTime.now()
                        )
                        tareaRef.document(id).set(newTarea).addOnSuccessListener {
                            Toast.makeText(context, "Tarea Registrada", Toast.LENGTH_LONG).show()
                            id = ""
                            tarea = ""
                            descripcion = ""
                        }.addOnFailureListener {
                            Toast.makeText(context, "Error al registrar tarea", Toast.LENGTH_LONG)
                                .show()
                        }
                    } else {
                        Toast.makeText(context, "La tarea ya existe", Toast.LENGTH_LONG).show()
                    }
                }
            }) {
            Text(text = "Nueva Tarea")
        }
        Button(modifier = Modifier
            .padding(9.dp)
            .align(Alignment.CenterHorizontally),
            colors = ButtonDefaults.buttonColors(Color.LightGray),
            onClick = {
                tareaRef.document(id).get().addOnSuccessListener { info ->
                    if (info.exists()) {
                        val campos = info?.data
                        //val desc = data?.get("campoNombre")
                        tarea = campos?.get("Nombre").toString()
                        descripcion = campos?.get("Descripcion").toString()
                        Toast.makeText(context, "Consulta exitosa", Toast.LENGTH_LONG).show()
                    } else {
                        Toast.makeText(context, "Tarea no encontrada", Toast.LENGTH_LONG).show()
                    }
                }.addOnFailureListener {
                    Toast.makeText(context, "Error al consultar", Toast.LENGTH_LONG).show()
                }
            }) {
            Text(text = "Consultar")
        }
        Button(modifier = Modifier
            .padding(9.dp)
            .align(Alignment.CenterHorizontally),
            colors = ButtonDefaults.buttonColors(Color.LightGray),
            onClick = {
                if (id == "" || tarea == "" || descripcion == "") {
                    Toast.makeText(context, "Error, algun campo esta vacio", Toast.LENGTH_LONG)
                        .show()
                } else {
                    tareaRef.document(id).get().addOnSuccessListener { info ->
                        if (info.exists()) {
                            val actuTarea = hashMapOf(
                                "Nombre" to tarea,
                                "Descripcion" to descripcion,
                                "Fecha" to LocalDateTime.now()
                            )

                            tareaRef.document(id).set(actuTarea)
                                .addOnSuccessListener {
                                    Toast.makeText(context, "Tarea actualizada", Toast.LENGTH_LONG)
                                        .show()
                                    id = ""
                                    tarea = ""
                                    descripcion = ""
                                }.addOnFailureListener {
                                    Toast.makeText(
                                        context,
                                        "Error al actualizar tarea",
                                        Toast.LENGTH_LONG
                                    ).show()
                                }
                        } else {
                            Toast.makeText(context, "La tarea no existe", Toast.LENGTH_LONG).show()
                        }
                    }
                }
            }) {
            Text(text = "Actualizar")
        }
        Button(modifier = Modifier
            .padding(9.dp)
            .align(Alignment.CenterHorizontally),
            colors = ButtonDefaults.buttonColors(Color.LightGray),
            onClick = {
                tareaRef.document(id).get().addOnSuccessListener { info ->
                    if (info.exists()) {
                        tareaRef.document(id).delete().addOnSuccessListener {
                            Toast.makeText(context, "Tarea Borrada", Toast.LENGTH_LONG).show()
                            id = ""
                            tarea = ""
                            descripcion = ""
                        }.addOnFailureListener {
                            Toast.makeText(context, "Error al borrar tarea", Toast.LENGTH_LONG)
                                .show()
                        }
                    } else {
                        Toast.makeText(context, "La tarea no existe", Toast.LENGTH_LONG).show()
                    }
                }
            }) {
            Text(text = "Borrar")
        }
        Button(modifier = Modifier
            .padding(9.dp)
            .align(Alignment.CenterHorizontally),
            colors = ButtonDefaults.buttonColors(Color.LightGray),
            onClick = {
                Firebase.auth.signOut()
                navController.navigate("Blank")
            }) {
            Text(text = "Cerrar sesion")
        }

         */
/*
fun limpiarAtributos{
    nombre = ""
    distanciaAtaque.forEach(){ campo->
        distanciaAtaque[campo.key] = false
    }
    tipoAtaque.forEach(){ campo->
        tipoAtaque[campo.key] = false
    }
    familia.forEach(){ campo->
        familia[campo.key] = false
    }
    genero.forEach(){ campo->
        genero[campo.key] = false
    }
}
*/
fun mensaje (context: Context, mensaje: String){
    Toast.makeText(context, mensaje, Toast.LENGTH_LONG).show()
}