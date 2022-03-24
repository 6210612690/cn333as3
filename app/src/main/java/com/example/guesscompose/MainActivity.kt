package com.example.guesscompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Gnumber()

        }
    }
}


var random = Random.nextInt(1, 1000)



@Composable
fun Gnumber(){

    var number = rememberSaveable { mutableStateOf("") }
    val count = remember { mutableStateOf(0) }
    val infotext = remember { mutableStateOf("") }
    Column (modifier = Modifier.fillMaxHeight(),
        verticalArrangement = Arrangement.SpaceEvenly,

    ){
        Text(
            "Guess number from 1 - 1000",
            fontSize = 18.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .padding(bottom = 60.dp )

        )
        Text(
            text = infotext.value,
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 100.dp)
        )
        TextField(
            value = number.value,
            onValueChange = { number.value = it },
            label = { Text("Number") },
            modifier = Modifier
                .padding(top = 100.dp)
                .fillMaxWidth()
        )
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 300.dp),
            horizontalArrangement = Arrangement.SpaceBetween
                ){
            RestartButton(word = "Restart", onClick = {
                count.value=0
                infotext.value=""
                random = Random.nextInt(1, 1000)})
            GuessButton(word = "Guess", onClick = {
                if (number.value.toInt() < random){
                    count.value += 1
                    infotext.value = "Wrong your number is higher!"
                }
                else if (number.value.toInt() > random){
                    count.value += 1
                    infotext.value = "Wrong your number is lower!"
                }
                else {
                    count.value += 1
                    infotext.value = "YOU ARE THE WINNER!"

                }

            })
        }

        Text(
            text = "Times: "+ count.value,
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 100.dp)

        )
    }

}


@Composable
fun RestartButton(word: String, onClick: () -> Unit){
    Button(
        onClick = onClick
    ) {
        Text(text = "$word!", fontSize = 20.sp)
    }

}

@Composable
fun GuessButton(word: String, onClick: () -> Unit){
    Button(
        onClick = onClick
    ) {
        Text(text = "$word!", fontSize = 20.sp)
    }

}

@Preview
@Composable
fun MyPreview() {
    Gnumber()
}

