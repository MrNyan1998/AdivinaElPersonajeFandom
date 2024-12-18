package com.mrssmiau.firebase.adivinaelpersonajefandom.juego

class Task(val nombre: String, var marcado: Boolean) {
    // Define la función equals, que solo compara el nombre de la tarea
    /*override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Task) return false
        if (nombre != other.nombre) return false
        return true
    }

    // Define la función hashCode, que solo usa el nombre de la tarea
    override fun hashCode(): Int {
        return nombre.hashCode()
    }*/
}