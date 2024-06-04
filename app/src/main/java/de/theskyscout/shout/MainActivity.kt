package de.theskyscout.shout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import de.theskyscout.shout.ui.navigation.ScreenNavigation
import de.theskyscout.shout.ui.theme.ShoutTheme

val viewModel = MainViewModel()

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ShoutTheme {
                ScreenNavigation()
            }
        }
    }
}

@Preview
@Composable
fun PreviewMain() {
    ShoutTheme {
        ScreenNavigation()
    }
}