package com.mrssmiau.firebase.adivinaelpersonajefandom.juego

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.firestore


//var personajes = mutableStateOf(emptyList<String>())
//var personajes = mutableStateOf(mutableStateListOf(""))

//@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Menu(navController: NavController){
    val db = Firebase.firestore //Declaramos la conexion a firebase
    //var personajes by remember { mutableStateOf(emptyList<String>()) }
    var personajes by remember { mutableStateOf(mutableStateMapOf(
        "nombre" to ""
    ))}
    //var personajes by remember {mutableStateOf(mutableStateListOf(""))}
    /*
    GlobalScope.launch{
        var respuesta = async(Dispatchers.IO){
            cargarPersonajes()
            }
        if(respuesta.await()){

        }else{

        }
    }

     */
    /*
    LaunchedEffect(Unit) {
        var respuesta = async(Dispatchers.IO){
            cargarPersonajes()
        }
        if(respuesta.await()){

        }else{

        }
        /*db.collection("Peronajes").get().addOnSuccessListener { perso ->
            for (nombre in perso){
                personajes.add(nombre.id)
            }
        }*/
        //personajes = cargarPersonajes().value
    }

     */
    val idUser = Firebase.auth.currentUser?.uid
    val context = LocalContext.current  //Contexto para los mensajes

    val personajesRef = db.collection("Usuarios").document(idUser?:"SinUsuario").collection("Tareas")    //Referenciamos lc coleccion y el documento
    var id by remember { mutableStateOf("") }    //Nombre del documento
    var tarea by remember { mutableStateOf("") } //Valor campo Nombre
    var descripcion by remember { mutableStateOf("") }   //Valor campo Descripcion
    val distanciaAtaque by remember { mutableStateOf(
        mutableStateMapOf (
        "Mele" to false,
        "Distancia" to false,
        "Global" to false
    )
    )}
    val tipoAtaque by remember { mutableStateOf(
        mutableStateMapOf (
        "Fijo" to false,
        "Recto" to false,
        "Guiado" to false
    )
    )}
    val genero by remember { mutableStateOf(
        mutableStateMapOf (
        "Hombre" to false,
        "Mujer" to false,
        "Otro" to false
    )
    )}
    val familia by remember { mutableStateOf(
        mutableStateMapOf (
        "Primario" to false,
        "Militar" to false,
        "Magia" to false,
        "Apoyo"  to false,
        "Heroe" to false
    )
    )}
    /*
    val personajes = snapshotFlow { db.collection("Personajes").get().await().asFlow() }
        .map { result -> result.map { it.id } }
        .collectAsState(initial = emptyList())

    val personajes = db.collection("Personajes")
        .map { result -> result.map { it.id } }
        .collectAsState(initial = emptyList())

    val personajes = async { db.collection("Personajes").get().await() }
        .map { result -> result.map { it.id } }
        .collectAsState(initial = emptyList())
        */

    //var personajes by remember { mutableStateOf(mutableStateListOf(""))}
    var expanded by remember { mutableStateOf(false) }
    var selectedOptionText by remember { mutableStateOf("") }
    db.collection("Peronajes").get().addOnSuccessListener { perso ->
        for (nombre in perso){
            personajes.put(nombre.id, nombre.id)
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(0.dp)
            .background(color = Color.Cyan)
            .verticalScroll(rememberScrollState())
    ) {
        Text(text = puntaciones(personajesRef))
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = {
                expanded = !expanded
            }
        ) {
            TextField(
                readOnly = true,
                value = selectedOptionText,
                onValueChange = { },
                label = { Text("Label") },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(
                        expanded = expanded
                    )
                },
                colors = ExposedDropdownMenuDefaults.textFieldColors()
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = {
                    expanded = false
                }
            ) {
                personajes.values.forEach(){}
                personajes.values.forEach { selectionOption ->
                    DropdownMenuItem(
                        text = {
                            Text(text = selectionOption)
                        },
                        onClick = {
                            selectedOptionText = selectionOption
                            expanded = false
                        }
                    )
                }
            }
        }
        OutlinedTextField(value = personajes["Sauda"].toString(), onValueChange = {tarea=it})
        //Text(text = personajes[0].toString())
        Button(onClick = {
            //Text(text = "")
            //cargarPersonajes()
            db.collection("Peronajes").get().addOnSuccessListener {nombres ->
                for (nombre in nombres){
                    personajes.put(nombre.id, nombre.id)
                    tarea = "$tarea ${nombre.id}"
                }
            }
        }) {
            Text(text = "Actualizar")
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
/*
        Column {
            TextField(
                readOnly = true,
                value = selectedOptionText,
                onValueChange = { },
                label = { Text("Label") },
                trailingIcon = {
                    IconButton(onClick = { expanded = !expanded }) {
                        Icon(
                            imageVector = if (expanded) Icons.Filled.ArrowDropDown else Icons.Filled.ArrowDropDown,
                            contentDescription = null
                        )
                    }
                }
            )
            if (expanded) {
                LazyColumn {
                    items(personajes) { option ->
                        Text(
                            text = option,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    selectedOptionText = option
                                    expanded = false
                                }
                                .padding(16.dp)
                        )
                    }
                }
            }
        }*/
    }
}
/*
fun Menu(navController: NavController) {
    val idUser = Firebase.auth.currentUser?.uid
    val context = LocalContext.current  //Contexto para los mensajes
    val db = Firebase.firestore //Declaramos la conexion a firebase
    val tareaRef = db.collection("Usuarios").document(idUser?:"SinUsuario").collection("Tareas")    //Referenciamos lc coleccion y el documento
    var id by remember { mutableStateOf("") }    //Nombre del documento
    var tarea by remember { mutableStateOf("") } //Valor campo Nombre
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
            text = "Tareas"
        )
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
    }
}
*/

fun puntaciones(personajesRef: CollectionReference): String {
    var puntajeAlto = ""
        val puntos = personajesRef.document().get().addOnSuccessListener { puntaje ->
        //val desc = data?.get("campoNombre")
        puntajeAlto = puntaje?.data?.get("Puntaje").toString()
    }
    return puntajeAlto
}


/*
fun cargarPersonajes(): State<List<String>> {
    val personajes = mutableStateOf(mutableStateListOf(""))
    val db = Firebase.firestore //Declaramos la conexion a firebase
    db.collection("Peronajes").get().addOnSuccessListener { perso ->
        for (nombre in perso){
            personajes.value.add(nombre.id)
        }
    }
    //Text(text = "Holi")
    return personajes
}*/
/*
fun cargarPersonajes(): Boolean {
    val personajes = mutableStateOf(mutableStateListOf(""))
    val db = Firebase.firestore //Declaramos la conexion a firebase
    var resultado = false
    db.collection("Peronajes").get().addOnSuccessListener { perso ->
        for (nombre in perso){
            personajes.value.add(nombre.id)
        }
        resultado = true //Indica que la carga fue exitosa
    }.addOnFailureListener {
        resultado = false //Indica que la carga falló
    }
    //Text(text = "Holi")
    return resultado
}*/

/*
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun eleme(){
    var expanded by remember { mutableStateOf(false) }
    var selectedOptionText by remember { mutableStateOf("") }
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        }
    ) {
        TextField(
            readOnly = true,
            value = selectedOptionText,
            onValueChange = { },
            label = { Text("Label") },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = expanded
                )
            },
            colors = ExposedDropdownMenuDefaults.textFieldColors()
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            }
        ) {
            personajes.value.forEach { selectionOption ->
                DropdownMenuItem(
                    text = {
                        Text(text = selectionOption)
                    },
                    onClick = {
                        selectedOptionText = selectionOption
                        expanded = false
                    }
                )
            }
        }
    }
}
*/