package com.example.taller1.ViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.taller1.Model.InteresesData
import com.example.taller1.Model.StudiantePerfil

class PerfilViewModel: ViewModel(){
    val profile = StudiantePerfil(
        name = "Pedro Jose Garcia Correa",
        program = "Ingenieria de Sistemas",
        semester = 5,
        bio = "Estudiante apasionado por el desarrollo de software, " +
                "la inteligencia artificial y la resolución de problemas " +
                "mediante la tecnología.",
        age = 21,
        city = "Chia, Cundinamarca",
        email = "pedro.10@gmail.com",
        photoUrl = "https://upload.wikimedia.org/wikipedia/commons/6/6f/Escudo_Universidad_de_Cundinamarca.png"
    )
    val interests = InteresesData(
        hobbies = listOf(
            "Jugar futbol",
            "Programas proyector personales",
            "Jugar videojuegos",
            "Dibujar",
            "Leer"
        ),
        pastimes = listOf(
            "Descubir nueva musica",
            "Ver series y peliculas"
        ),
        sports = listOf(
            "Futbol los fines de seamana",
            "Ciclismo urbano",
            "Tenis"
        ),
        interest = listOf(
            "Desarrollo movil",
            "Ciberseguridad",
            "Creacion de Videojuegos",
            "Inteligencia Artificial"
        )

    )
    var showExtraInfo by mutableStateOf(false)
        private set
    var selectedTab by mutableStateOf(0)
        private set
    fun onTabSelected(index: Int){
        selectedTab = index
    }
    fun toggleExtraInfo(){
        showExtraInfo = !showExtraInfo
    }

}
