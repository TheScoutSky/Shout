package de.theskyscout.shout.ui.screens.MainScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import de.theskyscout.shout.data.fakeChats
import de.theskyscout.shout.ui.screens.MainScreen.components.NavigationBar
import de.theskyscout.shout.ui.screens.MainScreen.pages.HomePage
import kotlinx.serialization.Serializable

@Serializable
object MainScreen

@Composable
fun MainScreenComposable() {
    NavigationBar { pagerState ->
        HorizontalPager(state = pagerState) {index ->
            when(index) {
                0 -> {
                    HomePage(chats = fakeChats)
                }
                1 -> {
                    Box(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Text(
                            text = "Chat",
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                }
                2 -> {
                    Box(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Text(
                            text = "Profile",
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                }
                3 -> {
                    Box(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Text(
                            text = "Ka",
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewHomeScreen() {
    MainScreenComposable()
}