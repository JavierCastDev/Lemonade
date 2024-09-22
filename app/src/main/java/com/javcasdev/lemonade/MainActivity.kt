package com.javcasdev.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.javcasdev.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LemonadeTheme {
                LemonadeApp()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LemonadeApp() {
    LemonadeGlassAndTree(
        modifier = Modifier
            .padding(32.dp)
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    )
}

@Composable
fun LemonadeGlassAndTree(modifier: Modifier = Modifier) {

    var lemonade by remember { mutableStateOf(1) }
    var countSqueeze by remember { mutableStateOf(0) }

    var image = when (lemonade) {
        1 -> R.drawable.lemon_tree
        2 -> R.drawable.lemon_squeeze
        3 -> R.drawable.lemon_drink
        else -> R.drawable.lemon_restart
    }

    var imageDescription = when (lemonade) {
        1 -> R.string.tap_the_lemon_tree
        2 -> R.string.keep_tapping_the_lemon
        3 -> R.string.tap_the_lemonade
        else -> R.string.tap_the_empty_glass
    }

    Column(modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(image),
            contentDescription = "1",
            Modifier
                .padding(16.dp)
                .size(200.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(color = Color(android.graphics.Color.parseColor("#B2E0F5")))
                .clickable(true, onClick = {
                    when (lemonade) {
                        1 -> {
                            lemonade = 2 // Cambia a "exprimir"
                            countSqueeze =
                                (2..4).random() // Establece un nuevo conteo al cambiar a 2
                        }

                        2 -> {
                            if (countSqueeze > 0) {
                                countSqueeze-- // Disminuye el conteo
                            }
                            if (countSqueeze == 0) {
                                lemonade = 3 // Cambia a "beber" cuando countSqueeze llega a 0
                            }
                        }

                        3 -> {
                            lemonade = 4 // Cambia a "reiniciar"
                        }

                        4 -> {
                            lemonade = 1 // Reinicia el juego
                        }
                    }
                })
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(stringResource(imageDescription), fontSize = 18.sp, textAlign = TextAlign.Center)
    }
}



