package de.theskyscout.shout.ui.screens.MainScreen.components

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Videocam
import androidx.compose.material.icons.outlined.Beenhere
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material.icons.outlined.Chat
import androidx.compose.material.icons.outlined.ChatBubble
import androidx.compose.material.icons.outlined.ChatBubbleOutline
import androidx.compose.material.icons.outlined.Groups
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.rounded.Beenhere
import androidx.compose.material.icons.rounded.Call
import androidx.compose.material.icons.rounded.ChatBubble
import androidx.compose.material.icons.rounded.Groups
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.key.Key.Companion.Home
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import de.theskyscout.shout.data.database.entity.Chat
import de.theskyscout.shout.viewModel
import dev.chrisbanes.haze.HazeDefaults
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.haze
import dev.chrisbanes.haze.hazeChild
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

val bottomNavigationItems = listOf(
    BottomNavigationItem(
        title = "Chats",
        unselectedIcon = Icons.Outlined.ChatBubbleOutline,
        selectedIcon = Icons.Rounded.ChatBubble,
        hasNews = false
    ),
    BottomNavigationItem(
        title = "Groups",
        unselectedIcon = Icons.Outlined.Groups,
        selectedIcon = Icons.Rounded.Groups,
        hasNews = false
    ),
    BottomNavigationItem(
        title = "Story",
        unselectedIcon = Icons.Outlined.Beenhere,
        selectedIcon = Icons.Rounded.Beenhere,
        hasNews = false
    ),
    BottomNavigationItem(
        title = "Calls",
        unselectedIcon = Icons.Outlined.Call,
        selectedIcon = Icons.Rounded.Call,
        hasNews = false
    ),
)

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NavigationBar(
    height: Dp = 80.dp,
    content: @Composable (PagerState) -> Unit
) {
    val hazeState = remember { HazeState() }
    var selected by remember { mutableIntStateOf(0) }
    val pagerState = rememberPagerState {
        bottomNavigationItems.size
    }

    val scope = rememberCoroutineScope()
    LaunchedEffect(pagerState.currentPage) {
        selected = pagerState.currentPage
    }

    Scaffold(
        contentColor = MaterialTheme.colorScheme.onBackground,
        bottomBar = {
            BottomBar(
                height = height,
                hazeState = hazeState,
                isSelected = { index -> selected == index },
                onClick = { index, _ ->
                    scope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                }
            )
        },
        topBar = {
            TopBar(
                navigationItem = bottomNavigationItems[selected],
                hazeState = hazeState
            )
        }
    ) {
        Box(
            modifier = Modifier.haze(
                state = hazeState,
                style = HazeDefaults.style(
                    tint = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.5f),
                    blurRadius = 8.dp
                )
            )
        ) {
            content(pagerState)
        }
    }
}

@Composable
fun RowScope.NavigationBarItem(
    onClick: () -> Unit,
    iconSelected: ImageVector,
    iconUnselected: ImageVector,
    tintSelected : Color = MaterialTheme.colorScheme.primary,
    tintUnselected : Color = MaterialTheme.colorScheme.onSurface,
    size : Dp = 30.dp,
    isSelected: Boolean,
) {
    Box(
        modifier = Modifier
            .fillMaxHeight()
            .weight(1f)
            .onSizeChanged { it.width }
            .clickable {
                onClick()
            },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = if (isSelected) iconSelected else iconUnselected,
            contentDescription = "",
            modifier = Modifier
                .padding(8.dp)
                .size(size),
            tint = if (isSelected) tintSelected else tintUnselected
        )
    }
}

@OptIn(ExperimentalCoilApi::class)
@Composable
fun TopBar(
    hazeState: HazeState,
    navigationItem: BottomNavigationItem
) {
    Column {
        Column(
            modifier = Modifier
                .height(100.dp)
                .fillMaxWidth()
                .background(
                    brush = Brush.verticalGradient(
                        listOf(
                            MaterialTheme.colorScheme.onPrimaryContainer,
                            MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0f)
                        )
                    )
                )
                .hazeChild(hazeState)
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            Box (
                modifier = Modifier
                    .fillMaxSize()
            ) {

                // Profile Image
                val imagePainter = rememberImagePainter(data = "https://images.ctfassets.net/h6goo9gw1hh6/2sNZtFAWOdP1lmQ33VwRN3/24e953b920a9cd0ff2e1d587742a2472/1-intro-photo-final.jpg?w=1200&h=992&fl=progressive&q=70&fm=jpg")
                Image(
                    painter = imagePainter,
                    contentDescription = "Profile Picture",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(10.dp)
                        .size(40.dp)
                        .clip(CircleShape)
                )

                // Title
                Text(
                    text = navigationItem.title,
                    fontSize = 20.sp,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(8.dp)
                )

                // More Button
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = "More Button",
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .clip(CircleShape)
                        .padding(10.dp),
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }
        HorizontalDivider(
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f),
            thickness = 0.5.dp
        )
    }
}


@Composable
fun BottomBar(
    height: Dp,
    hazeState: HazeState,
    isSelected: (Int) -> Boolean,
    onClick: (Int, BottomNavigationItem) -> Unit
) {
    NavigationBar(
            containerColor =  Color.Transparent,
            tonalElevation = 0.dp,
            modifier = Modifier
                .height(height)
                .background(
                    brush = Brush.verticalGradient(
                        listOf(
                            MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0f),
                            MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    )
                )
                .hazeChild(hazeState)
        ) {
            Column {
                HorizontalDivider(
                    thickness = 0.5.dp,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f)
                )
                Row {
                    bottomNavigationItems.forEachIndexed { index, item ->
                        NavigationBarItem(
                            onClick = {
                                onClick(index, item)
                            },
                            iconSelected = item.selectedIcon,
                            iconUnselected = item.unselectedIcon,
                            isSelected = isSelected(index),
                        )
                    }
                }
            }
        }

}

@Preview
@Composable
fun PreviewNavigationBar() {
    NavigationBar { pagerState ->
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = "Content",
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

data class BottomNavigationItem(
    val title: String,
    val unselectedIcon: ImageVector,
    val selectedIcon: ImageVector,
    val hasNews: Boolean,
    val badgeCount: Int? = null
)