package de.theskyscout.shout.ui.screens.ChatScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.Send
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Videocam
import androidx.compose.material.icons.rounded.Call
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import de.theskyscout.shout.data.database.entity.Chat
import de.theskyscout.shout.data.database.entity.Message
import de.theskyscout.shout.data.fakeUsers
import de.theskyscout.shout.ui.screens.ChatScreen.components.SpeechBubble
import de.theskyscout.shout.ui.screens.ChatScreen.components.SpeechBubbleType
import de.theskyscout.shout.ui.theme.ShoutTheme
import de.theskyscout.shout.viewModel
import dev.chrisbanes.haze.HazeDefaults
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.haze
import dev.chrisbanes.haze.hazeChild
import kotlinx.serialization.Serializable

@Serializable
data class ChatScreen(
    val chatId: String,
)

@Composable
fun ChatScreenComposable(chatScreen: ChatScreen) {
    val chat = viewModel.getChatById(chatScreen.chatId)!!
    val receiver = viewModel.getUserById(chat.data.receiverId)!!
    val hazeState = remember { HazeState() }
    Scaffold (
        topBar = {
            ChatTopBar(
                username = receiver.data.name,
                profileUrl = receiver.data.profilePictureURI,
                hazeState = hazeState
            )
        },
        bottomBar = {
            ChatBar(hazeState = hazeState)
        }
    ) {
        Box (
            modifier = Modifier.haze(
                state = hazeState,
                style = HazeDefaults.style(
                    tint = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.5f),
                    blurRadius = 8.dp
                )
            )
        ) {
            ChatContent(chat = chat)
        }
        it
    }
}

@Composable
fun ChatContent(
    chat: Chat
) {
    val messages = chat.data.messages
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        LazyColumn {
            item{ Spacer(modifier = Modifier.height(100.dp)) }
            items(messages.size) { index ->
                ChatMessageItem(messages[index])
            }
            item{ Spacer(modifier = Modifier.height(90.dp))}
        }
    }
}

@Composable
fun ChatMessageItem(chatMessage: Message) {
    val currentUser = fakeUsers[0]
    if (chatMessage.isFrom(currentUser)) ChatMessageIn(chatMessage.data.message)
    else ChatMessageOut(chatMessage.data.message)
}

@Composable
fun ChatMessageOut(message: String) {
    Box (
        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                indication = rememberRipple(
                    bounded = true,
                    color = MaterialTheme.colorScheme.primary
                ),
                interactionSource = remember { MutableInteractionSource() }
            ) { }
    ) {
        SpeechBubble (
            tailSize = 10.dp,
            cornerRadius = 10.dp,
            type = SpeechBubbleType.OUT,
            color = MaterialTheme.colorScheme.primary,
            tail = true,
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(5.dp)
        ) {
            Text(
                text = message,
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.padding(10.dp)
            )
        }
    }
}

@Composable
fun ChatMessageIn(message: String) {
    Box (
        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                indication = rememberRipple(
                    bounded = true,
                    color = MaterialTheme.colorScheme.primary
                ),
                interactionSource = remember { MutableInteractionSource() }
            ) { }
    ) {
        SpeechBubble (
            tailSize = 10.dp,
            cornerRadius = 10.dp,
            type = SpeechBubbleType.IN,
            color = MaterialTheme.colorScheme.surface,
            tail = true,
            modifier = Modifier.padding(5.dp)
        ) {
            Text(
                text = message,
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(10.dp)
            )
        }
    }
}

@Composable
fun ChatTopBar(
    username: String,
    profileUrl: String?,
    hazeState: HazeState
) {
    Box {
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
            Row (
                modifier = Modifier
                    .weight(1f),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Back button
                Icon(
                    imageVector = Icons.Default.ArrowBackIosNew,
                    contentDescription = "Back Button",
                    modifier = Modifier
                        .clip(CircleShape)
                        .clickable {
                            viewModel.navController.popBackStack()
                        }
                        .padding(10.dp),
                    tint = MaterialTheme.colorScheme.primary
                )

                // Chat Image
                if (profileUrl == null) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "Chat Image",
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                            .padding(10.dp)
                    )
                } else {
                    // Chat Image
                    val imagePainter = rememberImagePainter(data = profileUrl)
                    Image(
                        painter = imagePainter,
                        contentDescription = "Profile Picture",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                    )
                }
                // Chat Username
                Box(
                    modifier = Modifier
                        .height(80.dp)
                        .weight(1f)
                        .padding(start = 10.dp)
                        .clickable {  }
                ) {
                    Text(
                        text = username,
                        fontSize = 24.sp,
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.align(Alignment.CenterStart)
                    )
                }


                Icon(
                    imageVector = Icons.Default.Videocam,
                    contentDescription = "Chat Image",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .clip(CircleShape)
                        .clickable {

                        }
                        .padding(10.dp)
                )
                Icon(
                    imageVector = Icons.Rounded.Call,
                    contentDescription = "Chat Image",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .clip(CircleShape)
                        .clickable {

                        }
                        .padding(10.dp)
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
fun ChatBar(
    hazeState: HazeState
) {
    val message = remember { mutableStateOf("") }
    // Messages Input
    Box (
        modifier = Modifier
            .fillMaxWidth()
            .systemBarsPadding()
    ) {
        Row (
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .imePadding()
                .padding(10.dp)
        ){
            // Message Input
            TextField(
                value = message.value,
                placeholder = {
                    Text("Message")
                },
                onValueChange = {
                    message.value = it
                },
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(30.dp))
                    .background(MaterialTheme.colorScheme.surface.copy(alpha = 0.5f))
                    .hazeChild(
                        state = hazeState,
                        shape = RoundedCornerShape(30.dp)
                    ),
                singleLine = false,
                maxLines = 4,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent
                )
            )

            Spacer(modifier = Modifier.width(10.dp))

            // Send Button

            Icon(
                imageVector = Icons.AutoMirrored.Rounded.Send,
                contentDescription = "Send Button",
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .align(Alignment.Bottom)
                    .size(60.dp)
                    .clip(RoundedCornerShape(50))
                    .background(MaterialTheme.colorScheme.surface.copy(alpha = 0.5f))
                    .hazeChild(
                        state = hazeState,
                        shape = RoundedCornerShape(50)
                    )
                    .clickable(
                        indication = rememberRipple(
                            bounded = true,
                            radius = 30.dp,
                            color = MaterialTheme.colorScheme.primary
                        ),
                        interactionSource = remember { MutableInteractionSource() }
                    ) {
                        // Send Message

                    }
                    .padding(15.dp)
            )
        }
    }
}

@Preview
@Composable
fun PreviewChatTopBar() {
    ShoutTheme (darkTheme = true) {
        ChatScreenComposable(chatScreen = ChatScreen("chat1"))
    }
}