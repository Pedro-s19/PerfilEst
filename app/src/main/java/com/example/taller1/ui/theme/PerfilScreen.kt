package com.example.taller1.ui.theme

import android.R.attr.contentDescription
import coil.compose.AsyncImage
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.taller1.R
import com.example.taller1.ViewModel.PerfilViewModel


@Composable

fun PerfilScreen(viewModel: PerfilViewModel){
    val profile = viewModel.profile
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(16.dp),
        horizontalAlignment =  Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        //Foto del Estudiante
        AsyncImage(
            model = profile.photoUrl,
            contentDescription = "Foto de ${profile.name}",
            contentScale = ContentScale.Crop,
            error = painterResource(id = R.drawable.profile_placeholder),
            placeholder = painterResource(id = R.drawable.profile_placeholder),
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape)

        )
        Spacer(modifier = Modifier.height(16.dp))

        //Programa y Semestre
        Text(
            text = profile.name,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "${profile.program} Semestre ${profile.semester}",
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(16.dp))

        //Biografia
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant
            )
        ) {
            Text(
                text = profile.bio,
                modifier = Modifier.padding(16.dp),
                fontSize = 14.sp,
                lineHeight = 22.sp

            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        //Boton mostrar/ocultar info adicional
        Button(onClick = {viewModel.toggleExtraInfo()}) {
            Text(
                text = if(viewModel.showExtraInfo)
                    "Ocultar informacion adicional"
                else
                    "Ver informacion adicional"
            )
        }
        AnimatedVisibility(visible = viewModel.showExtraInfo) {
            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 16.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    InfoRow(label = "Edad", value = "${profile.age} años")
                    InfoRow(label = "Ciudad", value = profile.city)
                    InfoRow(label = "Correo", value = profile.email)
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}
@Composable
fun InfoRow(label: String, value: String){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween

    ){
        Text(text = label, fontWeight = FontWeight.SemiBold, fontSize = 14.sp)
        Text(text = value, fontSize = 14.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
    }
}