package com.example.ej_check_box

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Phone
import com.example.ej_check_box.ui.theme.Ej_check_boxTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Ej_check_boxTheme {
                MyScreen()
            }
        }
    }
}

@Composable
fun MyScreen() {
    var message by remember { mutableStateOf("") }
    var showProgress by remember { mutableStateOf(false) }
    var isChecked by remember { mutableStateOf(false) }
    var isSwitchOn by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf("Opción 1") }
    var currentImage by remember { mutableStateOf(1) }
    val scope = rememberCoroutineScope()

    fun onButtonClick() {
        message = "Botón presionado"
        showProgress = true

        scope.launch {
            delay(5000)
            showProgress = false
            message = ""
        }
    }

    fun toggleImage() {
        currentImage = when (currentImage) {
            1 -> 2
            2 -> 3
            else -> 1
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = { onButtonClick() },
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
            Text("Presionar")
        }

        if (showProgress) {
            Text(
                text = message,
                color = Color.Black,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }

        if (showProgress) {
            CircularProgressIndicator(modifier = Modifier.padding(bottom = 16.dp))
        }

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = isChecked,
                onCheckedChange = {
                    isChecked = it
                }
            )
            Text("Activar")
        }

        if (isChecked) {
            Text(
                text = "Lomo queso",
                color = Color.Black,
                modifier = Modifier.padding(top = 16.dp)
            )
        }


        Icon(
            imageVector = Icons.Filled.Phone,
            contentDescription = "telefono",
            modifier = Modifier.padding(top = 16.dp),
            tint = Color.Yellow
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("Mostrar grupo de opciones:")
            Switch(
                checked = isSwitchOn,
                onCheckedChange = { isSwitchOn = it }
            )
        }

        if (isSwitchOn) {
            Column(modifier = Modifier.selectableGroup()) {
                RadioButtonOption(
                    text = "Opción 1",
                    isSelected = selectedOption == "Opción 1",
                    onSelect = { selectedOption = "Opción 1" }
                )
                RadioButtonOption(
                    text = "Opción 2",
                    isSelected = selectedOption == "Opción 2",
                    onSelect = { selectedOption = "Opción 2" }
                )
                RadioButtonOption(
                    text = "Opción 3",
                    isSelected = selectedOption == "Opción 3",
                    onSelect = { selectedOption = "Opción 3" }
                )
            }
        }

        if (isSwitchOn) {
            Text(
                text = selectedOption,
                modifier = Modifier.padding(top = 16.dp)
            )
        }

        Spacer(modifier = Modifier.height(32.dp))
        Image(
            painter = painterResource(id = when (currentImage) {
                1 -> R.drawable.huevo1
                2 -> R.drawable.huevo2
                else -> R.drawable.huevo3
            }),
            contentDescription = "Imagen",
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clickable { toggleImage() }
        )
    }
}

@Composable
fun RadioButtonOption(text: String, isSelected: Boolean, onSelect: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .selectable(
                selected = isSelected,
                onClick = onSelect
            )
    ) {
        RadioButton(
            selected = isSelected,
            onClick = onSelect
        )
        Text(text = text)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Ej_check_boxTheme {
        MyScreen()
    }
}
