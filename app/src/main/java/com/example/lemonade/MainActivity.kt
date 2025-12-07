package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LemonadeTheme {
                Lemonade()
            }
        }
    }
}

// just popup all images on the screen after every tap
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Lemonade() {
    var step by remember { mutableStateOf(1) }
    var squeezeCount by remember { mutableStateOf(0) }

    Scaffold(
        modifier = Modifier,
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Lemonade",
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = colorResource(R.color.lightyellow)
                )
            )
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.tertiaryContainer),
            color = Color.White
        ) {
            when (step) {
                1 -> {
                    LemonImage(
                        text = R.string.tree,
                        image = R.drawable.lemon_tree,
                        contentDescriptions = R.string.lemon_tree,
                        onClick = {
                            squeezeCount = (2..4).random()
                            step = 2
                        }
                    )
                }

                2 -> {
                    LemonImage(
                        text = R.string.squeeze,
                        image = R.drawable.lemon_squeeze,
                        contentDescriptions = R.string.lemon,
                        onClick = {
                            squeezeCount--
                            if (squeezeCount == 0) {
                                step = 3
                            }
                        }
                    )
                }

                3 -> {
                    LemonImage(
                        text = R.string.drink,
                        image = R.drawable.lemon_drink,
                        contentDescriptions = R.string.gol,
                        onClick = {
                            step = 4
                        }
                    )
                }

                4 -> {
                    LemonImage(
                        text = R.string.restart_again,
                        image = R.drawable.lemon_restart,
                        contentDescriptions = R.string.empty_glass,
                        onClick = {
                            step = 1
                        }
                    )
                }
            }
        }
    }
}
@Composable
fun LemonImage(text: Int, image:Int, contentDescriptions: Int, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Box{
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = { onClick() },
                shape = RoundedCornerShape(20.dp),
                colors = ButtonDefaults.buttonColors(colorResource(R.color.pale_green))
            ) {
                Image(
                    painter = painterResource(image),
                    contentDescription = stringResource(contentDescriptions),
                )
            }
            Spacer(modifier = Modifier.padding(16.dp))
            Text(
                text = stringResource(text),
                color = Color.Black
            )
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LemonadePreview() {
    LemonadeTheme {
        Lemonade()
    }
}