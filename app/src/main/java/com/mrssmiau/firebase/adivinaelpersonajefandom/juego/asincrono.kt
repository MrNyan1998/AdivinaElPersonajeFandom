package com.mrssmiau.firebase.adivinaelpersonajefandom.juego

import kotlinx.coroutines.delay

/*
suspend fun cargarPersonajes(): State<Any> {
    val db = Firebase.firestore //Declaramos la conexion a firebase
    return snapshotFlow<Flow<QueryDocumentSnapshot>>(db.collection("Personajes").get().await()::asFlow)
        .map { result -> result.map { it.id } }
        .collectAsState(initial = emptyList())
}*/

suspend fun fetchAsyncData(server: String): Pair<String, String> {
    delay(2000)
    val data = "Async data from $server"
    return server to data
}